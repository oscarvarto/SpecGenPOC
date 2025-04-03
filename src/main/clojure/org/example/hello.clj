(ns org.example.hello
  (:require [taoensso.telemere :as t])
  (:import [sc.ex M$Person])
  (:gen-class))

(def someone
  (M$Person/apply "Juan" 21))

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