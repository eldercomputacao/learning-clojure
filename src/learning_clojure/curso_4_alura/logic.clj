(ns learning-clojure.curso-4-alura.logic
  (:require [learning-clojure.curso-4-alura.model :as l.model]))

(defn agora []
  (l.model/to-ms (java.util.Date.)))