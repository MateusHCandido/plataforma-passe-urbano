package com.api.gateway.servico_de_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServicoDeGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoDeGatewayApplication.class, args);
	}

}
