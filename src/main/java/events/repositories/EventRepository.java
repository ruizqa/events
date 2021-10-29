package events.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import events.models.Event;


@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
	List<Event> findByStateContaining(String state);
	List<Event> findByStateNotContaining(String state);
}
