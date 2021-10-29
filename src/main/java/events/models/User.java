package events.models;

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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Size(min=1, message="First name must be at least one character long")
    private String firstName;
    @Size(min=1, message="Last name must be at least one character long")
    private String lastName;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message="Email must be valid")
    private String email;
    @Size(min=1, message="Location must be at least one character long")
    private String location;
    @Size(min=2)
    private String state;
    @Size(min=5, message="Password must be greater than 5 characters")
    private String password;
    @Transient
    private String passwordConfirmation;
    @OneToMany(mappedBy="organizer", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Event> org_events;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "events_attendees", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> att_events;
    @OneToMany(mappedBy="sender", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Message> messages;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    public User() {
    }
    
    public User(String firstName, String lastName, String email, String location, String state, String password) {
    	this.firstName = firstName;
    	this.lastName= lastName;
    	this.email = email;
    	this.location = location;
    	this.state = state;
    	this.password = password;
    }
    
    public User(String email, String password) {
    	this.email = email;
    	this.password= password;
    }
    
    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}


	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	


	public List<Event> getOrg_events() {
		return org_events;
	}

	public void setOrg_events(List<Event> org_events) {
		this.org_events = org_events;
	}

	public List<Event> getAtt_events() {
		return att_events;
	}

	public void setAtt_events(List<Event> att_events) {
		this.att_events = att_events;
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


	// other getters and setters removed for brevitycopy
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}