# ClojureScript Demo App

This is a demonstration application showing how easy it is to build a web application in top-to-bottom Clojure & ClojureScript. In addition to Clojure/ClojureScript, this project uses [Aleph](https://github.com/ztellman/aleph) and [JayQ](https://github.com/ibdknox/jayq).

Originally presented by Scott Parker at [Chicago Clojure](http://www.meetup.com/ChicagoClj/) on April 24, 2012

## Usage

There's not much to see just yet, but do the following (note that if you want this to run on a different port than port 80, you'll currently need to change that in `src/cljsdemo/server.clj`):

```
lein deps
lein cljsbuild once
lein run
open http://localhost/index.html
```

## License

Distributed under the Eclipse Public License, the same as Clojure.
