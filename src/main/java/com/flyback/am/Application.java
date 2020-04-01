
package com.flyback.am;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "com.flyback.am")
@EnableConfigurationProperties
public class Application {

	public static void main(final String[] args) {
		

		SpringApplication.run(Application.class, args);
	}

	

}
