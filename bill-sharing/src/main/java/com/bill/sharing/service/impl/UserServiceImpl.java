package com.bill.sharing.service.impl;

import com.bill.sharing.repository.UserRepository;
import com.bill.sharing.entity.User;
import com.bill.sharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void addUser(List<User> user) {
        for(User u : user){
        userRepository.setUserMap(u);
        }
        userRepository.printUsers();
    }


    @Override
    public User getUser(String emailId) {
        return userRepository.getUser(emailId);
    }


}
