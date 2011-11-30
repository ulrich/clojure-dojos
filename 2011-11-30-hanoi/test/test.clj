(ns test
  (:use clojure.test)
  (:use midje.sweet))


(defn hanoi 
  ([n] (hanoi n "A" "B" "C"))
  ([n a b c]
    (if (= 1 n)
      [(str a c)]
      (concat (hanoi (- n 1) a c b) (hanoi 1 a b c) (hanoi (- n 1) b a c)))))

(facts 
  (hanoi 1) => ["AC"]
  (hanoi 2) => ["AB" "AC" "BC"]
  (hanoi 3) => ["AC" "AB" "CB" "AC" "BA" "BC" "AC"])


