package com.tsys.inventory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class InventoryAPI {
	@GetMapping(path = "stock")
	@HystrixCommand(fallbackMethod = "getStockCount2")
	public double getStockCount() {
		// i was trying to get other service or db or something and it failed
		return Math.random() * System.currentTimeMillis();
		// throw new RuntimeException();
	}
	public double getStockCount2() {
		return 2;
	}
}
