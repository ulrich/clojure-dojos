(ns quicksort-test
  (:use clojure.test)
  (:use midje.sweet))

(defn qs [items]
  (if (or (nil? items) (empty? items)) []
  (let [pivot (first items)
	partitions (group-by (fn [item] (< item pivot)) (next items))]
  (flatten [(qs (partitions true)) pivot (qs (partitions false))]))))

(facts (qs []) => []
	(qs [1]) => [1]
	(qs [2 1]) => [1 2]
	(qs [1 2 1]) => [1 1 2]
	(qs [1 1 1]) => [1 1 1])

