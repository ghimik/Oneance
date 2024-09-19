package com.petproject.oneance.repo;

import com.petproject.oneance.model.StockPriceHistory;
import com.petproject.oneance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPriceHistoryRepository extends JpaRepository<StockPriceHistory, Long> {
    
}
