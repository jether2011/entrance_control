package br.org.congregacao.meetings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "br.org.congregacao.meetings")
@EntityScan("br.org.congregacao.meetings.domain")
@EnableFeignClients
@EnableDiscoveryClient
public class MeetingServiceManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetingServiceManagerApplication.class, args);
	}

}
