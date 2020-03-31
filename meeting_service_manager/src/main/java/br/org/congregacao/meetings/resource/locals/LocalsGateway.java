package br.org.congregacao.meetings.resource.locals;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.org.congregacao.meetings.resource.locals.response.AdministrationResponse;
import br.org.congregacao.meetings.resource.locals.response.ChurchMeetingRoomsResponse;
import br.org.congregacao.meetings.resource.locals.response.WorkGroupResponse;

@FeignClient("locals")
public interface LocalsGateway {

	@GetMapping("/api/v1/administrations/{id}")
	AdministrationResponse findAdministrationById(@PathVariable("id") final String id);
	
	@GetMapping("/api/v1/churches/{id}/meeting-rooms")
	ChurchMeetingRoomsResponse findMeetingRoomsByChurchId(@PathVariable final String id);
	
	@GetMapping(value="/api/workgroups", produces = "${workgroups.api.version:application/vnd.workgroups.v1+json}")
	List<WorkGroupResponse> findAllWorkGroups();
	
}
