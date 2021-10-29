package events.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import events.models.Event;
import events.models.User;
import events.repositories.EventRepository;
import events.repositories.UserRepository;



@Service
public class UserService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    
    public UserService(UserRepository userRepository,EventRepository eventRepository ) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }
    
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    // join event
    public User joinEvent(Long userId, Long eventId) {
    	Optional<User> u = userRepository.findById(userId);
    	Optional<Event> e = eventRepository.findById(eventId);
    	
    	if(u.isPresent() & e.isPresent()) {
    		User user = u.get();
    		Event event = e.get();
    		if(user.getAtt_events()==null) {
    			List<Event> attev= new ArrayList<Event>();
    			user.setAtt_events(attev);
    		}
    		user.getAtt_events().add(event);
            return userRepository.save(user);
    	} else {
    	    return null;
    	}
    }
    
    // cancel event
    public User cancelEvent(Long userId, Long eventId) {
    	Optional<User> u = userRepository.findById(userId);
    	Optional<Event> e = eventRepository.findById(eventId);
    	
    	if(u.isPresent() & e.isPresent()) {
    		User user = u.get();
    		Event event = e.get();
    		user.getAtt_events().remove(event);
            return userRepository.save(user);
    	} else {
    	    return null;
    	}
    }
    
    
    
    
}

