(ns learning-clojure.curso_3_alura.aula-05
  (:use [clojure pprint])
  (:require [learning-clojure.curso_3_alura.logic :as l.logic])
  (:require [learning-clojure.curso_3_alura.model :as l.model]))

(defn chega-em! [hospital pessoa]
  (swap! hospital l.logic/chega-em :espera pessoa))

(defn tranfere! [hospital de para]
  (swap! hospital l.logic/transfere de para))

(defn simula-um-dia []
  (let [hospital (atom (l.model/novo-hospital))]
    (chega-em! hospital "joao")
    (chega-em! hospital "maria")
    (chega-em! hospital "daniela")
    (chega-em! hospital "guilherme")
    (tranfere! hospital :espera :laboratorio1)
    (tranfere! hospital :espera :laboratorio2)
    (tranfere! hospital :espera :laboratorio3)
    (tranfere! hospital :espera :laboratorio3)
    (pprint hospital)
    )
  )

(simula-um-dia)
