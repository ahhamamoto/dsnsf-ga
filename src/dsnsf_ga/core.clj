(ns dsnsf-ga.core
  (:require [clojure.java.io :as io]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn test
  []
  (average (cast-to-int (read-data "/Users/manoweng/Documents/Code/github/dsnsf-ga/data/data.txt"))))

(load "utils")

