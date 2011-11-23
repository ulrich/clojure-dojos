(ns test
  (:use clojure.test)
  (:use midje.sweet))

(defn div3 [n] (if (zero? (mod n 3)) "Foo" ""))
(defn div5 [n] (if (zero? (mod n 5)) "Bar" ""))
(defn div7 [n] (if (zero? (mod n 7)) "Qix" ""))
(defn diviseurs [n] (str (div3 n) (div5 n) (div7 n)))

(defn composants2
  ([digits]
  (cond
    (empty? digits) ""
    (= \3 (first digits)) (str "Foo" (composants2 (rest digits)))
    (= \5 (first digits)) (str "Bar" (composants2 (rest digits)))
    (= \7 (first digits)) (str "Qix" (composants2 (rest digits)))
    :else (composants2 (rest digits))
)))

(defn composants 
  ([n] (composants2 (seq (str n)))))

(defn foobarqix [n]
  (let [res (str (diviseurs n) (composants n))]
    (if (empty? res) (str n) res)
  ))

(facts 
  (map foobarqix (range 1 101)) => ""
)

(facts 
  (foobarqix 1) => "1"
  (foobarqix 2) => "2"
  (foobarqix 6) => "Foo"
  (foobarqix 10) => "Bar"
  (foobarqix 14) => "Qix"
  (foobarqix 60) => "FooBar"
  (foobarqix 33) => "FooFooFoo"
)

(facts 
  (composants 1) => ""
  (composants 3) => "Foo"
  (composants 5) => "Bar"
  (composants 7) => "Qix"
  (composants 33) => "FooFoo"
)
