package br.org.congregacao.locals.repository;

import br.org.congregacao.locals.domain.Administration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrationRepository extends MongoRepository<Administration, String> {
}
