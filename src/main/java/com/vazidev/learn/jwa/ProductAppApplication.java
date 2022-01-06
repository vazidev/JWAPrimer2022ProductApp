package com.vazidev.learn.jwa;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/***
 * sets up a default config
 * starts spring application context
 * performs class path scan
 * starts Tomcat server
 * @author nganga
 *
 */


@SpringBootApplication
public class ProductAppApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ProductAppApplication.class, args);
		String beans[] = ctx.getBeanDefinitionNames();
		Arrays.sort(beans);
		
		for(String beanNames:beans) {
			System.out.println(beanNames);
		}
	}
	
	@Bean 
	public RestTemplate getRestTemplate() {  //Allows auto generation of the new Beans
		return new RestTemplate();
	}

}
