package com.petproject.oneance.repo;

import com.petproject.oneance.model.Stock;
import com.petproject.oneance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    
}
