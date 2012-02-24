(ns test
  (:use clojure.test)
  (:use midje.sweet))


(defn decimaldigits [n]
  (map #(read-string (str %)) (seq (str n)))
)

(defn double-digits [n]
  (flatten (map decimaldigits (map #(* 2 %) n)))
)

(defn ten-comp [n]
  (mod n 10)
)

(defn isin? [s]
  (let [data (read-string (str "36r0" (butlast s)))
        crc (read-string (str (last s)))
        result (apply + (double-digits (decimaldigits data)))]
    (= (ten-comp result) crc))
)

(facts 
  (decimaldigits 123) => [1,2,3]

  (double-digits [1,2]) => [2, 4]
  (double-digits [1,2,9]) => [2, 4, 1, 8]

  (ten-comp 235) => 5

  (isin? "FR1234567898") => false
;  (isin? "FR1234567899") => true
)


