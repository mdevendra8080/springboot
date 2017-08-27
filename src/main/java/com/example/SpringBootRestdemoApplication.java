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
				resource.setProperty("password", "scott");
				resource.setProperty("username", "tiger");				

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
