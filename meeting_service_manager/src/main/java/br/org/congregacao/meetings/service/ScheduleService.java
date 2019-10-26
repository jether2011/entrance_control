package br.org.congregacao.meetings.service;

import br.org.congregacao.meetings.domain.Church;
import br.org.congregacao.meetings.domain.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService extends MongoRepository<Schedule, String> {
}
