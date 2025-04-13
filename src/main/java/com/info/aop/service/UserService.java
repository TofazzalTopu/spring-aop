package com.info.aop.service;

import com.info.aop.User;
import com.info.aop.annotation.RedisCacheable;
import com.info.aop.exception.CustomException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @RedisCacheable(ttl = 60)
    public User getUser(String id) throws CustomException {
        throw new CustomException("User not found id: " + id);
    }

    @RedisCacheable(ttl = 60)
    public User findById(Long id) {
        return new User(id, "John Doe");
    }
}
