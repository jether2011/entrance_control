package br.org.congregacao.meetings.application.resources;

import br.org.congregacao.meetings.application.resources.request.ScheduleRequest;
import br.org.congregacao.meetings.domain.Schedule;
import br.org.congregacao.meetings.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/schedules")
public class ScheduleResource implements Serializable {

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

    @PatchMapping("/{idSchedule}/schedule/")
    public ResponseEntity<Schedule> addSchedules(@PathVariable final String idSchedule) {
        final Optional<Schedule> optionalSchedule = scheduleService.findById(idSchedule);
        if (optionalSchedule.isPresent()) {
            final Schedule schedule = optionalSchedule.get();
            scheduleService.save(schedule);
            return ResponseEntity.accepted().body(schedule);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Schedule> delete(@PathVariable String id) {
        scheduleService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
