(ns test
  (:use clojure.test)
  (:use midje.sweet))

(defn sublist [nombres]
   (let [sub (conj (map list nombres) nombres)
	 pair (butlast nombres)
	]
   (if (= 1 (count nombres)) 
      sub
      (conj sub pair)))
)

(defn compte2 [seqs cible]
  (if (empty? seqs)
  []
  (if (= (apply + (first seqs)) cible)
     (first seqs)
     (compte2 (rest seqs) cible)))
)

(defn compte [nombres cible]
  (let [seqs (sublist nombres)]
  (compte2 seqs cible)
))


(facts 
  (compte [1] 1) => [1]
  (compte [1 2] 1) => [1]
  (compte [1 2] 2) => [2]
  (compte [1 1] 2) => [1 1]
  (compte [1 1 1] 2) => [1 1]
  (compte [1 1 1 1] 3) => [1 1 1]
;  (compte [1 1 1 1] 2) => [1 1]
)


