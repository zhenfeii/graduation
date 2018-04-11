package com.gdky.graduation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ServletComponentScan(basePackages={"com.gdky.restful","com.gdky.graduation"})
@ComponentScan(basePackages={"com.gdky.restful","com.gdky.graduation"})
@SpringBootApplication
public class GraduationApplication {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(GraduationApplication.class, args);
	}
}
