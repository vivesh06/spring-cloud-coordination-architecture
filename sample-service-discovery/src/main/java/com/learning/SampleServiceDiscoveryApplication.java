package com.learning;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@RestController
@SpringBootApplication
public class SampleServiceDiscoveryApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getTemlate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/test")
	@SuppressWarnings("rawtypes")
	public String test() {
		List list = restTemplate.getForObject("http://sample-service/fetch", List.class);
		return list.toString();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SampleServiceDiscoveryApplication.class, args);
	}
}
