(ns nowhot.handler
  (:require [clojure.java.io :as io]
            [ring.util.response :as response]
            [compojure.handler :as handler]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.form :as form]
            [hiccup.core :refer [html]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

;; ページレイアウト
(defn layout [& content]
  (html
   [:head [:title "file upload with compojure."]]
   [:body
    content]))

;; ファイルアップロード用フォーム
(def upload-form
  (layout
   (form/form-to
    {:enctype "multipart/form-data"}
    [:post "/file"]
    (form/file-upload "file")
    (form/submit-button "submit"))))

;; アップロード成功
(def success
  (layout
   [:h1 "File uploaded successfully!"]))

;; アップロードされたファイルを保存する
(defn save-file [filename tempfile]
  (io/copy tempfile (io/file filename))
  (io/delete-file tempfile))


;ルーティング設定
(defroutes app-routes
  (GET "/" [] upload-form)
  (GET "/success" [] success)
  (POST "/file" {{{:keys [filename tempfile]} :file} :params}
          (save-file filename tempfile)
          (response/redirect "/success"))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
