(ns cljsdemo.sockets
  (:use [cljs.reader :only [read-string]])
  (:require [cljsdemo.utils :as utils]))

(def web-socket (or (.-WebSocket js/window) (.-MozWebSocket js/window)))

(def host (str "ws://" (-> (.-location js/window) .-hostname) ":8080"))

(defn create-socket [f]
  (let [ws (new web-socket host)]
    (set! (.-onmessage ws) (fn [e] (f (.-data e))))
    ws
    ))

(def responders (atom []))

(defn on-message-received [f]
  (swap! responders conj f))

(defn receive-message [msg]
  (utils/append-msg-to msg :#log)
  (let [data (read-string msg)]
    (doseq [f @responders]
      (f data))))

(def server (create-socket receive-message ))

(defn send [identifier payload]
  (.send server (pr-str {:identifier identifier :payload payload})))
