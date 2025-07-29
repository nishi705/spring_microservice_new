package com.programmingtechie.discovery_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/*
while trying to make a call to the discovery server, client will store a local copy of the
registor


Api gateway takes care about the load balancer as well
so if the product service will have may instances and if the user makes a call then api gateway will
make sure which instance of the product service will have to connect and will get the response
 */

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServiceApplication.class, args);
	}

}
