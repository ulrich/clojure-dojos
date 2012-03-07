(ns test
  (:use clojure.test)
  (:use midje.sweet))


(defn subsequences [s]
  (if (empty? s) [[]]
      (let [subseqs (subsequences (rest s))]
        (concat (map #(cons (first s) %) subseqs) 
                subseqs))))

(defn compte [[elements] cible]
  (let [seqs (subsequences elements)]
     (first (filter #(= cible (second %)) (map vector seqs  (map (apply +) seqs))))))

(facts 
  (subsequences []) => [[]]
  (subsequences [1]) => [[1] []]
  (subsequences [1 2]) => [[1 2] [1] [2] []]
  (subsequences [1 2 3]) => [[1 2 3] [1 2] [1 3] [1] [2 3] [2] [3] []]
)

;(facts
;  (compte [1] 1) => [1]
;  (compte [2] 2) => [2]
;  (compte [1 1] 1) => [1]
;  (compte [1 2] 2) => [2]
;  (compte [1 1] 2) => [1 1]
;  (compte [2 1] 2) => [2]
;  (compte [1 1 3] 2) => [1 1]
;  (compte [1 1 3 3] 2) => [1 1]
;  (compte [1 3 1] 2) => [1 1]
;)

	
