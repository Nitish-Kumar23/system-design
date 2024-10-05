package com.masterclass.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "booking")
@Data
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "seat_id")
    private Integer seatId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "flight_trip_id")
    private Integer flightTripId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFlightTripId() {
        return flightTripId;
    }

    public void setFlightTripId(Integer flightTripId) {
        this.flightTripId = flightTripId;
    }
}
