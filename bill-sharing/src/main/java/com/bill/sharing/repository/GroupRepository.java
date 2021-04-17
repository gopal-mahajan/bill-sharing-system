package com.bill.sharing.repository;

import com.bill.sharing.entity.Group;
import com.bill.sharing.entity.User;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class GroupRepository {
    private final Map<String, Group> groupMapping;

    private final Map<String, List<String>> userGroupMapping;

    GroupRepository() {
        groupMapping = new HashMap<>();
        userGroupMapping = new HashMap<>();
    }

    public Map<String, Group> getGroupMap() {
        return groupMapping;
    }

    public void setGroupMap(Group group) {
        // TODO duplicate check
        groupMapping.putIfAbsent(group.getId(), group);
        for (User user : group.getGroupMembers())
        {
            List<String> userGroupList = userGroupMapping.getOrDefault(user.getEmailid(), new ArrayList<>());
            userGroupList.add(group.getId());
            userGroupMapping.put(user.getEmailid(), userGroupList);
        }
    }

    public Group getGroup(String groupId) {
        return groupMapping.get(groupId);
    }

    public void printGroups() {
        for (Map.Entry<String, Group> entry : groupMapping.entrySet()) {
            Group group = entry.getValue();
            System.out.println(group.toString());
        }
    }

    public List<String> getUserGroups(String emailId)
    {
      return  userGroupMapping.getOrDefault(emailId,new ArrayList<>());
    }


}
