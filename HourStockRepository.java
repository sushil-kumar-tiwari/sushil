package com.src.ingtradeapp.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.src.ingtradeapp.model.Orders;

@Component
public interface HourStockRepository extends CrudRepository<Orders, Serializable>{
	
	@Query(value="select  sum(volume) from ingdb.orders o "
			+ "where trade_time > DATE_SUB(CURRENT_TIMESTAMP(), INTERVAL 1 HOUR) and "
			+ "stock_name= ?1" , nativeQuery = true)
	Integer getHourStock(String stockname);
	
}
