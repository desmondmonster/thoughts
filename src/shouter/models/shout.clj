(ns shouter.models.shout
  (:require [clojure.java.jdbc :as sql]))

(def db-settings {:subprotocol "postgresql"
                  :subname "//localhost:5432/notes_app_development"
                  :user (System/getenv "NOTES_APP_USER")})

(defn all []
  (sql/with-connection db-settings
    (sql/with-query-results results
      ["select * from notes order by created_at desc"]
        (into [] results))))

(defn create [shout]
  (sql/with-connection "postgresql://localhost:5432/notes_app_development"
    (sql/insert-values :notes [:content] [shout])))

