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
public class SearchDAOImpl implements SearchDAO {
	
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
	public SearchDAO checkZipcode(String username) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		String SQL_QUERY ="select postcode from SearchDetails as o where o.username=?";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0,username);
		List list = query.list();
		if ((list != null) && (list.size() > 0)) {
			session.close();
			return (SearchDAO) query.uniqueResult();		
		}
		else{
			session.close();
			return null;
		}
	}

}
