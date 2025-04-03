(ns org.example.hello
  (:require
   [clojure.spec.alpha :as s]
   [clojure.java.data :as j]
   [taoensso.telemere :as t])
  (:import (sc.ex M$Address M$Person)))

(def someone
  (M$Person "Juan" 21 (M$Address "Ave" "12")))

;(def oscar
;  (-> (Person/builder)
;      (.name "Oscar")
;      (.age 42)
;      (.address
;       (-> (Address/builder)
;           (.street "Random Ave 54")
;           (.zipCode "59876")
;           (.build)))
;      (.build)))
;
;(def alex
;  (-> (Gamer/builder)
;      (.name "Alex")
;      (.age 14)
;      (.address
;       (-> (Address/builder)
;           (.street "Main St 123")
;           (.zipCode "12345")
;           (.build)))
;      (.favoriteGame "Roblox")
;      (.build)))

(s/def ::favorite-game (s/and map? #(string? (get % :favoriteGame))))

(s/def ::favoriteGame string?)

(s/def ::street string?)
(def zip-code-regex #"^(?!00000)\d{5}(?:[ \-](?!0000)\d{4})?$")
(s/def ::zipCode (s/and string? #(re-matches zip-code-regex %)))
(s/def ::address
  (s/keys :req-un [::street ::zipCode]))
(s/def ::name string?)
(s/def ::age (s/and int?
                #(>= % 0)
                #(<= % 130)))

(s/def ::person
  (s/keys :req-un [::name ::age ::address]))

(s/def ::gamer
  (s/merge ::person
           (s/keys :req-un [::favoriteGame])))

;(defn f [obj]
;  (j/from-java obj))
;
;(def input-map
;  (f alex))
;(println input-map)
;
;(s/valid? ::person (f oscar))
;(s/valid? ::gamer (f oscar))
;
;(s/valid? ::gamer input-map)
;(s/valid? ::favorite-game input-map)
;(s/conform ::gamer input-map)
;(s/explain ::gamer input-map)

;(defmethod j/to-java [Person clojure.lang.APersistentMap] [Person props]
;  {:pre [(s/valid? ::person props)]}
;  (let [p (-> (Person/builder)
;              (.name (:name props))
;              (.age (:age props))
;              (.address
;               (-> (Address/builder)
;                   (.street (-> props :address :street))
;                   (.zipCode (-> props :address :zipCode))
;                   (.build)))
;              (.build))]
;    [p props]))
;
;(j/to-java Person input-map)
(let [runtime-version (Runtime/version)
      version (str (.get (.version runtime-version) 0))]
   (t/log! :info (str "Java Runtime Version: " version)))

(defn greet
  "Callable entry point to the application."
  [data]
  (println (str "Hello, " (or (:name data) "World") "!")))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (greet {:name (first args)}))