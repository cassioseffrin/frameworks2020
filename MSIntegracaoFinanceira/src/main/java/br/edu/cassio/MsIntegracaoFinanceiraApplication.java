package br.edu.cassio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsIntegracaoFinanceiraApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsIntegracaoFinanceiraApplication.class, args);
	}

}
