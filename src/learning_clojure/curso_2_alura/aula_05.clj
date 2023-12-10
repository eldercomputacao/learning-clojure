(ns learning-clojure.curso-2-alura.aula-05
  (:require [learning-clojure.curso-2-alura.db :as l.db]
            [learning-clojure.curso-2-alura.logic :as l.logic]))

(defn gastou-bastante? [info-do-usuario]
  (> (:preco-total info-do-usuario) 500))

;(let [pedidos (l.db/todos-os-pedidos)
;      resumo (l.logic/resumo-por-usuario pedidos)]
;  (println "resumo")
;  (println resumo)
;  (println "keep")
;  (println (keep gastou-bastante? resumo)))

(defn gastou-bastante? [info-do-usuario]
  (println "gastou bastante" (:usuario-id info-do-usuario))
  (> (:preco-total info-do-usuario) 500))

(let [pedidos (l.db/todos-os-pedidos)
      resumo (l.logic/resumo-por-usuario pedidos)]
  (println "resumo")
  (println resumo)
  (println "keep")
  (println (keep gastou-bastante? resumo)))


;(defn filter1 [x]
;  (println "filter1" x)
;  x)
;
;(defn filter2 [x]
;  (println "filter2" x)
;  x)
;
;(->> (range 50)
;     (map filter1)
;     (map filter2)
;     println)