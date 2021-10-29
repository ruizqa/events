package events.controllers;

import java.text.Collator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import events.constants.USConstants;
import events.models.Event;
import events.models.User;
import events.services.EventService;
import events.services.UserService;
import events.validator.UserValidator;


@Controller
public class UsersController {
    private final UserService userService;
    private final EventService eventService;
    private final UserValidator userValidator;
    private final List<String> listOfUSStatesCode = USConstants.listOfUSStatesCode;
	
	public UsersController(UserService userService,EventService eventService, UserValidator userValidator) {
		this.userService = userService;
		this.eventService = eventService;
		this.userValidator = userValidator;
		java.util.Collections.sort(listOfUSStatesCode, Collator.getInstance());
	}
	
	@RequestMapping("/")
    public String registerForm(@ModelAttribute("user") User user,
    		@ModelAttribute("userLogin") User userLogin, Model model) {
		java.util.Collections.sort(listOfUSStatesCode, Collator.getInstance());
		model.addAttribute("states", listOfUSStatesCode);
        return "users/index.jsp";
    }
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session,
	    		@ModelAttribute("userLogin") User userLogin) {
	    	userValidator.validate(user, result);
	    	if(result.hasErrors()) {
	    		return"users/index.jsp";
	    	} else {
	    		userService.registerUser(user);
	    		session.setAttribute("user_id", user.getId());
	    		return"redirect:/home";
	    	}

	    }
	    
	@RequestMapping(value="/login", method=RequestMethod.POST)
	    public String loginUser(@Valid @ModelAttribute("userLogin") User userLogin,BindingResult result,
	    		Model model, HttpSession session,@ModelAttribute("user") User user) {
		
		if(result.hasErrors()) {
    		return"users/index.jsp";
    	} else {
    		
    		if(userService.authenticateUser(userLogin.getEmail(), userLogin.getPassword())){
    			userLogin = userService.findByEmail(userLogin.getEmail());
        		session.setAttribute("user_id", userLogin.getId());
        		return"redirect:/home";
    			
    		}
    		else {
    		model.addAttribute("error", "Wrong credentials. Please enter your valid credentials");
    		return "users/index.jsp";}
    	}
		

	    }
	
	@RequestMapping("/home")
    public String home(@ModelAttribute("event") Event event, Model model, HttpSession session) {
		
		if(session.getAttribute("user_id")==null) {
			return"redirect:/";
		}
		
		Long id = (Long) session.getAttribute("user_id");
		User user = userService.findUserById(id);
		String state = user.getState();
		List<Event> stateEvents = eventService.findEventsInState(state);
		List<Event> otherStateEvents = eventService.findEventsNotInState(state);
		model.addAttribute("states", listOfUSStatesCode);
		model.addAttribute("user", user);
		model.addAttribute("stateEvents", stateEvents);
		model.addAttribute("otherStateEvents", otherStateEvents);
        return "/events/dashboard.jsp";
    }
	
	

	
	
	@RequestMapping("/logout")
    public String logout(HttpSession session) {
		session.invalidate();
        return "redirect:/";
    }
	
	
}
