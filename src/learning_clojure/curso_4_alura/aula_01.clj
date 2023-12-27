(ns learning-clojure.curso-4-alura.aula-01
  (:use clojure.pprint))

(defn adiciona-paciente
  [pacientes paciente]
  (if-let [id (:id paciente)]
    (assoc pacientes id paciente)
    (throw (ex-info "paciente não tem id" {}))))

(defn testa-uso-de-pacientes []
  (let [pacientes {}
        guilerme {:id 15 :nome "guilherme" :nascimento "18/09/1981"}
        daniela  {:id 20 :nome "daniela"   :nascimento "18/09/1982"}
        paulo    {       :nome "paulo"     :nascimento "18/09/1983"}]
    (pprint (adiciona-paciente pacientes guilerme))
    (pprint (adiciona-paciente pacientes daniela))
    (pprint (adiciona-paciente pacientes paulo))
    ))

;(testa-uso-de-pacientes)

(defrecord Paciente [id nome nascimento])

(println (->Paciente 15 "guilherme" "18/09/1981"))
(pprint (->Paciente 15 "guilherme" "18/09/1981"))
(pprint (Paciente. 15 "guilherme" "18/09/1986"))
(pprint (Paciente. "guilherme" 15 "18/09/1986"))
(pprint (Paciente. 15 "18/09/1986" "guilherme"))
(pprint (map->Paciente {:id 15 :nome "guilherme" :nascimento "18/09/1981"}))

(let [guilherme (->Paciente 15 "guilherme" "18/09/1981")]
  (pprint (:id guilherme))
  (pprint (:nascimento guilherme))
  (pprint (vals guilherme))
  (pprint (class guilherme))
  (pprint (record? guilherme))
  (pprint (.nome guilherme))
  )

(pprint (map->Paciente {:id 15 :nome "guilherme" :nascimento "18/09/1981" :rg "111414"}))
(pprint (map->Paciente {:nome "guilherme" :nascimento "18/09/1981"}))
