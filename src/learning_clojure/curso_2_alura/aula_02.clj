(ns learning-clojure.curso-2-alura.aula-02)

(println "com recursão")

(defn conta
  [total-ate-agora elementos]
  (recur (inc total-ate-agora) (rest elementos)))
;(println (conta 0 ["elder" "samara" "theo" "malu"]))
;(println "oi")

(defn conta
  [total-ate-agora elementos]
  (println total-ate-agora elementos)
  (if (next elementos)
    (recur (inc total-ate-agora) (next elementos))
    (inc total-ate-agora)))
;(println (conta 0 ["elder" "samara" "theo" "malu"]))
;(println (conta 0 []))

(defn conta
  [total-ate-agora elementos]
  (if (seq elementos)
    (recur (inc total-ate-agora) (next elementos))
    total-ate-agora))
;(println (conta 0 ["elder" "samara" "theo" "malu"]))
;(println (conta 0 []))

(defn conta
  ([elementos]
   (conta 0 elementos))
  ([total-ate-agora elementos]
   (if (seq elementos)
     (recur (inc total-ate-agora) (next elementos))
     total-ate-agora)))
;(println (conta ["elder" "samara" "theo" "malu"]))
;(println (conta []))

(println "sem recursão, usando loop")

(defn conta-com-loop
  [elementos]
  (println "antes do loop")
  (loop [total-ate-agora 0
         elementos-restantes elementos]
    (if (seq elementos-restantes)
      (recur (inc total-ate-agora) (next elementos-restantes))
      total-ate-agora)))
(println (conta-com-loop ["elder" "samara" "theo" "malu"]))
(println (conta-com-loop []))
