(ns learning-clojure.curso-5-alura.aula-04
  (:require [schema.core :as s])
  (:use [clojure.pprint]))

(s/set-fn-validation! true)

(def PosInt (s/pred pos-int? "inteiro positivo"))
(def Plano [s/Keyword])
(def Paciente
  {:id                          PosInt,
   :nome                        s/Str,
   :plano                       Plano,
   (s/optional-key :nascimento) s/Str})

(pprint (s/validate Paciente {:id 15, :nome "guilherme", :plano [:raio-x]}))
(pprint (s/validate Paciente {:id 15, :nome "guilherme", :plano [:raio-x, :coleta-de-sangue]}))
(pprint (s/validate Paciente {:id 15, :nome "guilherme", :plano []}))
(pprint (s/validate Paciente {:id 15, :nome "guilherme", :plano nil}))
(pprint (s/validate Paciente {:id 15, :nome "guilherme", :plano [], :nascimento "12/12/2005"}))

(def Pacientes
  {PosInt Paciente})

(pprint (s/validate Pacientes {}))

(let [guilherme {:id 15 :nome "guilherme", :plano [:raio-x]}
      daniela {:id 20 :nome "daniela", :plano []}
      paulo {:id 25 :nome "paulo"}]
  (pprint (s/validate Pacientes {15 guilherme,
                                 20 daniela,
                                 }))
  )
