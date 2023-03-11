package com.jonesman.shop.repository;

import com.jonesman.shop.entity.DailyTotalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DailyTotalRepository extends JpaRepository<DailyTotalEntity, Long> {

    DailyTotalEntity findByDate(LocalDate date);

}

