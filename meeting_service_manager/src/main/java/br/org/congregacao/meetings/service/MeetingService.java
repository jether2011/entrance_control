package br.org.congregacao.meetings.service;

import br.org.congregacao.meetings.domain.Meeting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface MeetingService extends MongoRepository<Meeting, String> {
}
