(ns test
  (:use clojure.test)
  (:use midje.sweet))

(defn floodFill [] 1)

(defn floodFill-internal [bitmap ligne colonne color replaced-color]
    (if (= replaced-color (get-in bitmap [ligne colonne]))
        (floodFill ligne colonne color bitmap)
        bitmap)
)

(defn floodFill [ligne colonne color bitmap ]

    (let [replaced-color (get-in bitmap [ligne colonne])
          new-bitmap (assoc-in bitmap [ligne colonne] color)]
       (reduce #(floodFill-internal %1 ligne (%2 colonne) color replaced-color) new-bitmap [inc dec])
)
)

(facts
  (floodFill 0 0 1 [[0]] ) => [[1]]
  (floodFill 0 0 2 [[0]] ) => [[2]]
  (floodFill 0 0 2 [[0 1]] ) => [[2 1]]
  (floodFill 0 1 2 [[0 1]] ) => [[0 2]]
  (floodFill 0 0 2 [[0][1]] ) => [[2] [1]]
  (floodFill 1 0 2 [[0][1]] ) => [[0] [2]]

  (floodFill 0 0 2 [[0 0]] ) => [[2 2]]
  (floodFill 0 0 2 [[1 1]] ) => [[2 2]]
  (floodFill 0 1 2 [[0 0]] ) => [[2 2]]
)


