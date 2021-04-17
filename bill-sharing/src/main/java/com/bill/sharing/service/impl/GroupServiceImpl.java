package com.bill.sharing.service.impl;

import com.bill.sharing.repository.GroupRepository;
import com.bill.sharing.entity.Group;
import com.bill.sharing.entity.User;
import com.bill.sharing.service.GroupService;
import com.bill.sharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    UserService userService;

    @Autowired
    GroupRepository groupRepository;

    @Override
    public void addGroup(String groupId, String groupName, List<String> emailIds) {
        Group group = new Group();
        group.setId(groupId);
        group.setGroupName(groupName);
        List<User> users = new ArrayList<>();
        for (String emailId : emailIds) {
            users.add(userService.getUser(emailId));
        }
        group.setGroupMembers(users);
        groupRepository.setGroupMap(group);
    }
}
