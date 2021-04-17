package com.bill.sharing.service;


import java.util.List;

public interface GroupService {

    void addGroup(String groupId, String groupName, List<String> emailIds);
}
