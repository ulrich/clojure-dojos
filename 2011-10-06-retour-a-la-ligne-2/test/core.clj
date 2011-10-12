(ns core
  (:use [clojure.test])
  (:use midje.sweet)
 )

(def words (partial clojure.contrib.string/split #" +"))

(defn line [limit [w & ws]]
	(if (or (nil? w)
	      (< limit (count w)))
		[]
		(cons w (line (- limit 1 (count w)) ws)))
		)

(fact
	(words "une") => ["une"]
	(words "une deux") => ["une", "deux"]
	(words "une  deux ") => ["une", "deux"]
	)

(fact
	(line 10 ["foo", "bar"]) => ["foo" "bar"]
	)

(fact
	(line 6  ["mot", "mot"]) => ["mot"] 
	)
