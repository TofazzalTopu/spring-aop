package com.info.aop.service;

import com.info.aop.User;
import com.info.aop.exception.CustomException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUser(String id) throws CustomException {
        throw new CustomException("User not found id: " + id);
    }
}
