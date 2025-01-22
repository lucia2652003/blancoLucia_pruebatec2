## **Prueba Técnica Manejo de Turnos** 

En la práctica vamos a realizar una gestión de turnos donde están presentes los trámites a realizar en ellas aplicaremos las
operaciones CRUD con una base de datos bajo el empleo de JPA (Java Persistence API) y JSP para que el usuario pueda interactuar.

## Antes de comenzar:

  1. **Clonar el directorio de GitHub**: Para eso abre el 'Símbolo de sistemas' (cmd) de tu ordenador.
      ```
        cd Desktop
        git clone https://github.com/lucia2652003/blancoLucia_pruebatec2.git
      ```
  2. **Encender el XAMPP**: Enciende MySQL y Apache (Start 'Empezar') y Admin. 
  3. **Apache Tomcat**: Un servidor web y contenerdor servlets, diseñado para realizar aplicaciones web.
      1) Descarga el [Tomcat 10.1.34.zip](https://tomcat.apache.org/download-10.cgi) Windows 64-bits. Siempre en zip.
      2) Extrae todos los archivos y coger la carpeta que contiene y llevarla al C:\jakarta10
      3) Edita las variables de entorno porque necesita JAVA_HOME si no lo tienes pon en el navegador 'Editar variables de entorno' y poner en las
         Variables de entorno > Crea una nueva bajo la sintaxis **JAVA_HOME = C:\Program Files\Java\jdk-...** sales aceptando los campos.
      4) Para probar su instalación abre en cmd en Administrador ve al directorio.
        ```
            cd C:\jakarta10\apache-tomcat-10.1.34\bin
            startup
        ```
      5) Ponemos en el navegador la url http://localhost:8080.
      6) Para detenerlo inserte **shutdown**.
  4. **Abrir proyecto**: Abrimos [Intellij IDEA](https://www.youtube.com/watch?v=eicDTFhVXxs) y comprobar si tenemos el JDK 17,
     para hacerlo funcionar (si no tienes ninguno de los dos instálalo). 
     También necesitamos el plugin Tomcat, para eso vamos a Intellij File > Settings > Plugins > **Smart Tomcat** (a secas). Aplicamos los cambios.
     Luego File > Settings > Tomcat Server > (+) > C:\jakarta10\apache-tomcat-10.1.34 aceptamos los cambios.
     Una vez hecho abrimos el app donde está la aplicación,
     así que ve a File > Open (Ctrl + O) > C:\Users\nnnn\Documents\blancoLucia_pruebatec2\app.
  5. **Workbench**: Enciéndelo y crea la conexión. 
      * [Instalar Workbench](https://support.academicsoftware.eu/hc/es/articles/360007014958-C%C3%B3mo-instalar-MySQL-Workbench)
      * [Conexión XAMPP con Workbech](https://www.youtube.com/watch?v=7ZD0D5m0jB0) 
  6. **DB (Base de datos)**: Coge el script **turnero.sql**, copia su sintaxis y lo pegas en Workbench. 
    Una vez hecho lo ejecutas pinchando en el icono del primer rayo :zap: que veas. Refresca
    DB que se encuentra en '**SCHEMAS**' pinchando en lado derecho :arrows_counterclockwise:. 
     ``` 
          -- Creación de Base de datos
          CREATE DATABASE turnero_db;
          USE turnero_db;

          -- Crear tabla de ciudadano
          CREATE TABLE ciudadano (
              id BIGINT AUTO_INCREMENT PRIMARY KEY,
              nombre VARCHAR(50) NOT NULL,
              apellido VARCHAR(50) NOT NULL
          );
 
          -- Crear la tabla turnos para la relación uno a muchos
          CREATE TABLE turno (
              id BIGINT AUTO_INCREMENT PRIMARY KEY,
              fecha DATETIME(6) NOT NULL,
              descripcion VARCHAR(255) NOT NULL,
              estado ENUM('ESPERA', 'ATENDIDO') NOT NULL,
              ciudadano_id BIGINT NOT NULL,
              FOREIGN KEY (ciudadano_id) REFERENCES ciudadano(id) ON DELETE CASCADE
          );

          -- Insertar registros en la tabla persona
             INSERT INTO ciudadano (nombre, apellido) VALUES
             ('Juan', 'Perez'),('Maria', 'Gomez'),('Carlos', 'Lopez'),
             ('Ana', 'Martinez'),('Luis', 'Garcia'),('Elena', 'Sanchez'),
             ('Fernando', 'Diaz'), ('Lucia', 'Ramirez'),('Sofia', 'Torres'),
             ('Miguel', 'Hernandez');

          -- Insertar registros en la tabla turno
             INSERT INTO turno (fecha, descripcion, estado, ciudadano_id) VALUES
             ('2025-05-12','Nutrición','ESPERA', 1), ('2025-01-25', "Dentista",'ATENDIDO', 1),
             ('2025-02-14',"Logopeda",'ESPERA', 2), ('2025-05-26',"Nutrición",'ATENDIDO', 2),
             ('2025-01-30',"Logopeda",'ESPERA', 3),('2025-10-10',"Nutrición",'ATENDIDO', 3),
             ('2025-03-28',"Logopeda",'ESPERA', 4),('2025-11-12',"Logopeda",'ESPERA', 5),
             ('2025-04-25',"Nutrición",'ATENDIDO', 6),('2025-03-20','Oculista','ESPERA', 7),
             ('2025-10-01',"Médica",'ATENDIDO', 7), ('2025-08-08',"Médica",'ESPERA', 8),
             ('2025-03-15',"Dentista",'ATENDIDO', 8), ('2025-06-21',"Nutrición",'ESPERA', 9),
             ('2025-06-24',"Oculista",'ATENDIDO', 9), ('2025-11-26',"Médica",'ESPERA', 10),
             ('2025-12-12',"Logopeda",'ATENDIDO', 10);
     ```
  7. **Tablas de DB**: Crear otro script SQL y comprueba con la consulta 'SELECT * FROM turno/ciudadano'.
  8. Comprobar los archivos de configuración. Debemos ver si los parámetros están bien.
     * **pom.xml**: Debe tener las librerías externas de Hibernate y para los servlets de Tomcat. Te mostrará el icono de Maven para refrescar
      en la parte derecha arriba del fichero para descargar las librerías. Una vez terminado, te muestra en la sección
      **External Libraries**.
          ```
            <!--Instalar las librerías externas para la conexión de una DB-->
               <dependencies>
                   <dependency>
                     <groupId>junit</groupId>
                     <artifactId>junit</artifactId>
                     <version>3.8.1</version>
                     <scope>test</scope>
                   </dependency>
                   <!-- JPA (Hibernate) -->
                   <dependency>
                     <groupId>org.hibernate</groupId>
                     <artifactId>hibernate-core</artifactId>
                     <version>6.2.7.Final</version>
                   </dependency>
                   <!-- JPA (API) -->
                   <dependency>
                     <groupId>jakarta.persistence</groupId>
                     <artifactId>jakarta.persistence-api</artifactId>
                     <version>3.1.0</version>
                   </dependency>
                   <!--Conector mysql workbench (8.0.33)-->
                   <dependency>
                     <groupId>mysql</groupId>
                     <artifactId>mysql-connector-java</artifactId>
                     <version>8.0.33</version>
                   </dependency>
                   <!-- Jakarta Servlet (API) -->
                   <dependency>
                     <groupId>jakarta.servlet</groupId>
                     <artifactId>jakarta.servlet-api</artifactId>
                     <version>5.0.0</version>
                     <scope>provided</scope>
                   </dependency>
               </dependencies>
          ```
     * **/src/main/resources/META-INF/persistence.xml**: Comprobar la base de datos, el user, la dirección IP, el puerto (3306 por defecto), el password y el persistence-unit
      porque con él podemos realizar la JPA (Java Persistence API).
          ```
           <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" version="2.1">
            <persistence-unit name="examplePU"> <!--Ojo con este elemento-->
              <class>com.ejemplo.Empleado</class>
              <properties>
                 <!--  Configuración de la base de datos  -->
                 <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
                 <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/turnero_db?serverTimezone=UTC"/>
                 <property name="javax.persistence.jdbc.user" value="root"/>
                 <property name="javax.persistence.jdbc.password" value=""/>
                 <!--  Mostrar sentencias SQL  -->
                 <property name="hibernate.show_sql" value="true"/>
                 <property name="hibernate.format_sql" value="true"/>
                 <!--  Crear las tablas automáticamente  -->
                 <property name="hibernate.hbm2ddl.auto" value="update"/>
              </properties>
            </persistence-unit>
           </persistence>
          ```
     * **/src/main/java/com/example/persistence/ConfigJPA**: Para establecer la conexión a la DB, debe coincidir con el
      persistence-unit. **¡¡No lo cambies !!**
         ```
           package com.example.persistence;

           import jakarta.persistence.EntityManager;
           import jakarta.persistence.EntityManagerFactory;
           import jakarta.persistence.Persistence;

           public class ConfigJPA {

             //Para establecer la conexión con la DB
             private static final EntityManagerFactory emf =
                                  Persistence.createEntityManagerFactory("examplePU");

             public static EntityManager getEntityManager() {
                     return emf.createEntityManager();
             }

             public static void close(){//Cerrar la sentencia
                      emf.close();
             }

           }
         ```
       
## Estructura de JPA
 En el proyecto lo dividimos en cinco directorios específicos para mejor organización 
 y limpieza de código, se encuentran en **/src/main/java/com.example**. 
 Se dividen de esta manera:
   * controllers: Métodos que se realizan bajo las operaciones CRUD sobre la entidad Ciudadano y Turno.
   * entities: Se encuentran las plantillas Turno y Ciudadano donde tiene los atributos de la tabla 
     con su tipo de dato correspondiente. Métodos para cambiar y mostrar variables. Se especifica
     anotaciones Hibernate '@'. También donde realizamos las relaciones JPA, un ciudadano puede tener varios turnos y un turno
     es único para un ciudadano (1:N). 
   * exceptions: Donde se realiza el manejo de errores por ejemplo en la validación de datos.
   * persistence: Configuración de Hibernate DB "ConfigJPA" y el mapeo para realizar las operaciones CRUD "GenericoJPA".
   * servlets: Unas clases que reciben peticiones HTTP para nosotros necesitamos GET (obtener resultados) y POST(coger parámetros)
      en las que generamos páginas dinámicas HTML. **Ojo no cambies la WebServlet**

 Luego otro directorio que es **/app/webapp** donde se almacena los JSP además empleamos lenguaje JSP y HTML. Se divide de esta manera:
  * partials: Archivos JSP que se van a reutilizar en los ficheros principales (fuera del directorio). 
    Por ejemplo la cabecera o pié de página. Nos aparece en los principales como la sintaxis 
    ````
      <%@ include file="partials/nnnn.jsp" %>
    ````
  * public: Contiene directorios donde están almacenados imágenes (image), el diseño de HTML (css) y avise lo que le va a mostrar (js).
  * index.jsp: Es el primer HTML que se nos muestra cuando iniciamos el programa.
  * turno.jsp: Listado de los turnos de los diferentes ciudadanos y empleamos sintaxis JSP para que nos lo muestren
  * formTurno.jsp: Formulario para crear los turnos.

   No cambies los nombres de los JSP ni el documento, si no nos produce errores de que no existen.
   Puedes verlos en Visual Studio Code y pero antes debes instalar la Extension JSP "EJS language support".
## ¿Cómo ejecutar?
   Una vez conectado las conexiones a la base de datos y comprobado los parámetros debemos encender la aplicación,
   para eso debemos dirigirnos al pom.xml y lo ejecutamos poniendo en el panel derecho superior una lista desplegable y poner
   'Current File' y pinchar la flecha :arrow_forward: que se encuentra al lado nos aparece otro formulario de configuración y 
   seleccionamos en un campo llamado 'USE CLASSPATH OF MODULE: /app'> Aplly > Run. Nos debe aparecer en la terminal al final una 
   URL http://localhost:8080/app pinchas ahí. Y te aparece el Home donde está la cabecera y en el navegador te muestra los items:
   * Home
   * Listado Turnos: Donde nos muestra los turnos con los ciudadanos correspondientes y el estado de cada turnos. Se filtran los turnos
<<<<<<< HEAD
     que aparecen desde la fecha asignada y el tipo de estado. Si no le pasas fecha vuelve al listado anterior. Muestra total de turnos. 
   * Creación Turnos: Donde insertamos los datos y cuando le damos a enviar nos devuelve al listado de turnos donde podemos ver o no
=======
     que aparecen desde la fecha asignada y el tipo de estado. Si no le pasamos fecha vuelve al listado anterior. Muestra total de turnos. 
   * Creación Turnos: Donde insertamos los datos y cuando le damos al botón nos devuelve al listado de turnos donde podemos ver o no
>>>>>>> 5712cc935f755639dede30233908c8a756dfbd97
     el turno del ciudadano asignado.
   Para detener el programa debemos cerrar el navegador y se detiene en el cuadrado rojo del 'Run' que se encuentra en la terminal.
     
   
