package com.Dushyant.E_Commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.Dushyant.E_Commerce.Service.ProductService;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ECommerceApplication.class, args);
		//ProductService s  = context.getBean(ProductService.class);
		
	}

}
