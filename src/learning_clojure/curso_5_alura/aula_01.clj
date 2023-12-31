(ns learning-clojure.curso-5-alura.aula-01
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(defn adiciona-paciente
  [pacientes paciente]
  (if-let [id (:id paciente)]
    (assoc pacientes id paciente)
    (throw (ex-info "paciente n possui id" {:paciente paciente}))))

(defn adiciona-visita
  [visitas paciente novas-visitas]
  (if (contains? visitas paciente)
    (update visitas paciente concat novas-visitas)
    (assoc visitas paciente novas-visitas)))

(defn imprime-relatorio-de-paciente
  [visitas paciente]
  (println "visitas do paciente" paciente "são" (get visitas paciente)))

(defn testa-uso-de-paciente []
  (let [guilherme {:id 15 :nome "guilherme"}
        daniela {:id 20 :nome "daniela"}
        paulo {:id 25 :nome "paulo"}
        pacientes (reduce adiciona-paciente {} [guilherme daniela paulo])
        visitas {}
        visitas (adiciona-visita visitas 15 ["01/01/2019"])
        visitas (adiciona-visita visitas 20 ["01/02/2019" "01/01/2020"])
        visitas (adiciona-visita visitas 15 ["01/03/2019"])]
    (pprint pacientes)
    (pprint visitas)
    (imprime-relatorio-de-paciente visitas daniela)
    ))

;(testa-uso-de-paciente)


(pprint (s/validate Long 15))
;(pprint (s/validate Long "15"))
;(pprint (s/validate Long [1 2 3]))

(s/set-fn-validation! true)

(s/defn teste-simples
  [x :- Long]
  (println x))

(teste-simples 3)
;(teste-simples "3")

(s/defn imprime-relatorio-de-paciente
  [visitas paciente :- Long]
  (println "visitas do paciente" paciente "são" (get visitas paciente)))

;(testa-uso-de-paciente)

(s/defn novo-paciente
  [id :- Long, nome :- s/Str]
  {:id id, :nome nome})

(pprint (novo-paciente 15 "guilherme"))
;(pprint (novo-paciente "guilherme" 15))
