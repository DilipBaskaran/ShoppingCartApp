package com.shoppingcart.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingcart.models.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	
	Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean saveUser(User user) {
		boolean saveFlag = true;
		try{
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(user);
		}catch(Exception e){
			logger.error(e.getStackTrace());
			saveFlag = false;
		}
		return saveFlag;
	}

	@Override
	public List<User> getUsers() {
		List<User>  userList = null;
		try{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From User",User.class);
			userList = (List<User>)query.getResultList();
		}catch(Exception e){
			logger.error(e.getStackTrace());
		}
		return userList;
	}

	@Override
	public User getUser(Integer user_id) {
		User user = null;
		try{
			Session session = sessionFactory.getCurrentSession();
			user = session.get(User.class,user_id);
		}catch(Exception e){
			logger.error(e.getStackTrace());
		}
		return user;
	}

	@Override
	public Boolean deleteUser(Integer user_id) {
		boolean deleteFlag = true;
		try{
			Session session = sessionFactory.getCurrentSession();
			User user = session.load(User.class, user_id);
			session.delete(user);
		}catch(Exception e){
			logger.error(e.getStackTrace());
			deleteFlag = false;
		}

		return deleteFlag;
	}
	
	@Override
	public User getUser(String userName) {
        String sql = "From User u where u.user_name = :userName ";
        try {
        	Session session = sessionFactory.getCurrentSession();
            Query query =  session.createQuery(sql,User.class);
            query.setParameter("userName", userName);
            User user = (User)(!query.getResultList().isEmpty()?query.getResultList().get(0):null);
            return user;
        } catch (Exception e) {
        	logger.error(e.getStackTrace());
            return null;
        }
    }

}
