package br.org.congregacao.locals.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.org.congregacao.locals.domain.Church;

@Repository
public interface ChurchRepository extends MongoRepository<Church, String> {

}
