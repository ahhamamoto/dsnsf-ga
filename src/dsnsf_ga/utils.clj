(in-ns 'dsnsf-ga.core)

(defn average
  "returns average of a list"
  [coll]
  (/ (apply + coll) (count coll)))

(defn rand-float
  "returns random float in range"
  [inf sup]
  (+ inf (rand (- sup inf))))

(defn cast-to-float
  "casts all elements in list to integer"
  [coll]
  (map #(Float/parseFloat %) coll))

(defn read-data
  "returns a list of strings, each element is the content of a line"
  [file]
  (cast-to-float
   (with-open [rdr (io/reader file)]
     (doall (line-seq rdr)))))

(defn pre-process
  "gets the average of partitions"
  [data & {:keys [time-window] :or {time-window 300}}]
  (map #(average %) (partition time-window data)))

(defn read-all-data
  "reads and preprocesses all input files"
  [files]
  (map #(pre-process (read-data %)) files))

(defn transpose
  "transposes matrix"
  [data]
  (apply map list data))

(defn get-input
  "returns the preprocessed and transposed input"
  [files]
  (transpose (read-all-data files)))

(defn inferior-limit
  "returns inferior limit from input"
  [data]
  (map #(apply min %) data))

(defn superior-limit
  "returns superior limit from input"
  [data]
  (map #(apply max %) data))

(defn euclidian-distance
  "calculates the euclidian distance"
  [value input]
  (Math/sqrt (apply + (map #(* % %) (map #(- value %) input)))))

(defn save-dsnsf-ga
  "saves the dsnsf generated into a file"
  [dsnsf filename]
  (with-open [wtr (io/writer filename)]
    (doseq [point (map #(:value %) dsnsf)]
      (.write wtr (str point "\n")))))
