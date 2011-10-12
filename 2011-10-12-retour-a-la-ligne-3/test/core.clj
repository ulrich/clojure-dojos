(ns core
  (:use [clojure.test])
  (:use midje.sweet)
 )

(defn filtre [seqs]
	(filter #(not (empty? %)) seqs))

(defn trimmed-str [seqs]
	(.trim (apply str seqs)))

(defn cesure [phrase longueur]
	(if (empty? phrase)
		[]
		(filtre (cons (trimmed-str (take longueur phrase)) (cesure (drop longueur phrase) longueur)))))

(fact (cesure "c" 1) => ["c"])
(fact (cesure "ca" 1) => ["c" "a"])
(fact (cesure "c a" 1) => ["c" "a"])
(fact (cesure "c a" 2) => ["c" "a"])
(fact (cesure "c a t" 1) => ["c" "a" "t"])
(fact (cesure "ceci est un texte" 6) => ["ceci" "est un" "texte"])

;; "ceci est un texte" 6 => "ceci" "est un" "texte"