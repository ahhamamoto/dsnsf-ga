(ns dsnsf-ga.core
  (:require [clojure.java.io :as io]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn test
  []
  (let [files ["/Users/manoweng/Documents/Code/github/dsnsf-ga/data/bits-1.txt"
               "/Users/manoweng/Documents/Code/github/dsnsf-ga/data/bits-2.txt"
               "/Users/manoweng/Documents/Code/github/dsnsf-ga/data/bits-3.txt"
               "/Users/manoweng/Documents/Code/github/dsnsf-ga/data/bits-4.txt"]
        data (get-input files)]
    (inferior-limit data)))

(load "utils")

