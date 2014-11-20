(ns dsnsf-ga.core
  (:gen-class)
  (:require [clojure.java.io :as io]))

(load "utils")

(defn create-chromosome
  "creates a chromosome based on limits and input"
  ([inf sup input]
     (let [value (rand-float inf sup)]
       {:value value :fitness (euclidian-distance value input)}))
  ([value input]
     {:value value :fitness (euclidian-distance value input)}))

(defn generate-point-population
  "generates the population of a single point"
  [size inf sup input]
  (repeatedly size #(create-chromosome inf sup input)))

(defn generate-population
  "generates the whole population"
  [size inf sup input]
  (map #(generate-point-population size %1 %2 %3) inf sup input))

(defn tournament-selection
  "selection by tournament"
  [point-population]
  (let [chr1 (nth point-population (rand-int (count point-population)))
        chr2 (nth point-population (rand-int (count point-population)))]
    (if (> (:fitness chr1) (:fitness chr2))
      chr1
      chr2)))

(defn crossover
  "crossover between two chromosomes"
  [chr1 chr2 input]
  (create-chromosome (/ (+ (:value chr1) (:value chr2)) 2) input))

(defn mutate
  "mutates a single chromosome"
  [chr input]
  (let [value (rand-float 0 1)]
    (cond
     (< value 0.05) (create-chromosome (- (:value chr) (* 0.05 (:value chr))) input)
     (< value 0.10) (create-chromosome (+ (:value chr) (* 0.05 (:value chr))) input)
     :else chr)))

(defn evolve
  "evolves a generation"
  [point-population input size]
  (repeatedly size #(mutate (crossover (tournament-selection point-population)
                                       (tournament-selection point-population)
                                       input) input)))

(defn optimize
  "optmizes a point"
  [point-population input generations size]
  (repeatedly generations #(evolve point-population input size)))

(defn optimize-all
  [data generations size]
  (map #(optimize %1 %2 generations size)
       (generate-population size
                            (inferior-limit data)
                            (superior-limit data)
                            data)
       data))

(defn get-best-chromosome
  [point-population]
  (nth (sort-by :value point-population) 0))

(defn assemble-best-chromosomes
  [population]
  (map #(get-best-chromosome %) population))

(defn -main
  [& {:keys [population-size generations]
      :or {population-size 20 generations 50}}]
  (let [files ["/Users/manoweng/Documents/Code/github/dsnsf-ga/data/bits-1.txt"
               "/Users/manoweng/Documents/Code/github/dsnsf-ga/data/bits-2.txt"
               "/Users/manoweng/Documents/Code/github/dsnsf-ga/data/bits-3.txt"
               "/Users/manoweng/Documents/Code/github/dsnsf-ga/data/bits-4.txt"]
        data (get-input files)
        dsnsf (assemble-best-chromosomes
               (optimize-all data generations population-size))]
    (println "Starting to run Genetic Algorithm")
    (println dsnsf)
    (save-dsnsf-ga (nth dsnsf 0) "/Users/manoweng/Documents/Code/github/dsnsf-ga/data/dsnsf.txt")
    (println "Done!")))
