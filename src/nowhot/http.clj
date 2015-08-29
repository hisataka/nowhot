(in-ns 'nowhot.handler)

; jsonレスポンスを返す
(defn res-json [body]
  {:status 200 :headers {"Content-Type" "application/json; charset=UTF-8"
                         "Access-Control-Allow-Origin" "*"
                         "Access-Control-Allow-Headersa" "x-requested-with, content-type, origin, accept"} :body body})
; httpレスポンスを返す
(defn res-http [body]
  {:status 200 :headers {"Content-Type" "text/html; charset=UTF-8"} :body body})
