package events.controllers;

import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import events.constants.USConstants;
import events.models.Event;
import events.models.Message;
import events.models.User;
import events.services.EventService;
import events.services.UserService;
import events.validator.EventValidator;



@Controller
public class EventsController {
	private final UserService userService;
    private final EventService eventService;
    private final EventValidator eventValidator;
    private final List<String> listOfUSStatesCode = USConstants.listOfUSStatesCode;
    
    
    public EventsController(UserService userService,EventService eventService, EventValidator eventValidator) {
    	this.userService = userService;
    	this.eventService = eventService;
    	this.eventValidator = eventValidator;
    	java.util.Collections.sort(listOfUSStatesCode, Collator.getInstance());
    }
    
    
	@RequestMapping(value="/createEvent", method=RequestMethod.POST)
    public String addEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session, Model model) {
		eventValidator.validate(event, result);
		Long id = (Long) session.getAttribute("user_id");
		User user = userService.findUserById(id);
		String state = user.getState();
		List<Event> stateEvents = eventService.findEventsInState(state);
		List<Event> otherStateEvents = eventService.findEventsNotInState(state);
		model.addAttribute("states", listOfUSStatesCode);
		model.addAttribute("user", user);
		model.addAttribute("stateEvents", stateEvents);
		model.addAttribute("otherStateEvents", otherStateEvents);
		
    	if(result.hasErrors()) {
    		
    		return"events/dashboard.jsp";
    	} else {
    		eventService.addEvent(event, user);
    		return"redirect:/home";
    	}

    }
	
	
	@RequestMapping(value="/join/{id}")
    public String joinEvent(@PathVariable (value="id") Long eventId, HttpSession session) {

		Long id = (Long) session.getAttribute("user_id");
		userService.joinEvent(id, eventId);
		return "redirect:/home";
		
    }
	
	@RequestMapping(value="/delete/{id}")
    public String deleteEvent(@PathVariable (value="id") Long eventId, HttpSession session) {
		Long user_id = (Long) session.getAttribute("user_id");
		Event event= eventService.findEventById(eventId);
		if(event.getOrganizer().getId().equals(user_id)) {
			eventService.deleteEvent(eventId);
		}
		return "redirect:/home";
    }
	
	@RequestMapping(value="/edit/{id}")
    public String editEvent(@PathVariable (value="id") Long eventId, HttpSession session, Model model) {
		Long user_id = (Long) session.getAttribute("user_id");
		Event event= eventService.findEventById(eventId);
		
		if(event.getOrganizer().getId().equals(user_id)) {
			model.addAttribute("states", listOfUSStatesCode);
			model.addAttribute("event", event);
			return "events/edit.jsp";
		}
		else {
			
		return "redirect:/home";}
		
    }
	
	@RequestMapping(value="/cancel/{id}")
    public String cancelEventAtt(@PathVariable (value="id") Long eventId, HttpSession session) {
		Long user_id = (Long) session.getAttribute("user_id");
		Event event= eventService.findEventById(eventId);
		User user = userService.findUserById(user_id);
		user.getAtt_events().remove(event);
		userService.registerUser(user);
		return "redirect:/home";
    }
	
	@RequestMapping(value="/updateEvent", method=RequestMethod.POST)
    public String updateEvent(@RequestParam("id") Long eventId,@RequestParam("name") String name,
    		@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
    		@RequestParam("location") String location, @RequestParam("state") String state,
    		RedirectAttributes redirectAttributes, Model model) {
	
		Event event = eventService.findEventById(eventId);

		
		if(eventService.validateEvent(name, date, location, state) == "") {
			event.setName(name);
			event.setDate(date);
			event.setLocation(location);
			event.setState(state);
	    	eventService.updateEvent(event);
			
			return "redirect:/home";
			
		} else {
			
			String error = eventService.validateEvent(name, date, location, state);
			redirectAttributes.addFlashAttribute("error_event", error);
			return String.format("redirect:/edit/%d",eventId);

		}
		

		
    }
	
	
	@RequestMapping(value="/events/{id}")
    public String displayEvent(@ModelAttribute("message") Message message,
    		@PathVariable (value="id") Long eventId, HttpSession session, Model model) {
		
		Long userId = (Long) session.getAttribute("user_id");
		Event event = eventService.findEventById(eventId);
		model.addAttribute("event", event);
		model.addAttribute("userId", userId);
		return "events/show.jsp";
		
    }
	
	@RequestMapping(value="/sendMessage", method=RequestMethod.POST)
    public String sendMessage(@Valid @ModelAttribute("message") Message message,
    		BindingResult result, HttpSession session, Model model) {
		
		Long userId = (Long) session.getAttribute("user_id");
		Event event = eventService.findEventById(message.getEventId());
		model.addAttribute("userId", userId);
		if(result.hasErrors()) {
			model.addAttribute("event", event);
    		return "events/show.jsp";
    	} else {
    		message.setSender(userService.findUserById(userId));
    		message.setEvent(event);
    		event.getMessages().add(message);
    		eventService.updateEvent(event);
    		model.addAttribute("event", event);
    		return"events/show.jsp";
    	}

		
    }
	
	
	
}
