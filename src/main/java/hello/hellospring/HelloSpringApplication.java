package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	// main method를 실행하면 SpringBootApplication이 실행된다
	// WebServer인 TOMCAT을 내장하고 있다
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
