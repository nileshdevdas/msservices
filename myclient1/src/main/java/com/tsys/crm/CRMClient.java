package com.tsys.crm;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.ribbon.Ribbon;

@RestController
public class CRMClient {

	@Autowired
	DiscoveryClient client;

	Ribbon ribbon; 
	@GetMapping(path = "order")
	public String placeOrder() {
		String returnResult = null;
		RestTemplate restTemplate = new RestTemplate();
		// hardcoded single client 
		// return " Result from " +
		// restTemplate.getForObject("http://localhost:7001/stock", String.class);
		
		// dynamic discovery 
		List<ServiceInstance> instances = client.getInstances("INVENTORY");
		for (Iterator iterator = instances.iterator(); iterator.hasNext();) {
			ServiceInstance serviceInstance = (ServiceInstance) iterator.next();
			returnResult = restTemplate.getForObject(serviceInstance.getUri() + "/stock", String.class);
			break;
		}
		return returnResult;
	}
}
