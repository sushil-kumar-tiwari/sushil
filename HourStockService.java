package com.src.ingtradeapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.src.ingtradeapp.model.HourStock;
import com.src.ingtradeapp.repo.HourStockRepository;

@Component
public class HourStockService {

	@Autowired
	HourStockRepository hourStockRepository;

	public HourStock getHourStock(String stockname) {
		Integer volume =  0;
		volume = hourStockRepository.getHourStock(stockname);
		HourStock order = new HourStock();
		order.setName(stockname);
		order.setVolume(volume);		
		return order;

	}
}
