package events.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Size(min=1, message="Name must be at least one character long")
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(min=1,message="Location must be at least one character long")
    private String location;
    @NotEmpty
    @Size(min=2, message="Please select a state for the event")
    private String state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organizer_id")
    private User organizer;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "events_attendees", 
        joinColumns = @JoinColumn(name = "event_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> attendees;
    @Transient
    private List<Long> att_ids;
    @OneToMany(mappedBy="event", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Message> messages;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    public Event() {
    	
    }
    
    public Event(String name, Date date, String location, String state) {
    	this.name = name; 
    	this.date = date;
    	this.location = location;
    	this.state= state;
    }
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date expirationDate) {
		this.date = expirationDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public User getOrganizer() {
		return organizer;
	}
	public void setOrganizer(User organizer) {
		this.organizer = organizer;
	}
	public List<User> getAttendees() {
		return attendees;
	}
	
	
	public List<Long> getAtt_ids() {
		List<Long> att_ids = new ArrayList<Long>(); 
		for(User att: this.getAttendees()) {
			att_ids.add(att.getId());
		}
		this.setAtt_ids(att_ids);
		return att_ids;
	}

	public void setAtt_ids(List<Long> att_ids) {
		this.att_ids = att_ids;
	}

	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
		
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	


	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
}
