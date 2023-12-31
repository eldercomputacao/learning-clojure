(ns learning-clojure.curso-5-alura.aula-03
  (:require [schema.core :as s])
  (:use [clojure.pprint]))

(s/set-fn-validation! true)

(def PosInt (s/pred pos-int? "inteiro-positivo"))

(def Paciente
  {:id PosInt, :nome s/Str})

(s/defn novo-paciente :- Paciente
  [id :- PosInt, nome :- s/Str]
  {:id id, :nome nome})

(pprint (novo-paciente 15, "guilherme"))
;(pprint (novo-paciente -15, "guilherme"))

(defn maior-ou-igual-a-zero? [x] (>= x 0))
(def ValorFinaceiro (s/constrained s/Num maior-ou-igual-a-zero?))

(def Pedido
  {:paciente     Paciente
   :valor        ValorFinaceiro
   :procedimento s/Keyword})

(s/defn novo-pedido :- Pedido
  [paciente     :- Paciente,
   valor        :- ValorFinaceiro,
   procedimento :- s/Keyword]
  {:paciente     paciente,
   :valor        valor,
   :procedimento procedimento})

(pprint (novo-pedido (novo-paciente 15, "guilherme"), 23.4, :raio-x))
;(pprint (novo-pedido (novo-paciente 15, "guilherme"), -23.4, :raio-x))

(def Numeros [s/Num])
(pprint (s/validate Numeros [2]))
(pprint (s/validate Numeros [2 44 55 4.5 -2.2]))
(pprint (s/validate Numeros [0]))
(pprint (s/validate Numeros []))
(pprint (s/validate Numeros nil))
;(pprint (s/validate Numeros [nil]))

(def Plano [s/Keyword])
(pprint (s/validate Plano [:raiox-x :coleta-de-sangue]))
(pprint (s/validate Plano []))
(pprint (s/validate Plano nil))
;(pprint (s/validate Plano [nil]))
;(pprint (s/validate Plano [":raiox-x" :coleta-de-sangue]))

(def Paciente
  {:id PosInt, :nome s/Str, :plano Plano})

(pprint (s/validate Paciente {:id 15, :nome "guilherme", :plano [:raio-x]}))
(pprint (s/validate Paciente {:id 15, :nome "guilherme", :plano [:raio-x, :coleta-de-sangue]}))
(pprint (s/validate Paciente {:id 15, :nome "guilherme", :plano []}))
(pprint (s/validate Paciente {:id 15, :nome "guilherme", :plano nil}))
;(pprint (s/validate Paciente {:id 15, :nome "guilherme"}))

