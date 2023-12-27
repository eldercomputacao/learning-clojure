(ns learning-clojure.curso-4-alura.aula-05
  (:use [clojure.pprint]))

(defn tipo-de-autorizador [pedido]
  (let [situacao (:situacao pedido)
        plano (:plano pedido)]
    (cond (= :urgente situacao) :sempre-autorizado
          (not= plano nil) :plano-de-saude
          :else :credito-minimo)))

(defmulti deve-assinar-pre-autorizacao? tipo-de-autorizador)

(defmethod deve-assinar-pre-autorizacao? :sempre-autorizado [pedido]
  false)

(defmethod deve-assinar-pre-autorizacao? :plano-de-saude [pedido]
  (not (some #(= % (:procedimento pedido)) (:plano pedido))))

(defmethod deve-assinar-pre-autorizacao? :credito-minimo [pedido]
  (>= (:valor pedido 0) 50))

(let [particular {:id 15 :nome "maria" :nascimento "18/09/1986" :situacao :urgente }
      plano {:id 15 :nome "guilherme" :nascimento "18/09/1986" :situacao :urgente :plano [:raio-x :ultra-som]}]
  (pprint (deve-assinar-pre-autorizacao? {:paciente particular, :valor 1000, :procedimento :coleta-de-sangue}))
  (pprint (deve-assinar-pre-autorizacao? {:paciente plano, :valor 1000, :procedimento :coleta-de-sangue}))
  )

(let [particular {:id 15 :nome "maria" :nascimento "18/09/1986" :situacao :normal :valor 1000, :procedimento :coleta-de-sangue}
      plano {:id 15 :nome "guilherme" :nascimento "18/09/1986" :situacao :normal :plano [:raio-x :ultra-som] :valor 1000, :procedimento :coleta-de-sangue}]
  (pprint (deve-assinar-pre-autorizacao? {:paciente particular, :valor 1000, :procedimento :coleta-de-sangue}))
  (pprint (deve-assinar-pre-autorizacao? {:paciente plano, :valor 1000, :procedimento :coleta-de-sangue}))
  )