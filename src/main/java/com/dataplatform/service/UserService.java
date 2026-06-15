package com.dataplatform.service;

import com.dataplatform.model.User;
import com.dataplatform.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public User findById(Long id) {
        return userMapper.selectById(id);
    }
}
