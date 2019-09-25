package com.shoppingcart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shoppingcart.models.Apparel;

@Repository
public interface ApparelDAO {

	public boolean saveApparel(Apparel apparel);

	public List<Apparel> getApparels();

	public Apparel getApparel(Integer apparel_id);

	public Boolean deleteApparel(Integer apparel_id);
}
