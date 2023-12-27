(ns learning-clojure.curso-4-alura.aula-02
  (:use clojure.pprint))

(defrecord Paciente [id, nome, nascimento])

;(defn deve-assinar-pre-autorizacao?
;  [paciente procedimento valor]
;  (if (= PacienteParticular (type paciente))
;    (>= valor 50)
;    (if (= PacientePlanoDeSaude (type paciente))
;      (let [plano (get paciente :plano)]
;        (not (some #(= % procedimento) plano)))
;      true)))

(defprotocol Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]))

(defrecord PacienteParticular [id, nome, nascimento])
(defrecord PacientePlanoDeSaude  [id, nome, nascimento, plano])


; forma 1
(extend-type PacienteParticular
  Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]
    (> valor 50)))

(extend-type PacientePlanoDeSaude
  Cobravel
  (deve-assinar-pre-autorizacao? [paciente procedimento valor]
    (let [plano (get paciente :plano)]
      (not (some #(= % procedimento) plano)))))

; forma 2
(defrecord PacienteSempreAssina
  [id, nome, nascimento, plano]
  Cobravel
  (deve-assinar-pre-autorizacao?
    [this procedimento valor]
    true))

(let [particular (->PacienteParticular 15 "guilherme" "18/09/1986")
      plano (->PacientePlanoDeSaude 15 "guilherme" "18/09/1986" [:raio-x :ultra-som])
      sempreAssina (->PacienteSempreAssina 15 "guilherme" "18/09/1986" [:raio-x :ultra-som])
      ]
  (pprint (deve-assinar-pre-autorizacao? particular :raio-x 560))
  (pprint (deve-assinar-pre-autorizacao? particular :raio-x 45))
  (pprint (deve-assinar-pre-autorizacao? plano :raio-x 1000))
  (pprint (deve-assinar-pre-autorizacao? plano :exame-sangue 2000))
  (pprint (deve-assinar-pre-autorizacao? sempreAssina :exame-sangue 2000))

  )


(defprotocol Dateable
  (to-ms [this]))

(extend-type java.lang.Number
  Dateable
  (to-ms [this]
    (println "=> number")
    this))
(pprint (to-ms 56))

(extend-type java.util.Date
  Dateable
  (to-ms [this]
    (println "=> date")
    (.getTime this)))
(pprint (to-ms (java.util.Date.)))

(extend-type java.util.Calendar
  Dateable
  (to-ms [this]
    (to-ms (.getTime this))))
(pprint (to-ms (java.util.GregorianCalendar.)))

(extend-type java.lang.String
  Dateable
  (to-ms [this]
    this))
(pprint (to-ms "guilherme"))

