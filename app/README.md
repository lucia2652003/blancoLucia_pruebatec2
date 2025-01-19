## **Prueba Técnica Manejo de Turnos** 

En la práctica vamos a realizar la 

## Antes de comenzar:

  1. **Clonar el directorio de GitHub**: Para eso abre el 'Símbolo de sistemas' (cmd) de tu ordenador.
      ```
        cd Desktop
        git clone https://github.com/lucia2652003/blancoLucia_pruebatec2.git
      ```
  2. **Tomcat**:  
  3. **Abrir proyecto**: Abrimos [Intellij IDEA](https://www.youtube.com/watch?v=eicDTFhVXxs) y comprobar si tenemos el JDK 17,
     para hacerlo funcionar (si no tienes ninguno de los dos instálalo).
     Una vez hecho abrimos el proyecto Maven donde está la aplicación, 
     así que ve a File > Open (Ctrl + O) > C:\Users\nnnn\Documents\blancoLucia_pruebatec2\app.
  3. **Encender el XAMPP**: Enciende MySQL y Apache (Start 'Empezar') y Admin.
  4. **Workbench**: Enciéndelo y crea la conexión. 
      * [Instalar Workbench](https://support.academicsoftware.eu/hc/es/articles/360007014958-C%C3%B3mo-instalar-MySQL-Workbench)
      * [Conexión XAMPP con Workbech](https://www.youtube.com/watch?v=7ZD0D5m0jB0) 
  5. **DB (Base de datos)**: Coge el script **turnero.sql**, copia su sintaxis y lo pegas en Workbench. 
    Una vez hecho lo ejecutas pinchando en el icono del primer rayo :zap: que veas. Refresca
    DB  que se encuentra en '**SCHEMAS**' pinchando en lado derecho :arrows_counterclockwise:. 
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
  6. **Tabla empleado**: Crear otro script SQL y comprueba con la consulta 'SELECT * FROM empleado'.
  7. Comprobar los archivos de configuración. Debemos ver si los parámetros están bien.
     * **pom.xml**: Debe tener las librerías externas de Hibernate. Te mostrará el icono de Maven para refrescar
      en la parte derecha arriba del fichero para descargar las librerías. Una vez terminado, te muestra en la sección
      **External Libraries**.
          ```
            <!--Instalar las librerías externas para la conexión de una DB-->
               <dependencies>
                 <!--  JPA (Hibernate)  -->
                 <dependency>
                     <groupId>org.hibernate</groupId>
                     <artifactId>hibernate-core</artifactId>
                     <version>6.2.7.Final</version>
                 </dependency>
                 <!--  JPA API  -->
                 <dependency>
                    <groupId>jakarta.persistence</groupId>
                    <artifactId>jakarta.persistence-api</artifactId>
                    <version>3.1.0</version>
                 </dependency>
                <!-- Conector mysql workbench 8.0.33 -->
                <dependency>
                    <groupId>mysql</groupId>
                    <artifactId>mysql-connector-java</artifactId>
                    <version>8.0.33</version>
                </dependency>
               </dependencies>
          ```
     * **/src/main/resources/META-INF/persistence.xml**: Comprobar la base de datos, el user, la dirección IP, el puerto (3306 por defecto), el password y el persistence-unit
      porque con él podemos realizar la JPA (Java Persistence API).
          ```
           <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" version="2.1">
            <persistence-unit name="gt_empleados"> <!--Ojo con este elemento-->
              <class>com.ejemplo.Empleado</class>
              <properties>
                 <!--  Configuración de la base de datos  -->
                 <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
                 <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/empleados?serverTimezone=UTC"/>
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
     * **/src/main/java/org/example/persistence/ConfigJPA**: Para establecer la conexión a la DB empleados, debe coincidir con el
      persistence-unit. **¡¡No lo cambies !!**
         ```
           package org.example.persistence;

           import jakarta.persistence.EntityManager;
           import jakarta.persistence.EntityManagerFactory;
           import jakarta.persistence.Persistence;

           public class ConfigJPA {

             //Para establecer la conexión con la DB
             private static final EntityManagerFactory emf =
                                  Persistence.createEntityManagerFactory("gt_empleados");

             public static EntityManager getEntityManager() {
                     return emf.createEntityManager();
             }

             public static void close(){//Cerrar la sentencia
                      emf.close();
             }

           }
         ```
       
## Estructura de JPA
 En el proyecto lo dividimos en tres directorios específicos para mejor organización 
 y limpieza de código, se encuentran en **/src/main/java/org.example**. 
 Se dividen de esta manera:
   * controllers: Métodos que se realizan bajo las operaciones CRUD sobre la entidad Empleado.
   * entities: Se encuentran la plantilla de Empleado donde tiene los atributos de la tabla 
     con su tipo de dato correspondiente. Métodos para cambiar y mostrar variables. Se especifica
     anotaciones Hibernate '@'.
   * persistence: Configuración de Hibernate DB "ConfigJPA" y el mapeo para realizar las operaciones CRUD "EmpleadoJPA".
   * Main: Fichero en donde interactuaremos todo el rato. Introduciendo datos mientras que la app 
     realizará la entrada y salida de datos, la validación de datos y las operaciones CRUD. 

## ¿Cómo ejecutar?
   Una vez conectado las conexiones a la base de datos y comprobado los parámetros debemos encender la aplicación,
   para eso debemos dirigirnos al Main y lo ejecutamos poniendo en el panel derecho superior una lista desplegable y poner
   'Current File' y pinchar la flecha :arrow_forward: que se encuentra al lado.  Al principio nos pide nuestro nombre por teclado y luego nos ofrece
   diferentes opciones en las que debemos escoger tecleando un número, si no está en ese rango nos pide volver a introducirlo, también en la 
   entrada de datos cuando insertemos un empleado o lo actualicemos.
   Para detener el programs debemos pulsar -1.
     
   
