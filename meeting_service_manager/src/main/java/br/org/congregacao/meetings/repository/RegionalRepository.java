package br.org.congregacao.locals.repository;

import br.org.congregacao.locals.domain.Regional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionalRepository extends MongoRepository<Regional, String> {
}
