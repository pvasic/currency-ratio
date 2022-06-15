# REST API using Spring Boot, Java, Html, JavaScript

## Сервис отображения GIF по отношению курсов валют к заданному.

* На HTTP Endpoint передается код валюты.
* Если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
* Если ниже - отсюда https://giphy.com/search/broke

### Run App

#### 1) Install Gradle

#### 2) Running the Wrapper task
    gradle wrapper --gradle-version 7.4.2 --distribution-type all
#### 3) Running the App
    gradle run

### Swagger UI

<a href="http://localhost:8080/swagger-ui.html">You can watch it here: localhost:8080/swagger-ui.html</a>

### Curl tests:

#### Get all codes
    curl -s http://localhost:8080/api/codes

#### Get gif by code
    curl -s http://localhost:8080/api/gifs/RUB

### HTML + JS
http://localhost:8080/

