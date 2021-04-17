package com.bill.sharing.controllers;


import com.bill.sharing.entity.Contributors;
import com.bill.sharing.entity.User;
import com.bill.sharing.service.BillingService;
import com.bill.sharing.service.GroupService;
import com.bill.sharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {
    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    BillingService billingService;

    @PostMapping("group")
    ResponseEntity addGroup(@RequestParam(value = "groupId") String groupId,
                           @RequestParam(value = "groupName") String groupName,
                           @RequestBody List<String> emailIds)
    {

        groupService.addGroup(groupId, groupName, emailIds);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/user")
    ResponseEntity addUser(@RequestBody List<User> user) {
        userService.addUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/bill")
    ResponseEntity addBill(@RequestParam("billId") String billId,
                           @RequestParam(value = "groupId") String groupId,
                           @RequestParam(value = "amount") Integer amount,
                           @RequestParam(value = "paidBy") String paidBy,
                           @RequestBody List<Contributors> contributors) {
        billingService.addBill(billId, groupId, amount, paidBy, contributors);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/amount/owe")
    ResponseEntity userOwe(@RequestParam(value = "emailId") String emailId) {
        return ResponseEntity.ok(billingService.getUserAmount(emailId));
    }
}
