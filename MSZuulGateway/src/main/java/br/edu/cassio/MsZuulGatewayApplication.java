package br.edu.cassio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MsZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsZuulGatewayApplication.class, args);
	}

}
