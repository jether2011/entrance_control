package br.org.congregacao.locals.application.resources;

import br.org.congregacao.locals.application.errors.exception.NotFoundException;
import br.org.congregacao.locals.application.resources.request.WorkGroupRequest;
import br.org.congregacao.locals.application.resources.response.WorkGroupResponse;
import br.org.congregacao.locals.domain.WorkGroup;
import br.org.congregacao.locals.service.WorkGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/workgroups")
public class WorkGroupResource implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Autowired
    private WorkGroupService workGroupService;
    
    @GetMapping(produces = "application/vnd.workgroups.v1+json")
    public ResponseEntity<List<WorkGroupResponse>> getAll() {
    	final List<WorkGroupResponse> response = 
    			workGroupService.findAll().stream()
    				.map(WorkGroupResponse::from)
    				.collect(Collectors.toList());
    	
        return ResponseEntity.ok().body(response);
    }

	@GetMapping(value = "/{id}", produces = "application/vnd.workgroup_details.v1+json")
    public ResponseEntity<WorkGroupResponse> getOne(@PathVariable final String id) {
        final WorkGroup workGroup = workGroupService.findById(id)
        		.orElseThrow(() -> new NotFoundException(String.format("WorkGroup %s not found", id)));
        
        return ResponseEntity.ok().body(WorkGroupResponse.from(workGroup));
        }

    @PostMapping(produces = "application/vnd.workgroup_create.v1+json")
    public ResponseEntity<WorkGroup> create(@RequestBody @Valid final WorkGroupRequest request, final UriComponentsBuilder uriBuilder) {
        final WorkGroup workGroup = WorkGroupRequest.from(request);
        workGroupService.save(workGroup);

        final URI uri = uriBuilder.path("/api/workgroups/{id}").buildAndExpand(workGroup.getId()).toUri();
        return ResponseEntity.created(uri).body(workGroup);
    }
}
