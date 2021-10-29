package events.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import events.models.Event;
import events.models.Message;


@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

}
