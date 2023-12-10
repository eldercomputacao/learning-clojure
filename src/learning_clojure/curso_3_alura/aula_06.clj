(ns learning-clojure.curso_3_alura.aula-06
  (:use [clojure pprint])
  (:require [learning-clojure.curso_3_alura.logic :as l.logic])
  (:require [learning-clojure.curso_3_alura.model :as l.model]))

(defn cabe?
  [fila]
  (-> fila
      count
      (<,,, 5)))

(defn chega-em
  [fila pessoa]
  (conj fila pessoa))

(defn chega-em
  [fila pessoa]
  (if (cabe? fila)
    (conj fila pessoa)
    (throw (ex-info "fila esta cheia" {}))))

(defn chega-em!
  [hospital pessoa]
  (let [fila (get hospital :espera)]
    (ref-set fila (chega-em @fila pessoa))))

(defn chega-em!
  [hospital pessoa]
  (let [fila (get hospital :espera)]
    (dosync
      (ref-set fila (chega-em @fila pessoa)))))

(defn chega-em!
  [hospital pessoa]
  (let [fila (get hospital :espera)]
    (alter fila chega-em pessoa)))

(defn simula-um-dia []
  (let [hospital {:espera       (ref l.model/fila-vazia)
                  :laboratorio1 (ref l.model/fila-vazia)
                  :laboratorio2 (ref l.model/fila-vazia)
                  :laboratorio3 (ref l.model/fila-vazia)
                  }]
    (pprint hospital)
    (dosync
      (chega-em! hospital "guilherme")
      (chega-em! hospital "maria")
      (chega-em! hospital "lucia")
      (chega-em! hospital "daniela")
      (chega-em! hospital "ana")
      ;(chega-em! hospital "paulo")

      )
    (pprint hospital)
    )
  )

;(simula-um-dia)

(defn async-chega-em!
  [hospital pessoa]
  (future
    (Thread/sleep (rand 5000))
    (dosync
      (println "tentando codigo sincronizado" pessoa)
      (chega-em! hospital pessoa))))


(defn simula-um-dia-async []
  (let [hospital {:espera       (ref l.model/fila-vazia)
                  :laboratorio1 (ref l.model/fila-vazia)
                  :laboratorio2 (ref l.model/fila-vazia)
                  :laboratorio3 (ref l.model/fila-vazia)
                  }]
    (pprint hospital)

    (dotimes [pessoa 10]
      (async-chega-em! hospital pessoa))

    (future
      (Thread/sleep 8000)
      (pprint hospital))
    )
  )

(simula-um-dia-async)

;(println (future 15))
;(println (future ((Thread/sleep 1000) 15)))
