(ns test
  (:use clojure.test)
  (:use midje.sweet))

(defn fusion [args]
    (let [smallest (first (sort (map first args)))
  remainer (filter #(not (= smallest (first %1))) args)]
    (if (empty? args)
    []
    (concat [smallest] (fusion remainer)))))

(facts 
  (fusion [[1]]) => [1]
  (fusion [[0]]) => [0]
  (fusion [[0] [1]]) => [0 1]
  (fusion [[1] [0]]) => [0 1]
  (fusion [[0] [1] [2]]) => [0 1 2])

