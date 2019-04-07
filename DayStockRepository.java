package com.src.ingtradeapp.repo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.src.ingtradeapp.model.Orders;

@Component
public interface DayStockRepository extends CrudRepository<Orders, Serializable>{
	List<Orders> findAll();
	
	@Query("SELECT o FROM Orders o WHERE o.tradeTime > ?1")
	List<Orders> findAllLastDateData(Date tradeTime);
}
