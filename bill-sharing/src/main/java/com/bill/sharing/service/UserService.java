package com.bill.sharing.service;

import com.bill.sharing.entity.User;

import java.util.List;


public interface UserService {
    void addUser(List<User> user);
    User getUser(String emailId);

}
