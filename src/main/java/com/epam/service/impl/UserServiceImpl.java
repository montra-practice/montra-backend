package com.epam.service.impl;

import com.epam.service.UserService;
import com.epam.dao.UserRepository;
import com.epam.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserInfo(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            return null;
        }
        User userInfo = user.get();
        return userInfo;
    }
}
