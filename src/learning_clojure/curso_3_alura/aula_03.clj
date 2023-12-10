(ns learning-clojure.curso_3_alura.aula-03
  (:use [clojure pprint])
  (:require [learning-clojure.curso_3_alura.logic :as l.logic])
  (:require [learning-clojure.curso_3_alura.model :as l.model]))

(defn teste-atomo []
  (let [hospital-elder (atom { :espera l.model/fila-vazia })]
    (println hospital-elder)
    (pprint hospital-elder)
    (pprint (deref hospital-elder))
    (pprint @hospital-elder)

    ;(pprint (assoc @hospital-elder :laboratorio1 l.model/fila-vazia))
    ;(pprint @hospital-elder)

    (swap! hospital-elder assoc :laboratorio1 l.model/fila-vazia)
    (swap! hospital-elder assoc :laboratorio2 l.model/fila-vazia)
    (pprint @hospital-elder)
    (swap! hospital-elder update :laboratorio1 conj 111)
    (pprint hospital-elder)

    ))

;(teste-atomo)


(defn chega-em-malvado! [hospital pessoa]
  (swap! hospital l.logic/chega-em-pausado-logando :espera pessoa)
  (println "apos inserir" pessoa))

(defn simula-um-dia-em-paralelo []
  (let [hospital (atom (l.model/novo-hospital))]
    (.start (Thread. (fn [] (chega-em-malvado! hospital 111))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital 222))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital 333))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital 444))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital 555))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital 666))))
    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))
    ))

;(simula-um-dia-em-paralelo)

(defn chega-sem-malvado! [hospital pessoa]
  (swap! hospital l.logic/chega-em :espera pessoa)
  (println "apos inserir" pessoa))

(defn simula-um-dia-em-paralelo []
  (let [hospital (atom (l.model/novo-hospital))]
    (.start (Thread. (fn [] (chega-sem-malvado! hospital 111))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital 222))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital 333))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital 444))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital 555))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital 666))))
    (.start (Thread. (fn [] (Thread/sleep 1000)
                       (pprint hospital))))
    ))

(simula-um-dia-em-paralelo)


