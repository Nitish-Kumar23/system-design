package com.masterclass.service;

import com.masterclass.request.FlightBookingRequest;
import com.masterclass.response.FlightBookingResponse;

import java.util.concurrent.ExecutionException;

public interface CheckInService {

    /**
     *
     * Book flight tickets
     *
     * @param request
     * @return
     */
    FlightBookingResponse bookFlightTicket(FlightBookingRequest request) throws ExecutionException, InterruptedException;

    /**
     * Book flight ticket for all seats, select seats randomly
     */
    void bookFlightTicketRandom();

}
