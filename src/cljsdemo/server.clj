(ns cljsdemo.server
  (:require [aleph.http :as aleph]
            [lamina.core :as lamina]
            [ring.middleware.resource :as resource]
            [ring.middleware.file-info :as file-info]))

(def broadcast-channel (lamina/permanent-channel))

(defn broadcast
  "Sends a message to all connections"
  [message]
  (let [serialized-message (pr-str message)]
    (println (str "OUTBOUND: " serialized-message))
    (lamina/enqueue broadcast-channel serialized-message)))

(defn responder-for
  [sender]
  (fn [msg-str]
    (when msg-str
      (println (str "INBOUND: " msg-str))
      (let [msg (read-string msg-str)]
        (broadcast (assoc msg :sender sender))))))

(defn register!
  "Adds a new connection to the server"
  [ch msg-str]
  (lamina/siphon broadcast-channel ch)
  (let [msg (read-string msg-str)
        sender (:payload msg)]
    (lamina/receive-all ch (responder-for sender))
    (broadcast {:identifier :chat :payload (str (sender :name) " has joined!") :sender sender})))

(defn connection-router
  "Receives new connections. Some browsers will send null messages on close, causing a seemingly needless guard clause"
  [ch handshake]
  (lamina/receive ch
    (fn [message]
      (when message
        (register! ch message)))))

(defn default-handler [request]
  {:status 404
   :headers {"content-type" "text/plain"}
   :body "Not found"})

(defn wrap-to-index [handler]
  (fn [req]
    (handler
      (update-in req [:uri] #(if (= "/" %) "/index.html" %)))))

(def static-file-server
  (-> default-handler
      (resource/wrap-resource "public")
      (file-info/wrap-file-info)
      (wrap-to-index)
      aleph/wrap-ring-handler))

(defn -main []
  (aleph/start-http-server
    (var connection-router)
    {:port 8008 :websocket true})
  (aleph/start-http-server
    static-file-server
    {:port 8016}))
