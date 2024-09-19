package com.petproject.oneance.repo;

import com.petproject.oneance.model.Company;
import com.petproject.oneance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    
}
