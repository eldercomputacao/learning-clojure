(ns learning-clojure.curso_3_alura.colecoes
  (:use [clojure pprint]))

(defn teste-vetor []
  (let [espera [111 222]]
    (println "vetor")
    (println espera)
    (println (conj espera 333))
    (println (conj espera 444))
    (println (pop espera))))
(teste-vetor)

(defn teste-lista []
  (let [espera '(111 222)]
    (println "lista")
    (println espera)
    (println (conj espera 333))
    (println (conj espera 444))
    (println (pop espera))))
(teste-lista)

(defn teste-conjunto []
  (let [espera #{111 222}]
    (println "conjunto")
    (println espera)
    (println (conj espera 111))
    (println (conj espera 222))
    ;(println (pop espera))
    ))
(teste-conjunto)

(defn teste-fila []
  (let [espera (conj clojure.lang.PersistentQueue/EMPTY 111 222)]
    (println "fila")
    (println espera)
    (pprint espera)
    (println (seq espera))
    (println (seq (conj espera 333)))
    (println (seq (pop espera)))
    (println (peek espera))
    ;(println (pop espera))
    ))

(teste-fila)
