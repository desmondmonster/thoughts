(ns shouter.controllers.shouts
  (:use [compojure.core :only [defroutes GET POST]])
  (:require [clojure.string :as str]
            [ring.util.response :as ring]
            [shouter.views.shouts :as view]
            [shouter.models.shout :as model]))

(defn index []
  (view/index (model/visible)))

(defn new-note []
  (view/new-note))

(defn create [params]
  (let [shout (:shout params)]
    (when-not (str/blank? shout)
      (println "creating shout" + shout)
      (model/create shout)))
  (ring/redirect "/"))

(defn hide [params]
  (let [id (:shout params)]
    (model/hide id))
  (ring/redirect "/"))

(defroutes routes
           (GET "/" [] (index))
           (GET "/notes" [] (index))
           (GET "/n" [] (new-note))
           (POST "/create" {params :params} (create params))
           (POST "/hide" {params :params} (hide params)))


