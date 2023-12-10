(ns learning-clojure.curso_3_alura.aula-04
  (:use [clojure pprint])
  (:require [learning-clojure.curso_3_alura.logic :as l.logic])
  (:require [learning-clojure.curso_3_alura.model :as l.model]))


(defn chega-sem-malvado! [hospital pessoa]
  (swap! hospital l.logic/chega-em :espera pessoa)
  (println "apos inserir" pessoa))

(defn simula-um-dia-em-paralelo-com-mapv []
  (let [hospital (atom (l.model/novo-hospital))
        pessoas [111 222 333 444 555 666]]
    (mapv #(.start (Thread. (fn [] (chega-sem-malvado! hospital %)))) pessoas)
    (.start (Thread. (fn [] (Thread/sleep 7000)
                       (pprint hospital))))
    ))

;(simula-um-dia-em-paralelo-com-mapv)

(defn simula-um-dia-em-paralelo-com-mapv-refatorada []
  (let [hospital (atom (l.model/novo-hospital))
        pessoas [111 222 333 444 555 666]
        start-thread-de-chegada #(.start (Thread. (fn [] (chega-sem-malvado! hospital %))))
        ]
    (mapv start-thread-de-chegada pessoas)
    (.start (Thread. (fn [] (Thread/sleep 7000)
                       (pprint hospital))))
    ))

;(simula-um-dia-em-paralelo-com-mapv-refatorada)

;(defn start-thread-de-chegada
;  ([hospital]
;   (fn [pessoa] (start-thread-de-chegada hospital pessoa)))
;  ([hospital pessoa]
;   (.start (Thread. (fn [] (chega-sem-malvado! hospital pessoa))))))
;
;(defn preparadinha
;  [hospital]
;  (fn [pessoa] (start-thread-de-chegada hospital pessoa)))
;
;(defn simula-um-dia-em-paralelo-com-mapv-extraida []
;  (let [hospital (atom (l.model/novo-hospital))
;        pessoas [111 222 333 444 555 666]]
;
;    (mapv (start-thread-de-chegada hospital) pessoas)
;
;    (.start (Thread. (fn [] (Thread/sleep 7000)
;                       (pprint hospital))))
;    ))

;(simula-um-dia-em-paralelo-com-mapv-extraida)


(defn start-thread-de-chegada
  [hospital pessoa]
  (.start (Thread. (fn [] (chega-sem-malvado! hospital pessoa)))))

(defn simula-um-dia-em-paralelo-com-mapv-extraida-com-partial []
  (let [hospital (atom (l.model/novo-hospital))
        pessoas [111 222 333 444 555 666]
        starta (partial start-thread-de-chegada hospital)]

    (println (mapv starta pessoas))

    (.start (Thread. (fn [] (Thread/sleep 7000)
                       (pprint hospital))))
    ))

;(simula-um-dia-em-paralelo-com-mapv-extraida-com-partial)

(defn start-thread-de-chegada
  [hospital pessoa]
  (.start (Thread. (fn [] (chega-sem-malvado! hospital pessoa)))))

(defn simula-um-dia-em-paralelo-com-mapv-extraida-com-doseq []
  (let [hospital (atom (l.model/novo-hospital))
        pessoas [111 222 333 444 555 666]]

    (doseq [pessoa pessoas]
      (start-thread-de-chegada hospital pessoa))

    (.start (Thread. (fn [] (Thread/sleep 7000)
                       (pprint hospital))))
    ))

;(simula-um-dia-em-paralelo-com-mapv-extraida-com-doseq)

(defn simula-um-dia-em-paralelo-com-mapv-extraida-com-doseq-com-range []
  (let [hospital (atom (l.model/novo-hospital))
        pessoas (range 6)]

    (doseq [pessoa pessoas]
      (start-thread-de-chegada hospital pessoa))

    (.start (Thread. (fn [] (Thread/sleep 7000)
                       (pprint hospital))))
    ))

;(simula-um-dia-em-paralelo-com-mapv-extraida-com-doseq-com-range)

(defn simula-um-dia-em-paralelo-com-mapv-extraida-com-dotimes []
  (let [hospital (atom (l.model/novo-hospital))]

    (dotimes [pessoa 6]
      (start-thread-de-chegada hospital pessoa))

    (.start (Thread. (fn [] (Thread/sleep 7000)
                       (pprint hospital))))
    ))

(simula-um-dia-em-paralelo-com-mapv-extraida-com-dotimes)

