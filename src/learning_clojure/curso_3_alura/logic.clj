(ns learning-clojure.curso_3_alura.logic)

(defn cabe?
  [hospital departamento]
  (-> hospital
      (get ,,, departamento)
      (count ,,,)
      (< ,,, 5)))

(defn chega-em
  [hospital departamento pessoa]
  (if (cabe? hospital departamento)
    (update hospital departamento conj pessoa)
    (throw (ex-info "Fila já está cheia" {:tentanto-adicionar pessoa}))))

(defn chega-em-pausado
  [hospital departamento pessoa]
  ;(Thread/sleep (* (rand) 2000))
  (if (cabe? hospital departamento)
    (do
      ;(Thread/sleep 1000)
      (update hospital departamento conj pessoa))
    (throw (ex-info "Fila já está cheia" {:tentanto-adicionar pessoa}))))

(defn chega-em-pausado-logando
  [hospital departamento pessoa]
  (println "tentando adicionar a pessoa" pessoa)
  (Thread/sleep (* (rand) 2000))
  (if (cabe? hospital departamento)
    (do
      ;(Thread/sleep 1000)
      (println "dei update" pessoa)
      (update hospital departamento conj pessoa))
    (throw (ex-info "Fila já está cheia" {:tentanto-adicionar pessoa}))))

(defn atende
  [hospital departamento]
  (update hospital departamento pop))

(defn transfere
  [hospital de para]
  (let [pessoa (peek (get hospital de))]
    (atende hospital de)
    (chega-em hospital para pessoa)))

(defn transfere
  [hospital de para]
  (let [pessoa (peek (get hospital de))]
    (-> hospital
        (atende,,, de)
        (chega-em,,, para pessoa))))

;(defn proxima [hospital departamento]
;  (peek (get hospital de)))

(defn proxima [hospital departamento]
  (-> hospital
      departamento
      peek))

(defn transfere
  [hospital de para]
  (let [pessoa (proxima hospital de)]
    (-> hospital
        (atende,,, de)
        (chega-em,,, para pessoa))))

(defn atende-completo
  [hospital departamento]
  {:paciente (update hospital departamento peek)
   :hospital (update hospital departamento pop)})

(defn atende-completo-que-chama-ambos
  [hospital departamento]
  (let [fila (get hospital departamento)
        peek-pop (juxt peek pop)
        [pessoa fila-atualiza] (peek-pop fila)
        hosptital-atualizado (update hospital assoc departamento fila-atualiza)]
    {:paciente pessoa
     :hospital hosptital-atualizado}))
