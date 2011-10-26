(ns core
  (:use [clojure.test])
  (:use midje.sweet)
 )

(defn filtre [seqs]
	(filter #(not (empty? %)) seqs))

(defn trimmed-str [seqs]
	(.trim (apply str seqs)))

(defn cut-index [phrase longueur]
	(let [pc (drop-while #(not (= \space %)) (reverse (take longueur phrase)))]
	(if (empty? pc)
	longueur
	(count pc))))


(defn cesure [phrase longueur]
	(if (empty? phrase)
		[]
		(filtre (cons (trimmed-str (take (cut-index phrase longueur) phrase)) (cesure (drop (cut-index phrase longueur) phrase) (cut-index phrase longueur))))))

(fact (cesure "c" 1) => ["c"])
(fact (cesure "ca" 1) => ["c" "a"])
(fact (cesure "c a" 1) => ["c" "a"])
(fact (cesure "c a" 2) => ["c" "a"])
(fact (cesure "c a t" 1) => ["c" "a" "t"])
(fact (cesure "ceci a aaaaaa" 8) => ["ceci a" "aaaaaa"])
(fact (cesure "ceci a aaaaaa" 6) => ["ceci a" "aaaaaa"])

;; "ceci est un texte" 6 => "ceci" "est un" "texte"