/**
 * 
 */
package com.cinecrawler.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinecrawler.dao.SearchDAO;

/**
 * @author vamsi
 *
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDAO searchDAO;
	
	public SearchDAO getsearchDAO() {
		return this.searchDAO;
		
	}
	
	public void setSearchDAO(SearchDAO searchDAO) {
		this.searchDAO = searchDAO;
	}
	
	@Override
	@Transactional
	public SearchDAO checkZipcode(String username) {
		// TODO Auto-generated method stub
		return searchDAO.checkZipcode(username);
	}

}
