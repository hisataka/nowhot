(in-ns 'nowhot.handler)

; postgresql接続用
(def postgresql-db {:subprotocol "postgresql"
                    :subname "//ec2-54-83-59-154.compute-1.amazonaws.com:5432/d7krtngsfmctbs?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory"
                    :user "tyveimvsrtjina"
                    :password "fIuxvMBXFGlAGeMpDAjTeMn_Qw"})

(defn entry [hot device picture]
  (j/insert!
   postgresql-db
   :NOW_HOT
    {:DEVICE device :HOT hot :PICTURE picture}))

(defn nowhot [yyyymmddhhmiss]
  (res-json (generate-string (j/query postgresql-db
           ["select
	A.DEVICE,
	C.LATITUDE,
	C.LONGITUDE,
	B.HOT,
	B.PICTURE
from
	(select
		DEVICE,
		MAX(INS_TIME) INS_TIME
	from
		NOW_HOT
	group by
		DEVICE) A
	left outer join NOW_HOT B
		on B.DEVICE = A.DEVICE
		and B.INS_TIME = A.INS_TIME
	left outer join DEVICE_POSITION C
		on C.DEVICE = A.DEVICE
where
	A.INS_TIME >= to_timestamp(?, 'YYYYMMDDHH24MISS') - time '00:00:05'" yyyymmddhhmiss]))))

(defn nowhots []
  (res-json (generate-string (j/query postgresql-db
           ["select
	A.ID,
	A.DEVICE,
	B.LATITUDE,
	B.LONGITUDE,
	A.HOT,
	A.PICTURE,
	A.INS_TIME
from
	NOW_HOT A
	left outer join DEVICE_POSITION B
		on B.DEVICE = A.DEVICE"]))))

(defn devices []
  (res-json (generate-string (j/query postgresql-db
           ["select
	DEVICE,
	LATITUDE,
	LONGITUDE
from
	DEVICE_POSITION"]))))

