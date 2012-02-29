(ns test
  (:use clojure.test)
  (:use midje.sweet))

(defn compte [[head & tail :as elements] cible]
  (cond
    (empty? elements) []
    (= (apply + elements) cible) elements
    (and (>= (count elements) 3) (= (+ head (first (rest tail))) cible)) [head (first (rest tail))]
    :else 
     (let 
       [solution (compte (butlast elements) cible)] 
       (if (= (apply + solution) cible) 
           solution
           (compte tail cible)))))

(facts
  (compte [1] 1) => [1]
  (compte [2] 2) => [2]
  (compte [1 1] 1) => [1]
  (compte [1 2] 2) => [2]
  (compte [1 1] 2) => [1 1]
  (compte [2 1] 2) => [2]
  (compte [1 1 3] 2) => [1 1]
  (compte [1 1 3 3] 2) => [1 1]
  (compte [1 3 1] 2) => [1 1]
)

	
