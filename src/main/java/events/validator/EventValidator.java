package events.validator;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import events.models.Event;


@Component
public class EventValidator implements Validator {
    

    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        Event event = (Event) target;
        Date date = new Date(); 
        
        if (event.getDate() == null) {
            errors.rejectValue("date", "InvalidDate");
        }
        else if (event.getDate().before(date)) {
            errors.rejectValue("date", "InvalidDate");
        }

    }
    
    
    
    
}
