(ns learning-clojure.curso-1-alura.aula-01-02-03)

(println "Exemplo 1")

(defn aplica-desconto? [valor-bruto]
  (> valor-bruto 100))

(defn valor-descontado [valor-bruto]
  (if (aplica-desconto? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

;(println (valor-descontado 1000))
;(println (valor-descontado 500))

(println "Exemplo 2")

(defn mais-caro-que-100 [valor-bruto]
  (> valor-bruto 100))

(defn valor-descontado [aplica? valor-bruto]
  (if (aplica? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado mais-caro-que-100 500))
(println (valor-descontado mais-caro-que-100 40))

(println "Exemplo 3")

(println (valor-descontado (fn [valor-bruto] (> valor-bruto 100)) 1000))
(println (valor-descontado (fn [valor-bruto] (> valor-bruto 100)) 100))
(println (valor-descontado (fn [v] (> v 100)) 1000))
(println (valor-descontado (fn [v] (> v 100)) 100))
(println (valor-descontado #(> % 100) 1000))
(println (valor-descontado #(> % 100) 100))