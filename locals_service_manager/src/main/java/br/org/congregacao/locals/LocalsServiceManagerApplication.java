package br.org.congregacao.locals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "br.org.congregacao.locals")
@EntityScan("br.org.congregacao.locals.domain")
@EnableDiscoveryClient
public class LocalsServiceManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalsServiceManagerApplication.class, args);
	}

}
