# nowhot

今HOTな場所

## テスト画面

### データ登録テスト画面

/entry.html

## API一覧

### /

アプリルートです。下のように表示されていれば稼働しています。

<pre>running</pre>

### /entry

データを登録します。

メソッド：POST  
パラメータ：  
　hot：盛り上がり度  
　device：機器番号（1,2の二つかな？）  
　picture：base64エンコード文字列  
 
応答：
<pre>登録しました</pre> 

### /nowhot?yyyymmddhhmiss=20150829185302

nowhotデータを取得します。

メソッド：GET  
パラメータ：  
　yyyymmddhhmiss：基本は現在時刻（これをキーとして5秒以内の登録データを取得）   

応答：
<pre>
[{
  "picture":"XXXXX",
  "hot":"100",
  "longitude":"135.723628",
  "latitude":"34.725656",
  "device":"1"},
  {
  "picture":"YYYYYY",
  "hot":"200",
  "longitude":"34.732866",
  "latitude":"135.733910",
  "device":"2"
}]
</pre>

### /nowhots

NOW_HOTテーブルのデータを取得する（テスト用）

### /devices

DEVICE_POSITIONテーブルのデータを取得する（テスト用）

## License

Copyright © 2015 hisataka
