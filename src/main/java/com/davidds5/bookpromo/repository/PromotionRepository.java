package com.davidds5.bookpromo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davidds5.bookpromo.entity.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}
