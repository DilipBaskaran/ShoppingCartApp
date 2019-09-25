package com.shoppingcart.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingcart.models.Apparel;
import com.shoppingcart.models.Product;

@Repository("apparelDAO")
public class ApparelDAOImpl implements ApparelDAO {

	Logger logger = Logger.getLogger(ApparelDAOImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean saveApparel(Apparel apparel) {
		boolean saveFlag = true;
		try{
			Product product = apparel;
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(product);
			session.saveOrUpdate(apparel);
		}catch(Exception e){
			logger.error(e.getStackTrace());
			saveFlag = false;
		}
		return saveFlag;
		
	}

	@Override
	public List<Apparel> getApparels() {
		List<Apparel>  apparelList = null;
		try{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From Apparel",Apparel.class);
			apparelList = (List<Apparel>)query.getResultList();
		}catch(Exception e){
			logger.error(e.getStackTrace());
			return apparelList;
		}
		return apparelList;	}

	@Override
	public Apparel getApparel(Integer apparel_id) {

		Apparel apparel = null;
		try{
			Session session = sessionFactory.getCurrentSession();
			apparel = session.get(Apparel.class,apparel_id);
		}catch(Exception e){
			logger.error(e.getStackTrace());
			return apparel;
		}
		return apparel;

	}

	@Override
	public Boolean deleteApparel(Integer apparel_id) {
		boolean deleteFlag = true;
		try{
			Session session = sessionFactory.getCurrentSession();
			Apparel apparel = session.load(Apparel.class, apparel_id);
			session.delete(apparel);
		}catch(Exception e){
			logger.error(e.getStackTrace());
			deleteFlag = false;
		}

		return deleteFlag;
	}

}
