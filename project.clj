(defproject cljsdemo "0.0.1-SNAPSHOT"
  :description "For the April Chicago Clojure Meeting"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/clojurescript "0.0-971"]
                 [aleph "0.2.1-alpha2-SNAPSHOT"]
                 [domina "1.0.0-alpha1"]
                 [ring "1.0.2"]
                 [jayq "0.1.0-SNAPSHOT"]]
  :plugins [[lein-cljsbuild "0.0.13"]]
  :cljsbuild {
    :source-path "src-cljs"
    :compiler {
      :output-to "resources/public/js/main.js"
      :pretty-print true}}
  :main cljsdemo.server
  :repl-init script.repl)
