package com.project.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class user {
	
	 @Id
	 public String username;
	 public String password;
	 public long mobileno;
	 public String emailid;
	public user() {
		super();
		// TODO Auto-generated constructor stub
	}
	public user(String username, String password, long mobileno, String emailid) {
		super();
		this.username = username;
		this.password = password;
		this.mobileno = mobileno;
		this.emailid = emailid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getMobileno() {
		return mobileno;
	}
	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	@Override
	public String toString() {
		return "user [username=" + username + ", password=" + password + ", mobileno=" + mobileno + ", emailid="
				+ emailid + "]";
	}
	 
	 

}
