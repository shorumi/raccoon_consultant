```
 ______     ______     ______     ______     ______     ______     __   __    
/\  == \   /\  __ \   /\  ___\   /\  ___\   /\  __ \   /\  __ \   /\ "-.\ \   
\ \  __<   \ \  __ \  \ \ \____  \ \ \____  \ \ \/\ \  \ \ \/\ \  \ \ \-.  \  
 \ \_\ \_\  \ \_\ \_\  \ \_____\  \ \_____\  \ \_____\  \ \_____\  \ \_\\"\_\ 
  \/_/ /_/   \/_/\/_/   \/_____/   \/_____/   \/_____/   \/_____/   \/_/ \/_/ 
                                                                              
```

## Stack
- Java 14
- SpringBoot 2.3.2
- PostgreSQL 12
- Flyway
- Lombok
- Docker / Docker-compose


## SETUP

```shell script
cp -a .env.development .env
```

```shell script
docker-compose up --build
```

## CURRENT IMPLEMENTED ENDPOINTS
```
http://localhost:8080/api/v1/laboratories

POST LABORATORIES

curl -i -H -v "Accept: application/json" \
-H 'Content-Type:application/json' \
-X POST \
-d '{"name": "Chuck Norris2", "address": "Chuck Street2", "status": "INACTIVE"}' http://localhost:8080/api/v1/laboratories \

========================================================================================================================

GET ALL LABORATORIES - THIS ENDPOINT LISTS JUST THE NOT LOGICALLY DELETE RECORDS 

curl -i -v http://localhost:8080/api/v1/laboratories

========================================================================================================================

FIND LABORATORY BY STATUS

curl -i -v http://localhost:8080/api/v1/laboratories/findByStatus/\?status\="INACTIVE"

========================================================================================================================

FIND LABORATORY BY ID

curl -i http://localhost:8080/api/v1/laboratories/4

========================================================================================================================

DELETE LOGICALLY A LABORATORY

curl -i -v -X DELETE http://localhost:8080/api/v1/laboratories/4

========================================================================================================================

PATCH A RECORD

curl -i -v \ 
-H "Accept: application/json" \
-H 'Content-Type:application/json' \
-X PATCH \
-d '{"name": "CHARLES BRONSON", "status": "ACTIVE"}' http://localhost:8080/api/v1/laboratories/8

========================================================================================================================

PUT A RECORD

curl -i -v \
-H "Accept: application/json" \
-H 'Content-Type:application/json' \
-X PUT \
-d '{"name": "Chuck Norris", "status": "ACTIVE", "address": "BURP LAND"}' http://localhost:8080/api/v1/laboratories/6

========================================================================================================================

```