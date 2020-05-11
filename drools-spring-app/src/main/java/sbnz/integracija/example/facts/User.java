package sbnz.integracija.example.facts;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity // This tells Hibernate to make a table out of this class
public class User implements Serializable{
	
	private String username;

	private String password;

	private Date registrationDate;

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String password, String medal) {
		super();
		this.username = username;
		this.password = password;
		this.medal = medal;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getMedal() {
		return medal;
	}

	public void setMedal(String medal) {
		this.medal = medal;
	}

	private String medal;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}