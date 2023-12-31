(ns learning-clojure.curso-5-alura.aula-02
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

;(s/defrecord Paciente [id :- Long, nome :- s/Str])
;
;(pprint (->Paciente 15 "guilherme"))
;(pprint (->Paciente "15" "guilherme"))
;(pprint (map->Paciente {"15" "guilherme"}))
;(pprint (map->Paciente {:id "15", :nome "guilherme"}))

(def Paciente
  "Schema de um paciente"
  {:id s/Num, :nome s/Str})

(pprint (s/explain Paciente))
(pprint (s/validate Paciente {:id 15, :nome "guilherme"}))
;(pprint (s/validate Paciente {:id 15, :nome "guilherme", :plano []}))
;(pprint (s/validate Paciente {:id 15, :nomee "guilherme"}))
;(pprint (s/validate Paciente {:id 15}))

(s/defn novo-paciente :- Paciente
  [id :- s/Num, nome :- s/Str]
  {:id id, :nome nome})

;(s/defn novo-paciente :- Paciente
;  [id :- s/Num, nome :- s/Str]
;  {:id id, :nome nome, :plano []})

(pprint (novo-paciente 15 "guilhermeeee"))

(pprint "-------------------")

(defn estritamente-positivo? [x]
  (> x 0))

(def EstritamentePositivo (s/pred estritamente-positivo?))

(pprint (s/validate EstritamentePositivo 15))
(pprint (s/validate EstritamentePositivo 10))
;(pprint (s/validate EstritamentePositivo 0))

(def EstritamentePositivo (s/pred estritamente-positivo? "estritamente-positivo"))

;(pprint (s/validate EstritamentePositivo 0))

(pprint "-------------------")

;(def Paciente
;  {:id (s/constrained s/Int estritamente-positivo?), :nome s/Str})

(def Paciente
  {:id (s/constrained s/Int pos-int?), :nome s/Str})

(pprint (s/validate Paciente {:id 10, :nome "guilherme"}))
;(pprint (s/validate Paciente {:id 10.3, :nome "guilherme"}))
;(pprint (s/validate Paciente {:id -20, :nome "guilherme"}))
;(pprint (s/validate Paciente {:id 0, :nome "guilherme"}))