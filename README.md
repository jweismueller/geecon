=======================================================================
Geecon Conferencing App
=======================================================================


System Requirements
-------------------

The application was developed and tested with the following configuration:

#### Runtime
- Java JDK 1.7
- JBoss AS 7.2.0 (JBoss 7.1.1 may be compatible)
  - Use -Djboss.server.default.config=standalone-full.xml system property to enable HornetQ message broker
- Maria DB 5.5.32 (MySQL 5.5.* is fully compatible)

#### Development
- Maven 3.0.5 
- Eclipse Kepler Build id: 20130614-0229 with WTP and JBoss Tools
- IntelliJ WebStorm 7.0.1 


Deployment
----------

#### Database
Create a database `geecon` in your MySQL instance. 

#### Datasource
Configure a datasource for your MySQL instance inside `standalone-full.xml` like this:

	<datasource jndi-name="java:jboss/datasources/GeeconDS" pool-name="GeeconDS" enabled="true" use-java-context="true">
		<connection-url>jdbc:mariadb://localhost:3308/geecon</connection-url>
		<driver>mariadb</driver>
		<security>
			<user-name>root</user-name>
		</security>
	</datasource>
	
	<driver name="mariadb" module="org.mariadb">
		<xa-datasource-class>org.mariadb.jdbc.MySQLDataSource</xa-datasource-class>
	</driver>

#### Artifact

Place geecon-ear-x.x.x.ear in the JBoss deployment folder. In a standalone environment you find it at `$JBOSS_HOME/standalone/deployments`.



Verification
----------

The following test assumes that your JBoss HTTP Connector is running at port 8080 and you have a binding to 127.0.0.1 (localhost). 

The execution of

    curl http://localhost:8080/geecon-rest/conferences/

returns a list of all conferences which are saved in the database. 

