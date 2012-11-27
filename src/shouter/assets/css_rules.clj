(ns shouter.assets.css-rules
  (:refer-clojure :exclude [+ - * /])
  (:use cssgen clojure.algo.generic.arithmetic))

(defn generate []
  (spit "/Users/desmond/code/shouter/src/public/stylesheets/notes.css"  ;this is the path to the target CSS file
    (css
      ["body"
        :color :black
        :background-color :#ddd
        :padding [:1px "2px" (px 3) 0]]
      ["shout"
        :text-transform :uppercase]
      )))
