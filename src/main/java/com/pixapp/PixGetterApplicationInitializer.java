package com.pixapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan("com.qminder")
public class PixGetterApplicationInitializer{
		
	   public final static void main(String[] a) {  
		 ApplicationContext context = SpringApplication.run(PixGetter.class, a);
		 PixGetter instance = context.getBean(PixGetter.class);
		 instance.startProcessing();
	   }
}
