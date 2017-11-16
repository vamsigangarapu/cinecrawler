package com.cinecrawler.myapp;

import java.util.Date;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "REGISTRATION_DETAILS")
public class RegisterDetails {

	@Id @GeneratedValue
	@Column(name = "id")
	private int id;
	private String username;
	private String password;
	@Temporal(TemporalType.DATE)
	private Date dob;
	private String email;
	private String tel;
	@Column(nullable=true)
	private String address;
	private String state;
	private String postcode;
	private ArrayList<String> genre;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public ArrayList<String> getGenre() {
		return genre;
	}
	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}
	
	@Override
	public String toString() {
		
		System.out.println("RegisterDetails [id=" + id + ", username=" + username + ", password=" + password 
				+ ", dob=" + dob + ", email=" + email + ", tel=" + tel + ", address=" + address + ", state="
				+ state + ", postcode=" + postcode + ", genre=" + genre + "]");
		
		return "RegisterDetails [id=" + id + ", username=" + username + ", password=" + password 
				+ ", dob=" + dob + ", email=" + email + ", tel=" + tel + ", address=" + address + ", state="
				+ state + ", postcode=" + postcode + ", genre=" + genre + "]";
	}
	
	
}
