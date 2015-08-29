(ns nowhot.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

;ルーティング設定
(defroutes app-routes
  (GET "/" [] "running!")
  (route/files "/")
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
