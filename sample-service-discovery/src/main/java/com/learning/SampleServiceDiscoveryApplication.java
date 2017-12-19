package com.learning;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@EnableEurekaClient
@RestController
@SpringBootApplication
@EnableCircuitBreaker
public class SampleServiceDiscoveryApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getTemlate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="fallback")
	@RequestMapping("/test")
	@SuppressWarnings("rawtypes")
	public String test() {
		List list = restTemplate.getForObject("http://sample-service/fetch", List.class);
		return list.toString();
	}
	
	public String fallback() {
		return "dafult val";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SampleServiceDiscoveryApplication.class, args);
	}
}
