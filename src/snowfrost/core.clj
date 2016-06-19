(ns snowfrost.core
  (:require [clj-http.client :as client] 
            [clojure.data.json :as json])
  (:gen-class))

(defonce http-headers {:content-type :json :accept :json})
  
(defn- send-request [service]
  (client/post service http-headers))

(defn- extract-id [response]
  (let [body (:body response)
        body-map (json/read-str body :key-fn keyword)]
    (:id body-map)))

(defn- display [t-id effort resp-id]
  (let [resp-id-bin (Long/toString resp-id 2)
        msg (str "Thread " t-id " at effort " effort " got " resp-id " aka " resp-id-bin "\n")]
    (print msg)))

    
(defn- prettify-number [n numbers]
  (let [total-digits (count (str numbers))
        format-pattern (str "%0" total-digits "d") ]
    (format format-pattern n)))

(defn- worker [thread-id times service]
  (Thread. #(dotimes [i times]
              (let [response (send-request service)
                    id (extract-id response)]
                (display thread-id (prettify-number i times) id)))))

(defn- make-workers [amount times service]
  (for [n (range amount)] 
    (worker (prettify-number n amount) times service)))


(defn -main [& args]
  (let [service (first args)
        number-of-workers (Integer/parseInt (second args))
        number-of-times (Integer/parseInt (nth args 2))
        workers (make-workers number-of-workers number-of-times service)]
    (doseq [w workers] (.start w))
    (doseq [w workers] (.join w))
  ))
