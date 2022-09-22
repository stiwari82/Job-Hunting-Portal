package com.jobhunting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobHuntingAppServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobHuntingAppServerApplication.class, args);
		
		System.out.println();
		System.out.println("<-------------Job Hunting Server Started--------------->");
	}

}
