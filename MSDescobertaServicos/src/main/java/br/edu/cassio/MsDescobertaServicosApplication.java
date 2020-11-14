package br.edu.cassio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsDescobertaServicosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsDescobertaServicosApplication.class, args);
	}

}