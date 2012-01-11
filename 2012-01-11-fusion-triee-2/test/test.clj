(ns test
  (:use clojure.test)
  (:use midje.sweet))

(defn count-all [cols]
  (reduce + (map count cols))
)

(defn remove-element [elem col]
 (if (= elem (first col))
     (rest col)
     col))

(defn remove-one-element [cols elem]
 (remove empty? (map (partial remove-element elem) cols)))

(defn fusion [args]
    (let [smallest (first (sort (map first args)))
          times (count (filter #(= smallest (first %)) args))
          remainer (remove-one-element args smallest)]
      (cond 
         (= 0 (count args)) []
         (= 1 (count args)) (first args)
         :otherwise (concat (repeat times smallest) (fusion remainer)))))

(facts 
  (fusion [[1]]) => [1]
  (fusion [[0]]) => [0]
  (fusion [[0] [1]]) => [0 1]
  (fusion [[1] [0]]) => [0 1]
  (fusion [[0] [1] [2]]) => [0 1 2]
  (fusion [[0 1]]) => [0 1]
  (fusion [[0 1] [2]]) => [0 1 2]
  (fusion [[0] [0]]) => [0 0]
  (fusion [[0 1 3] [2 5] [4 5 7 8]]) => [0 1 2 3 4 5 5 7 8]
  (take 4 (fusion [(range) (range)])) => [0 0 1 1]
)

