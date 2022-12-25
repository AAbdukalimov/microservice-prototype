Технологии проекта:
MySQL, FlyWay,Spring Boot 2.7.6, Lombok, Swagger 3.0, частично Spring Data JPA, сборка проекта Maven

Для запуска проекта: 

Импортировать проект на ваш сервер
скачать и установить БД, при необходимости, при установке указать данные из файла по ссылке:
https://github.com/AAbdukalimov/Microservice-prototype/blob/main/solva/src/main/resources/application.properties

Далее по ссылке ниже открываете файл SolvaApplication нажатием SHIFT+F10 запускаете приложение:
https://github.com/AAbdukalimov/Microservice-prototype/blob/main/solva/src/main/java/kz/idf/solva/SolvaApplication.java

Следующим шагом будет создание схемы и таблиц БД, пройдя по ссылке ниже нужно по очередности нажать зелёный треугольник в каждом файле:
https://github.com/AAbdukalimov/Microservice-prototype/tree/main/solva/src/main/java/kz/idf/solva/scripts

Далее откройте в вашем браузере ссылку http://localhost:8080/swagger-ui/index.html, и можно протестировать функционал приложения.

Описание API доступно по ссылке: https://github.com/AAbdukalimov/Microservice-prototype/blob/main/solva/swagger.yaml

P.S.
К сожалению не успел написать тесты, на днях добавлю, если будет необходимость.




