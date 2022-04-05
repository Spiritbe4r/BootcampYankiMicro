package com.bootcamp.yankiservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class YankiServiceApplication {

	@Value("${microservices-urls.api-debitcard}")
	private String apiDebitcard;

	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder
					.baseUrl(apiDebitcard)
					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.build();
	}


	public static void main(String[] args) {
		SpringApplication.run(YankiServiceApplication.class, args);
	}

}
