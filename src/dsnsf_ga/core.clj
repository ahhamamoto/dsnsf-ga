(ns dsnsf-ga.core
  (:require [clojure.java.io :as io]))

(defn create-chromosome
  [inf sup input]
  (let [value (rand-float inf sup)]
    {:value value :fitness (euclidian-distance value input)}))

(defn generate-point-population
  [size inf sup input]
  (repeatedly size #(create-chromosome inf sup input)))

(defn generate-population
  [size inf sup input]
  (map #(generate-point-population size %1 %2 %3) inf sup input))

(defn test
  []
  (let [files ["/Users/manoweng/Documents/Code/github/dsnsf-ga/data/bits-1.txt"
               "/Users/manoweng/Documents/Code/github/dsnsf-ga/data/bits-2.txt"
               "/Users/manoweng/Documents/Code/github/dsnsf-ga/data/bits-3.txt"
               "/Users/manoweng/Documents/Code/github/dsnsf-ga/data/bits-4.txt"]
        data (get-input files)
        inf (inferior-limit data)
        sup (superior-limit data)]))

(load "utils")

