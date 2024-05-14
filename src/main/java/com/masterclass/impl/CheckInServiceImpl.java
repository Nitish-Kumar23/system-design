package com.masterclass.impl;

import com.masterclass.entity.Booking;
import com.masterclass.entity.Seat;
import com.masterclass.entity.User;
import com.masterclass.enums.ErrorCodes;
import com.masterclass.exceptionHandler.ApplicationException;
import com.masterclass.repository.BookingRepo;
import com.masterclass.repository.SeatRepo;
import com.masterclass.repository.UserRepo;
import com.masterclass.request.FlightBookingRequest;
import com.masterclass.response.FlightBookingResponse;
import com.masterclass.service.BookingHelperService;
import com.masterclass.service.CheckInService;
import jakarta.persistence.LockModeType;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class CheckInServiceImpl implements CheckInService {

    private static final Logger logger = LoggerFactory.getLogger(CheckInServiceImpl.class);

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookingHelperService bookingHelperService;

    @Override
    public FlightBookingResponse bookFlightTicket(FlightBookingRequest request) throws ExecutionException, InterruptedException {
        logger.info("bookFlightTicket : for request {} and thread id {}", request, Thread.currentThread().getName());

        validateBookingRequest(request);
        Seat seat = seatRepo.findByLabel(request.getSeatLabel());
        Booking booking = new Booking();
        booking.setFlightTripId(request.getTripId());
        booking.setUserId(request.getUserId());
        booking.setSeatId(seat.getId());

        bookingRepo.saveAndFlush(booking);
        logger.info("bookFlightTicket : success for request {}", request);
        return new FlightBookingResponse(booking.getFlightTripId(), request.getSeatLabel());
    }

    private void validateBookingRequest(FlightBookingRequest request) {
        Optional<User> user = userRepo.findById(request.getUserId());
        if (user.isEmpty()) {
            logger.error("user not found for it {}", request.getUserId());
            throw new ApplicationException(ErrorCodes.USER_NOT_FOUND.getErrorCode(), ErrorCodes.USER_NOT_FOUND.getErrorMessage());
        }
        Seat seat = seatRepo.findByLabel(request.getSeatLabel());
        if (seat == null) {
            logger.error("seat doesn't exist");
            throw new ApplicationException(ErrorCodes.SEAT_DOES_NOT_EXIST.getErrorCode(), "invalid seats");
        }

        List<Booking> seatBooked = bookingRepo.findBySeatIdAndFlightTripId(seat.getId(), request.getTripId());
        if (CollectionUtils.isNotEmpty(seatBooked)) {
            logger.error("seat already booked");
            throw new ApplicationException(ErrorCodes.SEAT_ALREADY_BOOKED.getErrorCode(), ErrorCodes.SEAT_ALREADY_BOOKED.getErrorMessage());
        }
    }

    @Override
    public void bookFlightTicketRandom() {
        Pageable page = PageRequest.of(0,120, Sort.by(Sort.Direction.ASC,"id"));
        Page<User> users = userRepo.filterDataByPageable(page);
        List<User> userList = users.getContent();

        for (User user : userList){
            CompletableFuture.runAsync(()->bookingHelperService.book(user));
//            book(user);
        }

    }

}

/**
 *
 *     READ, - read lock
 *     WRITE, -
 *     OPTIMISTIC, - read lock, version exception
 *     OPTIMISTIC_FORCE_INCREMENT, - read lock with forced version upgrade even on read
 *     PESSIMISTIC_READ, - shared lock, two writes cannot be aquired, T will be blocked until other T releases lock
 *     PESSIMISTIC_WRITE, - only one T can perform read or writes, highest level of isolation
 *     PESSIMISTIC_FORCE_INCREMENT,
 *     NONE;
 *
 *      Serial requests - sequential request - having time delay
 *      PARALLEL REQUESTS - at same time
 *          IF OUR DATABASE IS SERIAL OR SUPPORTS serialization - then in this case should have same behaviour as if requests have come serially
 *
 */
/**
 *  do no use optimistic locking when there is lot of parallelism
 *  only few will success and others will fail
 * for booking we need for write lock so that different Tx doesn't update the row
 */