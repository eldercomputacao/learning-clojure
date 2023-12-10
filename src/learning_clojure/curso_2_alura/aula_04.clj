(ns learning-clojure.curso-2-alura.aula-04
  (:require [learning-clojure.curso-2-alura.db :as l.db]
            [learning-clojure.curso-2-alura.logic :as l.logic]))

;(println (l.db/todos-os-pedidos))

(let [pedidos (l.db/todos-os-pedidos)
      resumo (l.logic/resumo-por-usuario pedidos)]
  (println "resumo")
  (println resumo)
  (println "ordenado")
  (println (sort-by :preco-total resumo))
  (println "ordenado ao contrario")
  (println (reverse (sort-by :preco-total resumo)))
  (println "ordenado por id")
  (println (sort-by :usuario-id resumo))
  (println (get-in pedidos [0 :itens :mochila :quantidade])))

(defn resumo-por-usuario-ordenado [pedidos]
  (->> pedidos
       l.logic/resumo-por-usuario
       (sort-by :preco-total)
       reverse))

(let [pedidos (l.db/todos-os-pedidos)
      resumo (resumo-por-usuario-ordenado pedidos)]
  (println "resumo 2")
  (println resumo)
  (println "primeiro")
  (println (first resumo))
  (println "segundo")
  (println (second resumo))
  (println "resto")
  (println (rest resumo))
  (println "total")
  (println (count resumo))
  (println "classe")
  (println (class resumo))
  (println "index")
  (println (nth resumo 0))
  (println (nth resumo 2))
  (println (nth resumo 1))
  (println "pegar elemento")
  (println (take 2 resumo)))

(let [pedidos  (l.db/todos-os-pedidos)
      resumo   (resumo-por-usuario-ordenado pedidos)]
  (println "filter")
  (println "> 500 filter" (filter #(> (:preco-total %) 500) resumo))
  (println "tem alguem > 500 => " (not (empty? (filter #(> (:preco-total %) 500) resumo))))
  (println "some")
  (println "> 500 some" (some #(> (:preco-total %) 500) resumo)))


