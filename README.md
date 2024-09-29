# Сеть магазинов электроники

##  Версии
* JDK 23
* SpringBoot 3.3.4

## Программное обеспечение
* Intellij IDEA
* Docker

## Запуск приложения
* Создать подключение к базе данных (Есть файл для создания docker-контейнера)
* Обновить Maven-проекты во вкладке Maven
* Запустить микросервисы (через вкладку Services)
* Подождать около минуты (пока все сервисы будут зарегистрированы на сервере и получат доступ к маршрутам)
* Через браузер обратиться по адресу: http://localhost:8765/index - стартовая страница приложения

        В каждом модуле, относящемуся к базе данных (employee-service, product-service, purchase-service, store-service)
      есть конфигурационный файл миграции в базу данных config.InitDatabase.java, для добавлении данных при запуске приложения.
      Эти файлы можно отключить или удалить и загрузить данные через приложение (CSV, CSV UTF-8(с учетом кириллических символов) или форму заполения).
