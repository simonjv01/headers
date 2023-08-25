package com.example.headers;

import com.example.headers.service.JsonPlaceHolderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Map;

@SpringBootApplication
public class HeadersApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeadersApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(JsonPlaceHolderService client) {
		return args -> client.findAll(Map.of("X-POWERED-BY", "Spring Framework 6"));

	}

	@Bean
	JsonPlaceHolderService jsonPlaceHolderService() {
		WebClient client = WebClient.builder()
				.baseUrl("https://jsonplaceholder.typicode.com")
				.defaultHeader("SPRING-BOOT-VERSION","3.1.0")
				.exchangeStrategies(ExchangeStrategies.builder().codecs(c -> c.defaultCodecs().enableLoggingRequestDetails(true)).build())
				.build();
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
		return factory.createClient(JsonPlaceHolderService.class);
	}

}
