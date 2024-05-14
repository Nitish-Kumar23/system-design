package com.masterclass.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "seat_backup")
@Data
public class SeatBackup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "flight_id")
    private String flightId;

    @Column(name = "label")
    private String label;

    public SeatBackup() {
    }

    public SeatBackup(String flightId, String label) {
        this.flightId = flightId;
        this.label = label;
    }
}
