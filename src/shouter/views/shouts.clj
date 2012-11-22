(ns shouter.views.shouts
  (:use [hiccup.core :only [html h]]
        [hiccup.page :only [doctype]]
        [hiccup.form :only [form-to label text-area hidden-field submit-button]]
        [hiccup.element :only [link-to]]
        [clojure.string :only (join)])
  (:require [shouter.views.layout :as layout]))

(defn shout-form []
  [:div {:id "shout-form" :class "sixteen columns alpha omega" }
   (form-to [:post "/"]
            (label "shout" "What do you want to shout?")
            (text-area "shout")
            (submit-button "shout!"))])

(defn display-shouts[shouts]
  [:div {:id "shouts sixteen columns alpha omega" }
   [:table
     (map
       (fn [shout] [:tr
                    [:td {:class "shout"} (h (:content shout))]
                    [:td {:class "shout-timestamp"} (h (:created_at shout))]])
       shouts)]])

(defn index [shouts]
  (layout/common "SHOUTER"
                 (shout-form)
                 [:div {:class "clear"}]
                 (display-shouts shouts)))
