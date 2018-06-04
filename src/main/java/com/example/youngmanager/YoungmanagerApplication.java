package com.example.youngmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.youngmanager.*.mapper")
public class YoungmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoungmanagerApplication.class, args);
	}
}
