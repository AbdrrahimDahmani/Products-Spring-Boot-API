package com.example.productsApp;

import com.example.productsApp.Controllers.ProductController;
import com.example.productsApp.Services.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;

@SpringBootApplication
@EnableSwagger2
public class ProductsAppApplication {
	public static void main(String[] args) {
		new File(ProductService.uploadDirectory).mkdir();
		SpringApplication.run(ProductsAppApplication.class, args);
	}


}
