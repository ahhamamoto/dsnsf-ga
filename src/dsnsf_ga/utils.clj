(in-ns 'dsnsf-ga.core)

(defn average
  "returns average of a list"
  [coll]
  (/ (apply + coll) (count coll)))

(defn rand-float
  "returns random float in range"
  [inf sup]
  (+ inf (rand (- sup inf))))

(defn read-data
  "returns a list of strings, each element is the content of a line"
  [file]
  (with-open [rdr (io/reader file)]
    (doall (line-seq rdr))))

(defn cast-to-int
  "casts all elements in list to interger"
  [coll]
  (map #(Integer/parseInt %) coll))
