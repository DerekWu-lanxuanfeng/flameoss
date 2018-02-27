package com.flame.flameoss.modules.user.service;

import com.flame.flameoss.modules.user.repository.UserRepository;
import com.flame.flameoss.modules.user.entiry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByAccount(String userAccount) {
        return userRepository.findByUserAccount(userAccount);
    }

}
