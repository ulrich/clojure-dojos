(ns core
  (:use [clojure.test])
  (:use midje.sweet)
 )

(defn retour-a-la-ligne [phrase taille]
	(let [words (vec (clojure.contrib.string/split #" " phrase))]
		(if (< taille (count phrase))
		  words
		  (cons (first words) (second words))
		)
	)
)

(fact (retour-a-la-ligne "" 1 ) => [""])
(fact (retour-a-la-ligne "a" 1 ) => ["a"])
(fact (retour-a-la-ligne "a b" 1 ) => ["a" "b"])
(fact (retour-a-la-ligne "a b" 3 ) => ["a b"])
;;(fact (retour-a-la-ligne "a b c" 3 ) => ["a b" "c"] )

;; (retour-a-la-ligne "ceci est un exemple" 8) => ["ceci est" "un" "exemple"]
;; (retour-a-la-ligne "anticonstitutionellement" 8) => ["anticons" "titution" "ellement"]