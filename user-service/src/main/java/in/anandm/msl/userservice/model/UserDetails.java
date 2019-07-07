package in.anandm.msl.userservice.model;

import java.util.Date;

public class UserDetails {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean enabled;
	private Date passwordExipresAt;
	
	
	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public Date getPasswordExipresAt() {
		return passwordExipresAt;
	}


	public void setPasswordExipresAt(Date passwordExipresAt) {
		this.passwordExipresAt = passwordExipresAt;
	}
	
}
