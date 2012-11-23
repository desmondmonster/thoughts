(ns shouter.models.shout
  (:require [clojure.java.jdbc :as sql]))

(def db {:subprotocol "postgresql"
                  :subname "//localhost:5432/notes_app_development"
                  :user (System/getenv "NOTES_APP_USER")})

(defn now []
  (java.sql.Timestamp. (.getTime (java.util.Date.))))

(defn to-int [str]
  (Integer. str))

(defn with-updated-at [attrs]
  (merge attrs {:updated_at (now)}))

(defn visible []
  (sql/with-connection db
    (sql/with-query-results results
      ["select * from notes WHERE hidden = false order by created_at desc"]
        (into [] results))))

(defn all []
  (sql/with-connection db
    (sql/with-query-results results
      ["select * from notes order by created_at desc"]
        (into [] results))))

(defn update-note [id attr-map]
  (sql/with-connection db
    (sql/update-values :notes ["id=?" (to-int id)] (with-updated-at attr-map))))

(defn hide [id]
  (update-note id {:hidden true}))

(defn create [shout]
  (sql/with-connection db
    (sql/insert-values :notes [:content :created_at :updated_at] [shout now now])))

