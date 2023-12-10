(ns learning-clojure.curso-2-alura.aula-03
  (:require [learning-clojure.curso-2-alura.db :as l.db]))

;(println (l.db/todos-os-pedidos))
;(println (group-by :usuario (l.db/todos-os-pedidos)))

(defn minha-funcao-de-agrupamento
  [elemento]
  (println "elemento" elemento)
  (:usuario elemento))

(println (group-by minha-funcao-de-agrupamento (l.db/todos-os-pedidos)))

; { 15 [x x x]
;    1 [x]
;   10 [x]
;   20 [x] }

(println (count (vals (group-by minha-funcao-de-agrupamento (l.db/todos-os-pedidos)))))
(println (map count (vals (group-by minha-funcao-de-agrupamento (l.db/todos-os-pedidos)))))

(->> (l.db/todos-os-pedidos)
     (group-by :usuario)
     vals
     (map count)
     (println))

(defn conta-total-por-usuario
  [[usuario pedidos]]
  {
   :usuario-id usuario
   :total (count pedidos)
   })

(->> (l.db/todos-os-pedidos)
     (group-by :usuario)
     (map conta-total-por-usuario)
     println)


(println "PEDIDOS")

(defn total-do-item
  [[item-id detalhes]]
  (println "result" (get detalhes :quantidade 0) "x" (get detalhes :preco-unitario 0))
  (* (get detalhes :quantidade 0) (get detalhes :preco-unitario 0)))

(defn total-do-pedido
  [pedido]
  (->> pedido
       (map total-do-item)
       (reduce +)))

(defn total-dos-pedidos
  [pedidos]
  (->> pedidos
       (map :itens)
       (map total-do-pedido)
       (reduce +)))

(defn quantia-de-pedidos-e-gasto-total-por-usuario
  [[usuario pedidos]]
  {
   :usuario-id usuario
   :total-de-pedidos (count pedidos)
   :preco-total (total-dos-pedidos pedidos)
   })

(->> (l.db/todos-os-pedidos)
     (group-by :usuario)
     (map quantia-de-pedidos-e-gasto-total-por-usuario)
     println)
