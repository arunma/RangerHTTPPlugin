#!/usr/bin/env bash


#GET
#curl -iv -u admin:admin -X GET "http://localhost:6080/service/plugins/definitions/102"

#DELETE
#curl -iv -u admin:admin -X DELETE "http://localhost:6080/service/plugins/definitions/102"

#Register
curl -u admin:admin -X POST -H "Accept: application/json" -H "Content-Type: application/json" --data @http-ranger.json http://localhost:6080/service/plugins/definitions

