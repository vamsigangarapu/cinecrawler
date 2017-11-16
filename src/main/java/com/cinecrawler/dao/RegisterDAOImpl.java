/**
 * 
 */
package com.cinecrawler.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cinecrawler.myapp.RegisterDetails;

/**
 * @author vamsi
 *
 */

@Repository
public class RegisterDAOImpl implements RegisterDAO {
	
	//private static final Logger logger = (Logger) LoggerFactory.logger(RegisterDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	/*public void setSessionFactory(SessionFactory sf){
		sessionFactory = sf;
	}*/

	@Override
	public void addDetails(RegisterDetails regDetails) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(regDetails);
		//session.persist(regDetails);
		//logger.info("Person saved successfully, Person Details="+regDetails);
	}

}
