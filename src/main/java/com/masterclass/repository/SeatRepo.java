package com.masterclass.repository;

import com.masterclass.entity.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepo extends JpaRepository<Seat,Integer> {
    Seat findByLabel(String seatLabel);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select id from Seat s where s.id not in :activeSeats")
    Integer findBySeatIdNotIn(List<Integer> activeSeats, PageRequest pageRequest);

}
