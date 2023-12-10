(ns learning-clojure.curso-1-alura.aula-05)

(def estoque {"Mochila"  10
              "Camiseta" 5})
(println estoque)

(println "total" (count estoque) "de elementos")
(println "chaves" (keys estoque))
(println "values" (vals estoque))

(def estoque {:mochila  10
              :camiseta 5})
(println estoque)

(println (assoc estoque :cadeira 3))
(println estoque)

(defn tira-um [valor]
  (println "tira-um" valor)
  (- valor 1))
(println (update estoque :mochila inc))
(println (update estoque :mochila tira-um))
(println (update estoque :mochila #(- % 1)))

(println (dissoc estoque :mochila))


(println "=====================")

(def pedido {
             :mochila  {:quantidade 2 :preco 80}
             :camiseta {:quantidade 3 :preco 40}
             })
(println pedido)

(println (assoc pedido :chaveiro {:quantidade 1 :preco 10}))

(println (pedido :mochila))
(println (get pedido :mochila))
(println (get pedido :cadeira {}))
(println (:mochila pedido))
(println (:cadeira pedido))
(println (:cadeira pedido {}))

(println (:quantidade (:mochila pedido)))

(println (:quantidade (:camiseta pedido)))

(println (update-in pedido [:mochila :quantidade] inc))

(println "threading" (-> pedido
                         :mochila
                         :quantidade))

(-> pedido
    ,,, :mochila
    ,,, :quantidade
    ,,, println)


