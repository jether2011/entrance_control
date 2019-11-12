package br.org.congregacao.locals.application.resources;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.org.congregacao.locals.application.resources.request.ChurchRequest;
import br.org.congregacao.locals.domain.Church;
import br.org.congregacao.locals.domain.MeetingRoom;
import br.org.congregacao.locals.service.ChurchService;
import br.org.congregacao.locals.service.MeetingRoomService;

@RestController
@RequestMapping(value = "/api/v1/churches")
public class ChurchResource implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ChurchService churchService;
	@Autowired
	private MeetingRoomService meetingRoomService;
	
	@GetMapping
	public ResponseEntity<List<Church>> findAll() {
		return ResponseEntity.ok().body(churchService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Church> findOne(@PathVariable final String id) {
		final Optional<Church> optionalChurch = churchService.findById(id);
		
		if (optionalChurch.isPresent()) {
			return ResponseEntity.ok().body(optionalChurch.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
    public ResponseEntity<Church> create(@RequestBody @Valid final ChurchRequest request, final UriComponentsBuilder uriBuilder) {
        final Church church = ChurchRequest.from(request);
        churchService.save(church);

        final URI uri = uriBuilder.path("/api/v1/churches/{id}").buildAndExpand(church.getId()).toUri();
        return ResponseEntity.created(uri).body(church);
    }
	
	@PatchMapping("/{id}/meeting-room/{meetingRoomId}")
	public ResponseEntity<Church> addMeetingRoom(@PathVariable final String meetingRoomId, @PathVariable final String id, final UriComponentsBuilder uriBuilder) {
		final Optional<MeetingRoom> meetingRoomOptional = meetingRoomService.findById(meetingRoomId);
		final Optional<Church> churchOptional = churchService.findById(id);
		
		if (meetingRoomOptional.isPresent() && churchOptional.isPresent()) {
			final Church church = churchOptional.get();
			church.addMeetingRoom(meetingRoomOptional.get());
			
			final URI uri = uriBuilder.path("/api/v1/churches/{id}").buildAndExpand(church.getId()).toUri();
	        return ResponseEntity.created(uri).body(church);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
