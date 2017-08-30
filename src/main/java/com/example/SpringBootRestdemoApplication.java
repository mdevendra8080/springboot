package com.example;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jndi.JndiObjectFactoryBean;

@SpringBootApplication
public class SpringBootRestdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestdemoApplication.class, args);
	}
	
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {

		return new TomcatEmbeddedServletContainerFactory() {

			@Override
			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(
					Tomcat tomcat) {
				// TODO Auto-generated method stub
				tomcat.enableNaming();
				return super.getTomcatEmbeddedServletContainer(tomcat);
			}

			@Override
			protected void postProcessContext(Context context) {
										
				ContextResource resource = new ContextResource();
				resource.setName("jdbc/custread");
				resource.setProperty("factory",
						"org.apache.commons.dbcp.BasicDataSourceFactory");
				resource.setType(DataSource.class.getName());
				resource.setProperty("driverClassName",
						"oracle.jdbc.OracleDriver");
				resource.setProperty("url",
						"jdbc:oracle:thin:@localhost:1521:orcl");
				resource.setProperty("password", "tiger");
				resource.setProperty("username", "scott");				

				context.getNamingResources().addResource(resource);
			}
		};

	}
	
	@Bean(destroyMethod = "")
	public DataSource jndiDataSource() throws IllegalArgumentException,
			NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName("jdbc/custread");
		bean.setResourceRef(true);
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(true);
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();
	}
}

/*

For testing please refer below steps :

1] HTTP port number set to 9090 into application.properties file.

2] Create Customer table in alertcore .

CREATE TABLE CUSTOMER (
CUSTOMER_ID NUMBER(10) ,
NAME VARCHAR2(100),
EMAIL VARCHAR2(100),
CREATED_DATE VARCHAR2(100),
CONSTRAINT "CUSTOMER_ID_PK" PRIMARY KEY (CUSTOMER_ID));

3] Test end point to create customer :
                End point : http://localhost:9090/customer/new
                JSON Request  :  {"id":70,"name":"DEVENDRA","email":"abc@com","date":"2017-07-19"} 
                REQUEST METHOD : POST 

4] To resolve the JNDI naming require below dependency .
<dependency>
                                                <groupId>commons-dbcp</groupId>
                                                <artifactId>commons-dbcp</artifactId>
                                </dependency>

*/
