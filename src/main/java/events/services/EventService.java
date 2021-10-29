package events.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import events.models.Event;
import events.models.User;
import events.repositories.EventRepository;


@Service
public class EventService {
	private final EventRepository eventRepository;
	
	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
    public Event addEvent(Event event, User organizer) {
    	
    	event.setOrganizer(organizer);
    	if(event.getAttendees() == null) {
    		List<User> atts = new ArrayList<User>();
    		event.setAttendees(atts);
    	}
    	event.getAttendees().add(organizer);
        return eventRepository.save(event);
    }
    
    public Event updateEvent(Event event) {
    	
        return eventRepository.save(event);
    }
    
    public List<Event> findEventsInState(String state) {
    	List<Event> events = eventRepository.findByStateContaining(state);
    	
    	return events;
    	
    }
    
    public List<Event> findEventsNotInState(String state) {
    	List<Event> events = eventRepository.findByStateNotContaining(state);
    	
    	return events;
    	
    }
    
    
    public Event findEventById(Long id) {
    	Optional<Event> e = eventRepository.findById(id);
    	
    	if(e.isPresent()) {
            return e.get();
    	} else {
    	    return null;
    	}
    }
    
    public void deleteEvent(Long id) {
    	Optional<Event> e = eventRepository.findById(id);
    	
    	if(e.isPresent()) {
            eventRepository.deleteById(id);
    	}
    }
    
    public String validateEvent(String name, Date date, String location, String state) {
    	String errors = "";
    	Date today = new Date();
    	if(name.length()<1| location.length()<1 | state.length()<1) {
			errors += "Please complete all fields.";
		}
		if(date.before(today)) {
			errors += "The event must be in the future";
		}
    	
		return errors;
    	
    }
	
	
}
