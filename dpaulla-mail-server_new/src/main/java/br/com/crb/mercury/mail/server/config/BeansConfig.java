package br.com.crb.mercury.mail.server.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

	private ExecutorService executor = Executors.newCachedThreadPool();
	
	@Bean
	public ExecutorService executor(){
		return this.executor;
	}
}
