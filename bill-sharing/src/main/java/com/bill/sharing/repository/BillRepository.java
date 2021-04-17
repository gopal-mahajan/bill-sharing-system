package com.bill.sharing.repository;

import com.bill.sharing.entity.Bill;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BillRepository {

    Map<String, Bill> billDetails;

    Map<String, List<String>> billGroupDetails;

   public  BillRepository(){
        this.billDetails = new HashMap<>();
        this.billGroupDetails = new HashMap<>();
    }

    public Map<String, List<String>> getBillGroupDetails() {
        return billGroupDetails;
    }

    public void setBillGroupDetails(Map<String, List<String>> billGroupDetails) {
        this.billGroupDetails = billGroupDetails;
    }


    public void addBill(Bill bill) {
        billDetails.put(bill.getBillId(), bill);
        List<String> billsForGroup = billGroupDetails.getOrDefault(bill.getGroupId(), new ArrayList<>());
        billsForGroup.add(bill.getBillId());
        billGroupDetails.put(bill.getGroupId(), billsForGroup);
    }

    public List<String> getBillIdsByGroupId(String group) {
        return billGroupDetails.getOrDefault(group,new ArrayList<>());
    }

    public Bill getBillByBillId(String billId) {
        return billDetails.get(billId);
    }
}
