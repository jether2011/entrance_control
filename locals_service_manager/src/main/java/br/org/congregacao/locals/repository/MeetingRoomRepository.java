package br.org.congregacao.locals.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.org.congregacao.locals.domain.MeetingRoom;

@Repository
public interface MeetingRoomRepository extends MongoRepository<MeetingRoom, String> {

}
