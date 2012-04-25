(ns cljsdemo.core
  (:use [cljs.reader :only [read-string]])
  (:require [cljsdemo.utils :as utils]
            [cljsdemo.sockets :as sockets]))


; {:identifer :chat :payload "Hello"}
(utils/on-user-input
  (fn [msg]
    (sockets/send :chat msg)))

; {:identifer :chat :payload "Hello" :sender {:name "Scott" :email "sparker@example.com"}
(sockets/on-message-received
  (fn [data]
    (utils/append-msg-to
      (str (get-in data [:sender :name]) ":" (:payload data))
      :#chatlog)))
