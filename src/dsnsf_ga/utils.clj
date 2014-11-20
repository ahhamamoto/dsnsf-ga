(in-ns 'dsnsf-ga.core)

(defn average
  "returns average of a list"
  [coll]
  (/ (apply + coll) (count coll)))

(defn rand-float
  "returns random float in range"
  [inf sup]
  (+ inf (rand (- sup inf))))
