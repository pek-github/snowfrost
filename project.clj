(defproject snowfrost "0.1.0-SNAPSHOT"
  :description "A multi-threaded client for Snowflake"
  :url "https://github.com/pek-github/snowfrost"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [clj-http "2.0.0"]
                 [org.clojure/data.json "0.2.6"]]
  :main snowfrost.core)
