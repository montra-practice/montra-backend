package com.epam.service;

import com.epam.data.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User getUserInfo(Long id);

}
