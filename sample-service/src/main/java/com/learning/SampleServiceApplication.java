package com.learning;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SampleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleServiceApplication.class, args);
	}
	
	@RequestMapping("/fetch")
	public List<String> getList() {
		return Arrays.asList("a", "b", "c");
	}
}
