package com.ishaan.ecommerce_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class EcommerceApiApplication {
	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load(); // loads .env file from root
		String mongoUri = dotenv.get("MONGO_URI");
		System.setProperty("spring.data.mongodb.uri", mongoUri);

		// System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
		// System.setProperty("MONGODB_URI", dotenv.get("MONGODB_URI"));
		SpringApplication.run(EcommerceApiApplication.class, args);
	}
}
