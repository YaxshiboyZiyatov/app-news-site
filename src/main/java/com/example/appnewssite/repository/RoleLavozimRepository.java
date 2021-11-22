package com.example.appnewssite.repository;

import com.example.appnewssite.entity.RoleLavozim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleLavozimRepository extends JpaRepository<RoleLavozim, Long> {
    Optional<RoleLavozim> findByName(String name);

    boolean existsByName(String name);
}
