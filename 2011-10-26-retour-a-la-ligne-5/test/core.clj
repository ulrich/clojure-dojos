(ns core
  (:use [clojure.test])
  (:use midje.sweet)
 )

(defn cut-index [phrase longueur]
	(let [pc (drop-while #(not (= \space %)) (reverse (take (inc longueur) phrase)))]
	(if (empty? pc)
	longueur
	(count pc))))

(defn cesure [phrase longueur]
	(loop [phrase phrase longueur longueur resultat []]
	    (letfn [(to-trimmed-str [seqs] (.trim (apply str seqs)))]
	    	(let [cindex (cut-index phrase longueur)
	       		reste (drop cindex phrase)
	        	ligne (to-trimmed-str (take cindex phrase))]
	    
			(if (empty? phrase)
				resultat
				(recur reste longueur (conj resultat ligne)))))))

(fact (cesure "c" 1) => ["c"])
(fact (cesure "ca" 1) => ["c" "a"])
(fact (cesure "c a" 1) => ["c" "a"])
(fact (cesure "c a" 2) => ["c" "a"])
(fact (cesure "c a t" 1) => ["c" "a" "t"])
(fact (cesure "ceci a aaaaaa" 8) => ["ceci a" "aaaaaa"])
(fact (cesure "ceci a aaaaaa" 6) => ["ceci a" "aaaaaa"])
(fact (cesure "cecia aaaaaa" 6) => ["cecia" "aaaaaa"])
(fact (cesure "ceci est un texte" 6) => ["ceci" "est un" "texte"])
(fact (cesure "ceci est  un texte" 6) => ["ceci" "est" "un" "texte"])
(fact (cesure "ceciestun texte" 6) => ["cecies" "tun" "texte"])
