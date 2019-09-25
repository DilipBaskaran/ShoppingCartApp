package com.shoppingcart.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.dao.ApparelDAO;
import com.shoppingcart.models.Apparel;

@Service("apparelService")
@Transactional
public class ApparelServiceImpl implements ApparelService {

	@Autowired
	ApparelDAO apparelDAO;

	@Override
	public boolean saveApparel(Apparel apparel) {
		return apparelDAO.saveApparel(apparel);
	}

	@Override
	public List<Apparel> getApparels() {
		return apparelDAO.getApparels();
	}

	@Override
	public Apparel getApparel(Integer apparel_id) {
		return apparelDAO.getApparel(apparel_id);
	}

	@Override
	public Boolean deleteApparel(Integer apparel_id) {
		return apparelDAO.deleteApparel(apparel_id);
	}

}
