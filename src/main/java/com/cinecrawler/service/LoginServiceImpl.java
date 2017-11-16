/**
 * 
 */
package com.cinecrawler.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinecrawler.dao.LoginDAO;

/**
 * @author vamsi
 *
 */

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAO loginDAO;
	
	public LoginDAO getLoginDAO() {
		return this.loginDAO;
		
	}
	
	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
	
	@Override
	@Transactional
	public boolean checkLoginDetails(String username, String password) {
		// TODO Auto-generated method stub
		//assignDetails(username, password);
		return loginDAO.checkLoginDetails(username, password);
	}

/*	@SuppressWarnings("null")
	@Override
	public LoginDetails assignDetails(String username, String password) {
		// TODO Auto-generated method stub
		LoginDetails loginDetails = null;
		loginDetails.setUsername(username);
		loginDetails.setPassword(password);
		return loginDetails;
	}*/

}
