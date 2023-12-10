(ns learning-clojure.curso-1-alura.aula-06)

(def pedido {
             :mochila  {:quantidade 2 :preco 80}
             :camiseta {:quantidade 3 :preco 40}
             })

(defn imprime-e-15 [valor]
  (println "valor->" (class valor) valor)
  15)

;(println (map imprime-e-15 pedido))

(defn imprime-e-15 [[chave valor]]
  valor)

;(println (map imprime-e-15 pedido))

(defn preco-dos-produtos [[chave valor]]
  (* (:quantidade valor) (:preco valor)))
(println (reduce + (map preco-dos-produtos pedido)))

(defn preco-dos-produtos [[_ valor]]
  (* (:quantidade valor) (:preco valor)))
(println (reduce + (map preco-dos-produtos pedido)))

(defn total-do-pedido [pedido]
  (reduce + (map preco-dos-produtos pedido)))
(println (total-do-pedido pedido))

(defn total-do-pedido
  [pedido]
  (->> pedido
       (map preco-dos-produtos ,,,)
       (reduce + ,,,)))
(println (total-do-pedido pedido))

(defn preco-total-do-produto [produto]
  (* (:quantidade produto) (:preco produto)))

(defn total-do-pedido
  [pedido]
  (->> pedido
       vals
       (map preco-total-do-produto ,,,)
       (reduce + ,,,)))
(println (total-do-pedido pedido))
(println (vals pedido))


(def pedido {
             :mochila  {:quantidade 2 :preco 80}
             :camiseta {:quantidade 3 :preco 40}
             :chaveiro {:quantidade 1}
             })

(defn gratuito? [item]
  (<= (get item :preco 0) 0))
(println "gratuitos" (filter gratuito? (vals pedido)))

(defn gratuito? [[_ item]]
  (<= (get item :preco 0) 0))
(println "gratuitos" (filter gratuito? pedido))

(defn gratuito? [item]
  (<= (get item :preco 0) 0))
(println "gratuitos" (filter (fn [[chave item]] (gratuito? item)) pedido))
(println "gratuitos 2" (filter #(gratuito? (second %)) pedido))

(defn pago? [item]
  (not (gratuito? item)))
(println (pago? {:preco 40}))
(println (pago? {:preco 0}))