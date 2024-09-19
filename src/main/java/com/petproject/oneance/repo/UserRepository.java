package com.petproject.oneance.repo;

import com.petproject.oneance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

