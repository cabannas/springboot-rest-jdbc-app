# Proyecto Aplicación SpringBoot con RequestMapping y JDBC para PostgreSQL

Proyecto desarrollado en Java para el despliegue de una aplicación Spring que aúna RequestMapping para servicios REST y JDBC para gestionar una base de datos. En este proyecto, JDBC está configurado para una base de datos PostgreSQL.

## Despliegue de la aplicación

1. Descargar el código fuente de GIT:

[SPRINGBOOTJDBC-GIT](https://github.com/cabannas/springbootrestjdbc-master/)

2. Importar el proyecto en el IDE (en este caso he usado eclipse debido a las facilidades que otorga a la hora de lanzar aplicaciones Spring)

3. Crear una base de datos en PostgreSQL que cumpla con los parámetros establecidos en el proyecto importado.

    3.1. Estos parámetros están definidos dentro de "application.properties", que se encuentra dentro de "src/main/resources". Los parámetros definidos por defecto son los siguientes:
- Nombre de la base de datos: testrequestmap
- Propietario: postgres
- Puerto que usa postgreSQL: 5432 (es el que viene por defecto)

    3.2. Para gestionar y controlar la base de datos PostgreSQL yo he utilizado PgAdmin4.

4. Compilar el proyecto con Maven, con párametros específicos de prelimpieza e instalación (clean install) y saltando la ejecución de los tests de pruebas:

    4.1. Es necesario saltarse los test debido a que éstos no se han desarrollado. Esto se puede hacer marcando la opción "[x] Skip Tests" o especificándolo en los parámetros de lanzamiento si estás en Intellij → https://stackoverflow.com/questions/32006351/intellij-idea-14-how-to-skip-tests-while-deploying-project-into-tomcat.

5. Ejecutar el proyecto como Springboot Application.

## Funcionamiento de la Aplicación

La aplicación está configurada para generar y gestionar datos en una base de datos.

Al iniciar la aplicación, ésta crea una tabla en la base de datos llamada Customer, que trata clientes con los siguientes atributos:

* customerId - El identificador del cliente de tipo Integer.
* name - El nombre del cliente de tipo String.
* age - La edad del cliente de tipo Integer.

La aplicación permite realizar las siguientes operaciones de tipo REST:

(Se ha utilizado el puerto 8081, esto es modificable en "application.properties" como se puede ver en la imagen del tercer paso del despliegue de la aplicación)
(No se ha implementado una interfaz para realizar los métodos POST, PUT y DELETE. Se ha utilizado Postman para manipular este tipo de métodos)

* Método GET con un customerId como parámetro: devuelve un JSON con la información del cliente cuyo identificador coincida con el customerId.

    Sintaxis: "localhost:8081/customers/2"

* Método GET sin parámetros: devuelve un JSON con todos los clientes actuales en la base de datos.

    Sintaxis: "localhost:8081/customers"

* Método POST: realiza un post de un cliente, el cual se gestionará introduciéndolo en la base de datos.

    Sintaxis: "localhost:8081/customers/post"

* Método PUT: realiza un put de un cliente, actualizando la información de éste, utilizando el identificador como clave para actualizar al cliente.

    Sintaxis: "localhost:8081/customers/edit/2"

* Método DELETE: borra un cliente de la base de datos, a partir de su identificador.

    Sintaxis: "localhost:8081/customers/delete/2"


La aplicación cuenta un una serie de controles de errores muy limitados, no se ha implementado ningún tipo de tratamiento o gestión de errores de tipo "bad input".


## Articulos relacionados y referencias:

[How to use Spring JDBC Template with Spring Boot for Postgres DataBase](https://grokonez.com/spring-framework/spring-boot/how-to-use-jdbc-template-with-spring-boot-for-postgres-database)

[Spring Framework 4.3 New Feature RequestMapping: @GetMapping, @PostMapping, @PutMapping, @DeleteMapping](https://grokonez.com/spring-framework/spring-boot/spring-framework-4-3-new-feature-requestmapping-getmapping-postmapping-putmapping-deletemapping)
