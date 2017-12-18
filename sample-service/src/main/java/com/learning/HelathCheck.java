package com.learning;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HelathCheck implements HealthIndicator {

	private int code = 0;
	
	@Override
	public Health health() {
		System.out.println("count: " + code);
		if(code > 4 && code < 8) {
			code++;
			return Health.down().withDetail("Key", code).build();
		}
		code++;
		return Health.up().build();
	}
	
}
