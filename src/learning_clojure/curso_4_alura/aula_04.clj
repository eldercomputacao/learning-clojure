(ns learning-clojure.curso-4-alura.aula-04
  (:use clojure.pprint))

(defrecord PacienteParticular [id, nome, nascimento, situacao])
(defrecord PacientePlanoDeSaude [id, nome, nascimento, situacao, plano])

(defprotocol Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]))

(defn nao-e-urgente? [paciente]
  (not= :urgente (:situacao paciente :normal)))

(extend-type PacienteParticular
  Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]
    (and (>= valor 50) (nao-e-urgente? paciente))))

(extend-type PacientePlanoDeSaude
  Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]
    (let [plano (get paciente :plano)]
      (and (not (some #(= % procedimento) plano)) (nao-e-urgente? paciente)))))


(let [particular (->PacienteParticular 15 "guilherme" "18/09/1986" :normal)
      plano (->PacientePlanoDeSaude 15 "guilherme" "18/09/1986" :normal [:raio-x :ultra-som])]
  (pprint (deve-assinar-pre-autorizacao? particular :raio-x 500))
  (pprint (deve-assinar-pre-autorizacao? particular :raio-x 45))
  (pprint (deve-assinar-pre-autorizacao? plano :raio-x 5))
  (pprint (deve-assinar-pre-autorizacao? plano :exame-sangue 5))
  )

(let [particular (->PacienteParticular 15 "guilherme" "18/09/1986" :urgente)
      plano (->PacientePlanoDeSaude 15 "guilherme" "18/09/1986" :urgente [:raio-x :ultra-som])]
  (pprint (deve-assinar-pre-autorizacao? particular :raio-x 500))
  (pprint (deve-assinar-pre-autorizacao? particular :raio-x 45))
  (pprint (deve-assinar-pre-autorizacao? plano :raio-x 5))
  (pprint (deve-assinar-pre-autorizacao? plano :exame-sangue 5))
  )

(println "-------------defmulti----------------")

(defmulti deve-assinar-pre-autorizacao-multi? class)

(defmethod deve-assinar-pre-autorizacao-multi? PacienteParticular [paciente]
  (println "invocando paciente particular")
  (println (:nome paciente))
  true)

(defmethod deve-assinar-pre-autorizacao-multi? PacientePlanoDeSaude [paciente]
  (println "invocando paciente plano de saude")
  (println (:nome paciente))
  false)

(let [particular (->PacienteParticular 15 "elder" "18/09/1986" :urgente)
      plano (->PacientePlanoDeSaude 15 "guilherme" "18/09/1986" :urgente [:raio-x :ultra-som])]
  (pprint (deve-assinar-pre-autorizacao-multi? particular))
  (pprint (deve-assinar-pre-autorizacao-multi? plano))
  )


(println "-------------minha func----------------")

(defn minha-funcao [p]
  (println "aqui")
  (class p))

(defmulti multi-teste minha-funcao)

(defmethod multi-teste java.lang.String [p]
  (println "na string")
  p)

(defmethod multi-teste java.lang.Number [p]
  (println "na number")
  p)
(println (multi-teste "elder"))
(println (multi-teste 87))
(println (multi-teste 87.34))

(println "-----------------------------")

(defn tipo-de-autorizador [pedido]
  (let [paciente (:paciente pedido)
        situacao (:situacao paciente)
        urgente? (= :urgente situacao)]
    (if urgente?
      :sempre-autorizado
      (class paciente))))

(defmulti deve-assinar-pre-autorizacao-do-pedido? tipo-de-autorizador)

(defmethod deve-assinar-pre-autorizacao-do-pedido? :sempre-autorizado [pedido]
  false)

(defmethod deve-assinar-pre-autorizacao-do-pedido? PacienteParticular [pedido]
  (>= (:valor pedido 0) 50))

(defmethod deve-assinar-pre-autorizacao-do-pedido? PacientePlanoDeSaude [pedido]
  (not (some #(= % (:procedimento pedido)) (:plano (:paciente pedido)))))

(let [particular (->PacienteParticular 15 "elder" "18/09/1986" :urgente)
      plano (->PacientePlanoDeSaude 15 "guilherme" "18/09/1986" :urgente [:raio-x :ultra-som])]
  (pprint (deve-assinar-pre-autorizacao-do-pedido? {:paciente particular, :valor 1000, :procedimento :coleta-de-sangue}))
  (pprint (deve-assinar-pre-autorizacao-do-pedido? {:paciente plano, :valor 1000, :procedimento :coleta-de-sangue}))
  )

(let [particular (->PacienteParticular 15 "elder" "18/09/1986" :normal)
      plano (->PacientePlanoDeSaude 15 "guilherme" "18/09/1986" :normal [:raio-x :ultra-som])]
  (pprint (deve-assinar-pre-autorizacao-do-pedido? {:paciente particular, :valor 1000, :procedimento :coleta-de-sangue}))
  (pprint (deve-assinar-pre-autorizacao-do-pedido? {:paciente plano, :valor 1000, :procedimento :coleta-de-sangue}))
  )
