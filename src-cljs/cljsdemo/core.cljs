(ns cljsdemo.core
  (:use [cljs.reader :only [read-string]])
  (:require [cljsdemo.utils :as utils]
            [cljsdemo.sockets :as sockets]))


(defn append-msg-to [msg id]
  (let [el ($ id)
        obj (.get el 0)]
    (-> el
      (.append (str (html-escape msg) "<br>")))
    (set! (.-scrollTop obj) (.-scrollHeight obj))))