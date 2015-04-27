# Simple Spring-Maven RESTful Web service
This is a simple example of a **RESTful** Web service written in **Java** using **Spring** framework and **Maven**.
The IDE used is **STS** (Spring Tool Suite).

### How to create project in STS
1. New Maven Project > Create a simple project (skip archetype selection), packaging: war
2. Right click project > Spring Tools > Add Spring Project Nature
3. Right click project > Properties > Libraries > Remove *JRE System Library* > Add Library > again *JRE System Library* > java-7-oracle (or any other)
4. Edit *pom.xml*
5. src > main > webapp > Create new directory (folder) called *WEB-INF* > inside WEB-INF create XML file *web.xml*
6. Create base package in *src/main/java* called my.rest.test (or any other)
7. Edit *web.xml* and add Configuration classes and files
8. Write a simple controller in *my.rest.test.controller* package to test the Web service
9. Right click project > Run As > Run on Server > *Pivotal tc Server* (or Tomcat)

### Editing pom.xml
```xml
    <properties>
		<spring.version>4.0.5.RELEASE</spring.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${spring.version}</version>
			<exclusions>
				<exclusion>
					<artifactId>commons-logging</artifactId>
					<groupId>commons-logging</groupId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>commons-logging</groupId>
			<artifactId>commons-logging</artifactId>
			<version>1.2</version>
		</dependency>
	</dependencies>

	<build>
		<finalName>simple_spring_maven_restful</finalName>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.1</version>
				<configuration>
					<source>1.7</source>
					<target>1.7</target>
					<encoding>UTF-8</encoding>
				</configuration>
			</plugin>
		</plugins>
	</build>
```

### Editing web.xml
```xml
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
	xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
	http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	id="simple_spring_maven_restful" version="3.0">

    <display-name>Simple Spring Maven RESTful</display-name>
    
    <listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

</web-app>
```

###### Add Application context (Application configuration) information into web.xml
```xml
	<context-param>
		<param-name>contextClass</param-name>
		<param-value>
			org.springframework.web.context.support.AnnotationConfigWebApplicationContext
		</param-value>
	</context-param>
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>my.rest.test</param-value>
	</context-param>
```

Add Application configuration class into *my.rest.test.config* package.
```java
@Configuration
@ImportResource( { "classpath*:/app_config.xml" } )
@ComponentScan( basePackages = "my.rest.test" )
public class AppConfig {
}
```
Create app_config.xml 

###### Add Web context (Web configuration) information into the web.xml
```xml
    <servlet>
		<servlet-name>spring</servlet-name>
		<servlet-class>
			org.springframework.web.servlet.DispatcherServlet
		</servlet-class>
		<init-param>
			<param-name>contextClass</param-name>
			<param-value>
				org.springframework.web.context.support.AnnotationConfigWebApplicationContext
			</param-value>
		</init-param>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>my.rest.test</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>spring</servlet-name>
		<url-pattern>/*</url-pattern>
	</servlet-mapping>
```

Add Web configuration class into *my.rest.test.config* package.
```java
@Configuration
@EnableWebMvc
public class WebConfig{
}
```

### Writing simple controller in *my.rest.test.controller*
```java
@Controller
public class TestController {
	@RequestMapping( value = "/greet", method = RequestMethod.GET )
	@ResponseBody
	public String hello()
	{
		return "Hello!";
	}
}
```
