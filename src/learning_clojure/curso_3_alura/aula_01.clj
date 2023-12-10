(ns learning-clojure.curso_3_alura.aula-01
  (:use [clojure pprint])
  (:require [learning-clojure.curso_3_alura.logic :as l.logic])
  (:require [learning-clojure.curso_3_alura.model :as l.model]))


(defn simula-um-dia []
  (def hospital (l.model/novo-hospital))
  (pprint hospital)
  (def hospital (l.logic/chega-em hospital :espera 111))
  (def hospital (l.logic/chega-em hospital :espera 222))
  (def hospital (l.logic/chega-em hospital :espera 333))
  (def hospital (l.logic/chega-em hospital :laboratorio1 444))
  (def hospital (l.logic/chega-em hospital :laboratorio3 555))
  (pprint hospital)

  (def hospital (l.logic/atende hospital :espera))
  (def hospital (l.logic/atende hospital :laboratorio1))
  (pprint hospital)

  (def hospital (l.logic/chega-em hospital :espera 666))
  (def hospital (l.logic/chega-em hospital :espera 777))
  (def hospital (l.logic/chega-em hospital :espera 888))
  (pprint hospital)
  )

;(simula-um-dia)

;(defn simula-um-dia-em-paralelo []
;  (def hospital (l.model/novo-hospital))
;  (.start (Thread. (fn [] (println 111))))
;  (.start (Thread. (fn [] (println 222))))
;  (.start (Thread. (fn [] (println 333))))
;  (.start (Thread. (fn [] (println 444))))
;  (.start (Thread. (fn [] (println 555))))
;  (.start (Thread. (fn [] (println 666))))
;  )
;
;(simula-um-dia-em-paralelo)

(defn chega-em-malvado
  [pessoa]
  ;(Thread/sleep 1000)
  (def hospital (l.logic/chega-em-pausado hospital :espera pessoa))
  (println "apos inserir" pessoa))

(defn simula-um-dia-em-paralelo []
  (def hospital (l.model/novo-hospital))
  (.start (Thread. (fn [] (chega-em-malvado 111))))
  (.start (Thread. (fn [] (chega-em-malvado 222))))
  (.start (Thread. (fn [] (chega-em-malvado 333))))
  (.start (Thread. (fn [] (chega-em-malvado 444))))
  (.start (Thread. (fn [] (chega-em-malvado 555))))
  (.start (Thread. (fn [] (chega-em-malvado 666))))

  (.start (Thread. (fn [] (Thread/sleep 4000)
                           (pprint hospital))))

  )

(simula-um-dia-em-paralelo)