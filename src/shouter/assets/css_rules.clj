(ns shouter.assets.css-rules
  (:refer-clojure :exclude [+ - * /])
  (:use cssgen clojure.algo.generic.arithmetic))

(def output-path
  "/Users/desmond/code/shouter/src/public/stylesheets/notes.css")

(defn generate []
  (spit output-path
    (css
      ["body"
        :color :black
        :background-color :#ddd
        :padding [:1px "2px" (px 3) 0]]
      ["td"
        :font-family ["Cardo" "Georgia" "Times" "serif"]
        :font-size (px 18)
        :line-height (px 25)]
      )))
