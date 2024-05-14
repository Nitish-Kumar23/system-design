package com.masterclass.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "flight_trip")
@Data
public class FlightTrip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "source")
    private String source;

    @Column(name = "destination")
    private String destination;

    @Column(name = "flight_id")
    private String flightId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    private Date endTime;

}
