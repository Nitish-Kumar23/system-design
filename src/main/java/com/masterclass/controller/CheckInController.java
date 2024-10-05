package com.masterclass.controller;

import com.masterclass.request.FlightBookingRequest;
import com.masterclass.response.FlightBookingResponse;
import com.masterclass.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("v1/checkin")
public class CheckInController {
    @Autowired
    private CheckInService checkInService;

    @PostMapping("/book-seat")
    public ResponseEntity<FlightBookingResponse> bookFlightTicket(@RequestBody FlightBookingRequest request) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(checkInService.bookFlightTicket(request), HttpStatus.OK);
    }

    @PostMapping("/book-seat/random")
    public ResponseEntity<FlightBookingResponse> bookFlightTicket() {
        checkInService.bookFlightTicketRandom();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
