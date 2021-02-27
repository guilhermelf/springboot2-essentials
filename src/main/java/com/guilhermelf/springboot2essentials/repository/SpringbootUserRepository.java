package com.guilhermelf.springboot2essentials.repository;

import com.guilhermelf.springboot2essentials.domain.SpringbootUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringbootUserRepository extends JpaRepository<SpringbootUser, Long> {
    SpringbootUser findByUsername(String username);
}
