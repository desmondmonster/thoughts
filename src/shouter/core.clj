(ns shouter.core
  (:use [compojure.core :only [defroutes GET]]
        [compojure.route :only [files]])
  (:use [hiccup.page :only [html5]])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as ring]
            [shouter.controllers.shouts]
            [shouter.views.layout :as layout]
            [shouter.assets.css-rules :as css]))


(defroutes routes
  shouter.controllers.shouts/routes
  (route/resources "/")
  (GET "/*" {params :params} (or (files "/public/stylesheets/notes.css" (params :*)) :next))
  (route/not-found (layout/four-oh-four)))

(def application (handler/site routes))

(defn start [port]
  (css/generate)
  (ring/run-jetty #'application {:port (or port 8080) :join? false}))

(defn -main []
  (let [port (Integer. (System/getenv "PORT"))]
    (start port)))


