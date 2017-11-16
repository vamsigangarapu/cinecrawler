/**
 * 
 */
package com.cinecrawler.service;

import com.cinecrawler.dao.SearchDAO;

/**
 * @author vamsi
 *
 */
public interface SearchService {

	public SearchDAO checkZipcode(String username);
}
