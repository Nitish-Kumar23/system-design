package com.masterclass.controller;

import com.masterclass.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/seat")
public class SeatsController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/bulk-add")
    public ResponseEntity<Void> addBulkSeats(){
        seatService.addBulkSeats();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
