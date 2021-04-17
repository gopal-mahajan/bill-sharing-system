package com.bill.sharing.service.impl;

import com.bill.sharing.exceptions.IncorrectBillException;
import com.bill.sharing.repository.BillRepository;
import com.bill.sharing.repository.GroupRepository;
import com.bill.sharing.entity.Bill;
import com.bill.sharing.entity.Contributors;
import com.bill.sharing.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillServiceImpl implements BillingService {

    @Autowired
    BillRepository billRepository;

    @Autowired
    GroupRepository groupRepository;

    @Override
    public void addBill(String billId, String groupId, Integer amount,
                        String paidBy, List<Contributors> contributors) {

        validateBillAmount(amount, contributors);
        Bill bill = new Bill();
        bill.setBillId(billId);
        bill.setGroupId(groupId);
        bill.setTotalAmount(amount);
        bill.setPaidBy(new Contributors(paidBy, amount));

        bill.setContributorsList(contributors);
        billRepository.addBill(bill);

    }

    private void validateBillAmount(Integer amount, List<Contributors> contributors) {
        int sum = 0;
        for (Contributors contributors1 : contributors) {
            sum = sum + contributors1.getContribution();
        }
        if (sum != amount) {
            throw new IncorrectBillException();
        }
    }


    @Override
    public Map<String, Integer> getUserAmount(String emailId) {
        List<String> groups = groupRepository.getUserGroups(emailId);
        Map<String, Integer> amountToPay = new HashMap<>();
        int overAllAmount = 0;
        for (String group : groups) {
            int groupAmount = 0;
            List<String> bills = billRepository.getBillIdsByGroupId(group);
            for (String billId : bills) {
                Bill bill = billRepository.getBillByBillId(billId);
                List<Contributors> contributors = bill.getContributorsList();
                for (Contributors contributors1 : contributors) {
                    if (contributors1.getEmailId().equals(emailId)) {
                        groupAmount = groupAmount + contributors1.getContribution();
                    }
                }
                if (bill.getPaidBy().getEmailId().equals(emailId)) {
                    groupAmount = groupAmount - bill.getTotalAmount();
                }
            }
            amountToPay.put(group, groupAmount);
            overAllAmount = overAllAmount + groupAmount;
        }
        amountToPay.put("OverAll", overAllAmount);
        return amountToPay;
    }
}
