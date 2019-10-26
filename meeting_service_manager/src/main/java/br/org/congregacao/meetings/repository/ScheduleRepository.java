package br.org.congregacao.meetings.repository;

import br.org.congregacao.meetings.domain.Meeting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends MongoRepository<Meeting, String> {
}
