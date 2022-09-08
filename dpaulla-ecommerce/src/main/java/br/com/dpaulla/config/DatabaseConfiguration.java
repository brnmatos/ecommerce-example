package br.com.dpaulla.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.driver}")
	private String driver;
	
	@Value("${spring.datasource.username}")
	private String user;
	
	@Value("${spring.datasource.password}")
	private String passwd;
	
}
