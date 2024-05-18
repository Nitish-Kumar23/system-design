package com.masterclass.service;

import com.masterclass.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface BookingHelperService {
    void book(User user);

    @Transactional
    void bookV1(User user);
}
