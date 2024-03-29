package ru.otus.lyamin.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static ru.otus.lyamin.app.util.OpenPageUtil.openHomePage;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		openHomePage();
	}

}
