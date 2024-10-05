package com.masterclass.impl;

import com.masterclass.entity.Booking;
import com.masterclass.entity.User;
import com.masterclass.exceptionHandler.ApplicationException;
import com.masterclass.repository.BookingRepo;
import com.masterclass.repository.SeatBackUpRepo;
import com.masterclass.repository.SeatRepo;
import com.masterclass.service.BookingHelperService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingHelperServiceImpl implements BookingHelperService {

    private static final Logger logger = LoggerFactory.getLogger(BookingHelperServiceImpl.class);

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private SeatBackUpRepo seatBackUpRepo;

    @Override
    @Transactional
    public void book(User user) {
        try {
            logger.info("bookFlightTicket : for user {} and thread id {}", user, Thread.currentThread().getName());
            List<Booking> bookings = bookingRepo.getAllBookings();
            List<Integer> activeSeats = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(bookings)) {
                activeSeats = bookings.stream().map(t -> t.getSeatId()).collect(Collectors.toList());
            }

            Integer availableSeatId = seatRepo.findBySeatIdNotIn(activeSeats, PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "id")));
            if (availableSeatId == null) {
                logger.info("no seat found");
                return;
            }
            logger.info("seat found for user {} and id {} : thread id {}", user, availableSeatId, Thread.currentThread().getId());
            Booking booking = new Booking();
            booking.setFlightTripId(1);
            booking.setUserId(user.getId());
            booking.setSeatId(availableSeatId);

            bookingRepo.saveAndFlush(booking);
            logger.info("bookFlightTicket : success for booking {}", booking);
        } catch (Exception exception) {
            logger.error("Exception occurred while trying to book seat for user {} & thread id {} : exception {}", user, Thread.currentThread().getId(), ExceptionUtils.getStackTrace(exception));
            throw new ApplicationException(107, "database exception");
        }

    }

    @Override
    @Transactional
    public void bookV1(User user) {
        try {

//            List<Integer> availableSeats = seatRepo.findAvailableSeats();
//            if (CollectionUtils.isEmpty(availableSeats)) {
//                logger.info("no seat found");
//                return;
//            }
            Integer availableSeatId = seatRepo.findAvailableSeat();

            if (availableSeatId == null) {
                logger.info("no seat found");
                return;
            }
//            logger.info("seat found for user {} and id {} : thread id {}", user, availableSeats.get(0),Thread.currentThread().getId());
//            logger.info("seat found for user {} and id {} : thread id {}", user, availableSeatId,Thread.currentThread().getId());
            Booking booking = new Booking();
            booking.setFlightTripId(1);
            booking.setUserId(user.getId());
            booking.setSeatId(availableSeatId);

            bookingRepo.saveAndFlush(booking);
            logger.info("bookFlightTicket : success for booking {}", booking);
        } catch (Exception exception) {
            logger.error("Exception occurred while trying to book seat for user {} & thread id {} : exception {}", user, Thread.currentThread().getId(), ExceptionUtils.getStackTrace(exception));
            throw new ApplicationException(107, "database exception");
        }

    }

}
