(ns learning-clojure.curso-1-alura.aula-04)

(def vetor [10 20 30 40 50])

(println (get vetor 0))
(println (get vetor 5))
(println (vetor 1))
;(println (vetor 5))
(println (get vetor 10 1000))

(println (conj vetor 60))
(println vetor)

(println (inc 10))
(println (dec 10))

(println (update vetor 0 inc))
(println (update vetor 0 dec))

(defn soma+1 [valor]
  (+ valor 1))

(println (update vetor 0 soma+1))
(println (update vetor 0 (fn [x] (+ x 1))))
(println (update vetor 0 #(+ % 1)))


(def precos [30 700 1000])

(defn aplica-desconto? [valor-bruto]
  (> valor-bruto 100))

(defn valor-descontado [valor-bruto]
  (if (aplica-desconto? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (map valor-descontado precos))

(println (range 10))
(println (filter even? (range 10)))

(println "preços" precos)
(println (filter aplica-desconto? precos))
(println (map valor-descontado (filter aplica-desconto? precos)))

(defn minha-soma [valor1 valor2]
  (println "soma" valor1 valor2)
  (+ valor1 valor2))

(println "reduce" (reduce + precos))
(println "reduce" (reduce minha-soma 0 precos))
(println "reduce" (reduce minha-soma 0 (range 10)))
(println "reduce" (reduce minha-soma 0 []))
;(println "reduce" (reduce minha-soma [29]))