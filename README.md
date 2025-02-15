This project s containerized application by using docker, spring bootJPA and my sql

curl --location 'http://localhost:8080/books/save' \
--header 'Content-Type: application/json' \
--data '{
     
    "title": "EL",
    "price": 36.00
}'


curl --location --request GET 'http://localhost:8080/books/getTit/EL' \
--header 'Content-Type: application/json' \
--data '{
     
    "title": "EL",
    "price": 36.00
}'

curl --location 'http://localhost:8080/books/1'
