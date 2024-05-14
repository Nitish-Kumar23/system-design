package com.masterclass.repository;

import com.masterclass.entity.Booking;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {
    Booking findBySeatIdAndUserIdAndFlightTripId(Integer seatId, Integer userId, Integer tripId);

    List<Booking> findBySeatIdAndFlightTripId(Integer seatId, Integer tripId);

    // exclusive lock - read & write no other Tx can read or write.
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select b from Booking b")
    List<Booking> getAllBookings();
}
