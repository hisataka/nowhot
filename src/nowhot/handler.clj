(ns nowhot.handler
  (:require [ring.util.response :as response]
            [compojure.handler :as handler]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
  (:require [org.httpkit.client :as http])
  (:require [clojure.java.jdbc :as j])
  (:use [cheshire.core]))

(load "http")
(load "db")

;ルーティング設定
(defroutes app-routes
  (GET "/" [] "running!")
  (POST "/entry" [hot device picture]
        (do
          (println "##########################################################")
          (println (str "hot=" hot ", device=" device ",picture=" picture))
          (println "##########################################################")
          (entry hot device picture)
          (res-http (str "登録しました"))))
  (GET "/nowhot" {params :params}
       (nowhot (params :yyyymmddhhmiss)))
  (GET "/nowhots" [] (nowhots))
  (GET "/devices" [] (devices))
  (GET "/delete" [] (delete))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes (assoc-in site-defaults [:security :anti-forgery] false)))
