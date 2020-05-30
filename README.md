# HR Tasker
Система автоматизации сбора актуальный данных от сотрудников
 
Сборка: mvn clean install <br />
Запуск: java -jar target/tasker-0.0.1-SNAPSHOT.jar<br />
или mvn spring-boot:run <br />

<br />    
Сборка Docker-образа: docker build --build-arg JAR_FILE=target/*.jar -t kornilov/tasker:0.0.1 .
<br />
Запуск микросервиса в Docker: docker run -p 8080:8080 kornilov/tasker:0.0.1
<br />
Запуск postgres с помощью docker compose: docker-compose up

Начальная страница http:/localhost:8080/

для входа использовать<br/> 
|логин|пароль|<br/>
|admin|admin |<br/>
| user | user |
                       