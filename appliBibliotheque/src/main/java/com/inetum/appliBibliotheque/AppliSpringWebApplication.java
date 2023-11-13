package com.inetum.appliBibliotheque;

import java.util.ResourceBundle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppliSpringWebApplication {

	public static void main(String[] args) {
		
		String typeDeBase=
				"oracle";
				//"mysql";
				//"h2";
		
		System.setProperty("spring.profiles.active", typeDeBase+",withSecurity"); //init

		SpringApplication.run(AppliSpringWebApplication.class, args);
		
		
		ResourceBundle props = ResourceBundle.getBundle("application");
		String port = props.getString("server.port");
		String  context = props.getString("server.servlet.context-path");
		System.out.println("http://localhost:"+port+context); //le / est déjà inclus dans le context
	}

}