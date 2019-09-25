package com.shoppingcart.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shoppingcart.models.Apparel;

@Service("apparelService")
public interface ApparelService {

	public boolean saveApparel(Apparel apparel);

	public List<Apparel> getApparels();

	public Apparel getApparel(Integer apparel_id);

	public Boolean deleteApparel(Integer apparel_id);
}