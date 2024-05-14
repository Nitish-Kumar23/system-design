package com.masterclass.service;

import com.masterclass.request.FlightBookingRequest;
import com.masterclass.response.FlightBookingResponse;

import java.util.concurrent.ExecutionException;

public interface CheckInService {

    /**
     *
     * Book flight tickets sequentially
     *
     * @param request
     * @return
     */
    FlightBookingResponse bookFlightTicket(FlightBookingRequest request) throws ExecutionException, InterruptedException;

    void bookFlightTicketRandom();
}
