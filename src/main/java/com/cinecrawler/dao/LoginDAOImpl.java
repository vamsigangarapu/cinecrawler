/**
 * 
 */
package com.cinecrawler.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author vamsi
 *
 */
@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SessionFactory sessionFactory;
	 
	public void setSessionFactory(SessionFactory sessionFactory) {
         this.sessionFactory = sessionFactory;
	}
 
	protected Session getSession(){
         return sessionFactory.openSession();
	}
	 
	@SuppressWarnings("rawtypes")
	@Override
	public boolean checkLoginDetails(String username, String password) {
		// TODO Auto-generated method stub
		System.out.println("In Check login");
		boolean userFound = false;
		Session session = sessionFactory.openSession();
		String SQL_QUERY =" from LoginDetails as o where o.username=? and o.password=?";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0,username);
		query.setParameter(1,password);
		List list = query.list();

		if ((list != null) && (list.size() > 0)) {
			userFound= true;
		}

		session.close();
		return userFound;
		
	}

	
	
}
