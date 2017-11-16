/**
 * 
 */
package com.cinecrawler.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinecrawler.dao.RegisterDAO;
import com.cinecrawler.myapp.RegisterDetails;

/**
 * @author vamsi
 *
 */

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterDAO registerDAO;

	public void setRegisterDAO(RegisterDAO registerDAO) {
		this.registerDAO = registerDAO;
	}
	
	@Override
	@Transactional
	public void addDetails(RegisterDetails regDetails) {
		// TODO Auto-generated method stub
		this.registerDAO.addDetails(regDetails);
	}

}
