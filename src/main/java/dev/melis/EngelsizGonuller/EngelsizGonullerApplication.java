package dev.melis.EngelsizGonuller;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
	//(exclude = { SecurityAutoConfiguration.class })
public class EngelsizGonullerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EngelsizGonullerApplication.class, args);


	}
}
