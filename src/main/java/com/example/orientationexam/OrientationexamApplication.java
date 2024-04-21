package com.example.orientationexam;

import com.example.orientationexam.models.User;
import com.example.orientationexam.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrientationexamApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrientationexamApplication.class, args);
	}

	@Bean
	public CommandLineRunner clr(UserRepository ur) {
		return args-> {
			var user1 = User.builder().name("Attila").group_id(null).build();
			ur.save(user1);
			var user2 = User.builder().name("Béla").group_id(null).build();
			ur.save(user2);
			var user3 = User.builder().name("Csaba").group_id(null).build();
			ur.save(user3);
			var user4 = User.builder().name("Dani").group_id(null).build();
			ur.save(user4);
		};
	}

}
