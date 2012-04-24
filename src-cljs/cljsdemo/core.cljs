(ns cljsdemo.core
  (:use [cljs.reader :only [read-string]])
  (:require [cljsdemo.utils :as utils]
            [cljsdemo.sockets :as sockets]))

(utils/on-user-input (fn [msg] (.log js/console msg) ) "default")
