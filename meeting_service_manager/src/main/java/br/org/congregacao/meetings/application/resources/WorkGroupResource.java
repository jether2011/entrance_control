package br.org.congregacao.meetings.application.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.congregacao.meetings.resource.locals.LocalsGateway;
import br.org.congregacao.meetings.resource.locals.response.WorkGroupResponse;

@RestController
@RequestMapping(value = "/api/v1/workgroups")
public class WorkGroupResource {
	
	@Autowired
	private LocalsGateway localsGateway;
	
	@GetMapping
	public ResponseEntity<List<WorkGroupResponse>> findAll() {
		return ResponseEntity.ok().body(localsGateway.findAllWorkGroups());
	}

}
