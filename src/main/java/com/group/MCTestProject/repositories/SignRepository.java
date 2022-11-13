package com.group.MCTestProject.repositories;

import com.group.MCTestProject.models.Sign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Neil Alishev
 */
@Repository
public interface SignRepository extends JpaRepository<Sign, Integer> {
    Optional<Sign> findByUsername(String username);
}
