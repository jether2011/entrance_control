package br.org.congregacao.meetings.application.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.congregacao.meetings.resource.locals.LocalsGateway;
import br.org.congregacao.meetings.resource.locals.response.ChurchMeetingRoomsResponse;

@RestController
@RequestMapping(value = "/api/v1/churches")
public class ChurchResource {
	
	@Autowired
	private LocalsGateway localsGateway;
	
	@GetMapping("/{id}/meeting-rooms")
	public ResponseEntity<ChurchMeetingRoomsResponse> findChurchById(@PathVariable("id") final String id) {
		return ResponseEntity.ok()
				.body(localsGateway.findMeetingRoomsByChurchId(id));
	}

}
