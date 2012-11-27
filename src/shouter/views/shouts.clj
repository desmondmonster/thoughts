(ns shouter.views.shouts
  (:use [hiccup.core :only [html h]]
        [hiccup.page :only [doctype]]
        [hiccup.form :only [form-to label text-area hidden-field submit-button]]
        [hiccup.element :only [link-to]]
        [clojure.string :only (join)])
  (:require [shouter.views.layout :as layout]))

(defn shout-form []
  [:div {:id "shout-form"}
   (form-to [:post "/create"]
            (label "shout" "What do you want to shout?")
            (text-area "shout")
            (submit-button "shout!"))])

(defn hider [shout]
  (form-to [:post "/hide"]
           (hidden-field "shout" (:id shout))
           (submit-button "hide")))

(defn display-shouts[shouts]
  [:div {:id "shouts" }
   [:table
     (map
       (fn [shout] [:tr
                    [:td {:class "shout"} (h (:content shout))]
                    [:td {:class "shout-timestamp"} (h (:created_at shout))]
                    [:td {:class "hide-link"} (hider shout)]])
       shouts)]])

(defn index [shouts]
  (layout/common "list of notes"
                 [:div {:class "clear"}]
                 (display-shouts shouts)))

(defn new-note []
  (layout/common "add something"
                 (shout-form)))
