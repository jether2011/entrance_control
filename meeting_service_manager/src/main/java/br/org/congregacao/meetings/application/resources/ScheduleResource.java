package br.org.congregacao.meetings.application.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.org.congregacao.meetings.application.resources.request.ScheduleRequest;
import br.org.congregacao.meetings.domain.Schedule;
import br.org.congregacao.meetings.service.ScheduleService;

@RestController
@RequestMapping(value = "/api/v1/schedules")
public class ScheduleResource {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<Schedule>> getAll() {
        return ResponseEntity.ok().body(scheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getOne(@PathVariable final String id) {
        final Optional<Schedule> optionalSchedule = scheduleService.findById(id);
        if (optionalSchedule.isPresent()) {
            return ResponseEntity.ok().body(optionalSchedule.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Schedule> create(@RequestBody @Valid final ScheduleRequest request, final UriComponentsBuilder uriBuilder) {
        final Schedule schedule = ScheduleRequest.from(request);
        scheduleService.save(schedule);

        final URI uri = uriBuilder.path("/schedule/{id}").buildAndExpand(schedule.getId()).toUri();
        return ResponseEntity.created(uri).body(schedule);
    }
    
    @PatchMapping("/{scheduleId}/status")
    public ResponseEntity<Schedule> updateStatus(@RequestBody final ScheduleRequest request, @PathVariable final String scheduleId) {
        final Optional<Schedule> optionalSchedule = scheduleService.findById(scheduleId);
        if (optionalSchedule.isPresent()) {
            final Schedule schedule = optionalSchedule.get();
            schedule.addStatus(request.getStatus());
            scheduleService.save(schedule);
            return ResponseEntity.accepted().body(schedule);
        } else {
            return ResponseEntity.notFound().build();
        }
    }	

    @DeleteMapping("/{id}")
    public ResponseEntity<Schedule> delete(@PathVariable final String id) {
        scheduleService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
