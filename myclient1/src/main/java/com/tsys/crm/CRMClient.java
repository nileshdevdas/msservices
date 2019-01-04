package com.tsys.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RibbonClient(name = "lb")
public class CRMClient {

	@Autowired
	DiscoveryClient client;

	@Autowired
	private LoadBalancerClient loadBalancer;

	@GetMapping(path = "order")
	public String placeOrder() {
		String returnResult = null;
		RestTemplate restTemplate = new RestTemplate();
		ServiceInstance ist = loadBalancer.choose("INVENTORY");
		returnResult = restTemplate.getForObject(ist.getUri() + "/stock", String.class);
		System.out.println(returnResult);
		return returnResult;
	}
}

//hardcoded single client
// return " Result from " +
// restTemplate.getForObject("http://localhost:7001/stock", String.class);
//		for (Iterator iterator = instances.iterator(); iterator.hasNext();) {
//			ServiceInstance serviceInstance = (ServiceInstance) iterator.next();
//			returnResult = restTemplate.getForObject(serviceInstance.getUri() + "/stock", String.class);
//			break;
//		}
