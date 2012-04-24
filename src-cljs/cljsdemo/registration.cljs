(ns cljsdemo.registration
  (:use [cljs.reader :only [read-string]])
  (:require [cljsdemo.utils :as utils]
            [cljsdemo.sockets :as sockets]))

(defn register!
  [handle email]
  (sockets/send :registered {:name handle :email email}))

(utils/on-user-register register!)
