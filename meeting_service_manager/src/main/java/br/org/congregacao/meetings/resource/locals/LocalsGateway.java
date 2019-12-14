package br.org.congregacao.meetings.resource.locals;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.org.congregacao.meetings.resource.locals.response.AdministrationResponse;

@FeignClient("locals")
public interface LocalsGateway {

	@GetMapping("/api/v1/administrations/{id}")
	AdministrationResponse findAdministrationById();
	
}
