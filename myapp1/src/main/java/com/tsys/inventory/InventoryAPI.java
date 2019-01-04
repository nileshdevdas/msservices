package com.tsys.inventory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryAPI {
	@GetMapping(path = "stock")
	public double getStockCount() {
		return Math.random() * System.currentTimeMillis();
	}
}
