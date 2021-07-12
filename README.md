# Proyecto Rest
## Descripción
Proyecto semestral de la asignatura de Computación Paralela y Distribuida Correspondiente a un servicio web tipo *Api-Restful* para obtener a través de *scrapping*, la información de los últimos sismos que provee la página web de la [Agencia de Geofísica de la Universidad de Chile](http://www.sismologia.cl/links/ultimos_sismos.html) como un *array* de objetos *JSON* usando métodos de autenticación de aplicación consumidora como también de *JSON Web Token* (JWT).

## Integrantes

- Edgar I. Matus Soto
- Alex Phillips Bidart Orellana
- Nicolás Andrés Jiménez Jiménez

# Requerimientos
- JDK 11 (*Java SE Development Kit 11*), se recomienda *OpenJDK 11*:

		sudo apt-get install openjdk-11-jdk

- *Apache Maven 3.8.1+*:

		sudo apt-get install maven

- *PostgreSQL* o su cliente:

		sudo apt-get install postgresql postgresql-client`

# Ejecución
Posicionarse en el directorio del proyecto y compilar instalando las dependencias usando:

	sudo mvn clean install

Ejecutar el proyecto usando:

	sudo mvn spring-boot:run

O en su defecto:

	java -jar ./target/ProyectoRestZ.war

El puerto del servidor embebido es el clásico de *Apache Tomcat*: 8080

# Consumo
Se recomienda el uso de *cURL*:

	sudo apt-get install curl

Como también el uso de *jq JSON processor*:

	sudo apt-get install jq

A través del método *POST* en la ruta [proyecto-rest-cpyd/v1/auth/login](http://localhost:8080/proyecto-rest-cpyd/v1/auth/login) se solicita un *token* para una aplicación válida en donde en el cuerpo del objeto *JSON*  deben ir las credenciales nombre de la aplicación *app* y la contraseña *password* correspondientes:

	curl -X POST -H "content-type: application/json;charset=utf-8" -H "accept: application/json;charset=utf-8" -d "{\"app\": \"ProyectoAppMobile\", \"password\": \"test123\"}" http://127.0.0.1:8080/proyecto-rest-cpyd/v1/auth/login | jq


Se consume a través del método *GET* en la ruta [proyecto-rest-cpyd/v1/earthquakes](http://localhost:8080/proyecto-rest-cpyd/v1/earthquakes) conservando el cuerpo de la petición anterior y se agrega en la cabecera el token con el nombre *bearer:* en el elemento *auth*:

	curl -X GET -H "content-type: application/json;charset=utf-8" -H "accept: application/json;charset=utf-8" -H "auth: bearer: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIvdjEvYXV0aC9sb2dpbiIsImlkIjoiUHJveWVjdG9BcHBNb2JpbGUiLCJleHAiOjE2MjU5OTQ3ODksImlhdCI6MTYyNTk2OTU4OSwianRpIjoiYWJjMTIzIn0.C90YLSaEXxj54f9lkf_65ySFXvTemev7FgvZlf64X4g" -d "{\"app\": \"ProyectoAppMobile\", \"password\": \"test123\"}" http://127.0.0.1:8080/proyecto-rest-cpyd/v1/earthquakes | jq



    
