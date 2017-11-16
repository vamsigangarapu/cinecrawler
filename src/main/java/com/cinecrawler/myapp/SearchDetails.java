/**
 * 
 */
package com.cinecrawler.myapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vamsi
 *
 */
@Entity
@Table(name = "REGISTRATION_DETAILS")
public class SearchDetails {
	
	@Id
	@Column(name = "username")
	String username;
	
	String zipcode;
	
	public SearchDetails(String username) {
		// TODO Auto-generated constructor stub
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
}
