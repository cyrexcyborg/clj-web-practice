(ns practice1.core)
(require '[org.httpkit.server :as http] )
(require '[ring.util.response :as r])
(require '[bidi.ring :refer (make-handler)])


(+ 2 2)

(defn app [req]
  r/response
  {:status 200
   :header {"Content-type" "text/html"}
   :body "Hello http-kit!"})

(defonce server (atom nil))
(def handler (make-handler ["/hello" app]))


(defn stop-server []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)))

(defn start-server [port]
  (reset! server (http/run-server #'handler {:port port})))



