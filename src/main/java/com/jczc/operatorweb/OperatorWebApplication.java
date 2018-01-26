package com.jczc.operatorweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan("com.jczc.operatorweb.dao")
@SpringBootApplication
public class OperatorWebApplication {

	public static void main(String[] args) {

		SpringApplication.run(OperatorWebApplication.class, args);
		
	}
}
