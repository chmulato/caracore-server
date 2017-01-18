package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by jarbas on 05/10/15.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.example.demo.controllers,com.example.demo.services,com.example.demo.security")
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
