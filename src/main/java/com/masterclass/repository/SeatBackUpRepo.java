package com.masterclass.repository;

import com.masterclass.entity.SeatBackup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatBackUpRepo extends JpaRepository<SeatBackup, Integer> {
}
