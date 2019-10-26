package br.org.congregacao.meetings.repository;

import br.org.congregacao.meetings.domain.Church;
import br.org.congregacao.meetings.domain.Meeting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChurchRepository extends MongoRepository<Church, String> {
}
