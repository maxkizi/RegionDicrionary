# README #
Учебный проект. Предсталяет собой справачник регионов РФ.


Java версия: `11`

Сборка проекта осуществляется с помощью maven
mvn clean package


1) Приложение предоставляет ReST api для операций чтения и изменения справочника (CRUD)
2) Запуск приложения происходит при помощи команды: docker-compose up
3) В приложении используется внутрення БД (in-memory) H2
4) Со стартом приложения в базу "прогружаются" 50 регионов
5) Документация openApi (swagger) расположена: src/main/resources/api/dictionary/region/region.yaml
6) Postman коллекция расположена:
src/main/resources/postman-collection/RegionDictionary.postman_collection.json 
(постман тесты расчитаны на одну итерацию после запуска приложения,
так как меняется состояние бд)

