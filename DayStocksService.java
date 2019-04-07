package com.src.ingtradeapp.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.src.ingtradeapp.model.DayStocksResponse;
import com.src.ingtradeapp.model.Orders;
import com.src.ingtradeapp.repo.DayStockRepository;

@Component
public class DayStocksService {
	
	@Autowired
	DayStockRepository stockRepo;
	
	public List<DayStocksResponse> getLastDayStocks(Integer days) {
		LocalDate date = LocalDate.now().minusDays(days);
		List<Orders> result= stockRepo.findAllLastDateData(Date.valueOf(date));
		HashMap<String,Integer> countVolume = new HashMap();
		for(Orders currOrder : result) {
			String stockName = currOrder.getStockName();
			Integer value = currOrder.getVolume();
			if(countVolume.get(stockName)==null) {
				countVolume.put(stockName,value);
			} else {
				countVolume.put(stockName,countVolume.get(stockName)+value);
			}
		}
		List<DayStocksResponse> response  = new ArrayList<>();
		for(String name:countVolume.keySet()) {
			DayStocksResponse currStock = new DayStocksResponse();
			currStock.setName(name);
			currStock.setVolume(countVolume.get(name));
			response.add(currStock);
		}
		return response;
	}
}
