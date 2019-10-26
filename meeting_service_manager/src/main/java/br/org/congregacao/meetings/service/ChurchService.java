package br.org.congregacao.meetings.service;

import br.org.congregacao.meetings.domain.Church;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface ChurchService extends MongoRepository<Church, String> {
}
