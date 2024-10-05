package com.masterclass.impl;

import com.masterclass.entity.Seat;
import com.masterclass.repository.SeatRepo;
import com.masterclass.service.SeatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepo seatRepo;

    @Override
    public void addBulkSeats() {
        List<Seat> allSeats = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String[] seatInRow = new String[]{"A", "B", "C", "D", "E", "F"};
            for (String seat : seatInRow) {
                Seat entity = new Seat();
                entity.setLabel(StringUtils.join(seat, "-", i));
                entity.setFlightId("1");
                allSeats.add(entity);
            }
        }
        seatRepo.saveAll(allSeats);
        seatRepo.flush();
    }
}
