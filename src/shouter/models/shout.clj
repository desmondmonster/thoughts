(ns shouter.models.shout
  (:require [clojure.java.jdbc :as sql]))

(def db-settings {:subprotocol "postgresql"
                  :subname "//localhost:5432/notes_app_development"
                  :user (System/getenv "NOTES_APP_USER")})

(def now
  (java.sql.Timestamp. (.getTime (java.util.Date.))))

(defn to-int [str]
  (Integer. str))

(defn visible []
  (sql/with-connection db-settings
    (sql/with-query-results results
      ["select * from notes WHERE hidden = false order by created_at desc"]
        (into [] results))))

(defn all []
  (sql/with-connection db-settings
    (sql/with-query-results results
      ["select * from notes order by created_at desc"]
        (into [] results))))

(defn hide [id]
  (sql/with-connection db-settings
    (sql/update-values :notes ["id=?" (to-int id)] {:hidden true})))

(defn create [shout]
  (sql/with-connection db-settings
    (sql/insert-values :notes [:content :created_at :updated_at] [shout now now])))

