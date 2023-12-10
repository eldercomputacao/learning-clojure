(ns learning-clojure.curso-2-alura.aula-01)

(map println ["elder" "samara" "theo" "malu"])
(println (first ["elder" "samara" "theo" "malu"]))
(println (first []))
(println (rest ["elder" "samara" "theo" "malu"]))
(println (rest []))
(println (next ["elder" "samara" "theo" "malu"]))
(println (next []))
(println (seq ["elder" "samara" "theo" "malu"]))
(println (seq []))
; 055088414-90

(println "meu mapa sem if")

(defn meu-mapa
  [funcao sequecia]
  (let [primeiro (first sequecia)]
    (funcao primeiro)
    (meu-mapa funcao (rest sequecia))))
;(meu-mapa println ["elder" "samara" "theo" "malu"])


(println "meu mapa com if")

(defn meu-mapa [funcao sequecia]
  (let [primeiro (first sequecia)]
    (if (not (nil? primeiro))
      (do
        (funcao primeiro)
        (meu-mapa funcao (rest sequecia))))))

(meu-mapa println ["elder" "samara" "theo" "malu"])
(meu-mapa println ["elder" false "samara" "theo" "malu"])
(meu-mapa println [])
(meu-mapa println nil)

;(meu-mapa println (range 100000))

(println "meu mapa com recur")

(defn meu-mapa [funcao sequecia]
  (let [primeiro (first sequecia)]
    (if (not (nil? primeiro))
      (do
        (funcao primeiro)
        (recur funcao (rest sequecia))))))

(meu-mapa println (range 100001))

