package com.ub;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class LinkedIn {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LinkedIn.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(LinkedIn.class, args);
	}

}
