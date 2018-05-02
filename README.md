# Ejemplo aplicación web Java EE 7 con Maven

Proyecto Maven que muestra un ejemplo mínimo de aplicación web sobre Java EE 7.

## Contenido

Este es un proyecto Maven. Esto quiere decir que se tiene un archivo `pom.xml` que contiene una definición del proyecto.

Además, como proyecto Maven, se tienen carpetas separadas para el código fuente que se debe incluir en los archivos `.jar` y para las pruebas

| Carpeta        | Descripción                |
| :------------- | :------------------------- |
| `src/main/java`    | Clases principales en Java        |
| `src/main/webapp`  | Archivos de la aplicación web     |

El proyecto contiene unas clases y archivos de interés:

| en el `src/main/java`         | Descripción                        |
| :-------------------------------- | :--------------------------------- |
| `ejemplo.web.servicio.ServicioClima.java`   | Servicio de lógica de negocio de ejemplo |
| `ejemplo.web.presentacion.Index.java`        | Modelo (datos) observable desde las pantallas y que se conecta al servicio |

| en el `src/main/webapp`         | Descripción                        |
| :-------------------------------- | :--------------------------------- |
| `index.xhtml`          | Página (pantalla) de prueba |
| `WEB-INF/beans.xml`  | Archivo de configuración que indica al servidor a buscar las clases con anotaciones |
| `WEB-INF/web.xml`     | Archivo de configuración que define la configuración de JSF y la extensión de las pantallas | 


## Pantallas usando `JSF`

En este proyecto, las pantallas son construídas usando la tecnología de Java Server Faces (JSF)

En el archivo `web.xml` se define la extensión de los archivos usados en la pantalla

```
    <servlet-mapping>
        <servlet-name>Faces Servlet</servlet-name>
        <url-pattern>*.xhtml</url-pattern>
    </servlet-mapping>
    <welcome-file-list>
        <welcome-file>index.xhtml</welcome-file>
    </welcome-file-list>    
```

El archivo `index.xhtml` incluye referencias a los elementos del modelo que exponen la aplicación Java. En este caso, se usa `index` para referenciar a la clase `Index`. El atributo `prediccion` hace referencia al método `getPrediccion()`.

```
    <h:body>
        Predicción: #{index.prediccion}
    </h:body>
```

La clase `ejemplo.web.presentacion.Index` esta anotado con `@Model` para ser usado desde la pantalla. La clase contiene un `@Inject` que conecta automáticamente el Modelo al servicio.

```
@Model
public class Index {

	@Inject
	ServicioClima servicioClima;
	:
}
```

La clase `ejemplo.web.servicio.ServicioClima` están definidos como servicios sin estado mediante la anotación `@Stateless`. El servidor de aplicaciones genera instancias y conecta de forma automática los servicios.

```
@Stateless
public class ServicioClima {
	:
}
```

## Configuración en el `pom.xml`

En el `pom.xml` se tiene una dependencia a las librerías de Java EE.

```
	<!--  Java EE 7 -->
   <dependency>
   		<groupId>javax</groupId>
      <artifactId>javaee-api</artifactId>
      <version>7.0</version>
      <scope>provided</scope>
	</dependency>
```

Con el fin de poder ejecutar el proyecto sin tener pre-instalado un servidor de aplicaciones, el `pom.xml` incluye un plugin de wildfly.

```
	<!-- para ejecutar la aplicación en Wildfly -->
   <plugin>
		<groupId>org.wildfly.plugins</groupId>
      <artifactId>wildfly-maven-plugin</artifactId>
      <version>1.2.1.Final</version>
	</plugin> 
```


## Ejecución

El proyecto es una aplicación web con una sola pantalla. Es posible ejecutar la aplicación desde la línea de comandos usando Maven.

```bash
mvn wildfly:run
```

NOTA: Este comando descarga un servidor de aplicaciones (+150MB) durante la primera ejecución.

Si usa Eclipse, es posible instalar un servidor de aplicaciones y ejecutar el proyecto haciendo click-derecho sobre el proyecto y seleccionando `Run As > Run on Server`.  La aplicación debe ser visible en `http://localhost:8080/ejemplo-web/`

