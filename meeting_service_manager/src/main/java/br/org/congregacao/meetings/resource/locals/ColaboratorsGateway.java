package br.org.congregacao.meetings.resource.locals;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.org.congregacao.meetings.resource.locals.response.ColaboratorResponse;

@FeignClient("users")
public interface ColaboratorsGateway {
	
	@GetMapping("/api/v1/colaborators/{cardNumber}")
	ColaboratorResponse findColaboratorByCardNumber();

}
