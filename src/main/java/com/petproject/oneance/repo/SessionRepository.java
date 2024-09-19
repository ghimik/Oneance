package com.petproject.oneance.repo;

import com.petproject.oneance.model.Session;
import com.petproject.oneance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
    
}
