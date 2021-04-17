package com.bill.sharing.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bill {

    String billId;
    String groupId;
    int totalAmount;
    List<Contributors> contributorsList;
    Contributors paidBy;

    public Bill() {
        contributorsList = new ArrayList<>();
    }

    public Contributors getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Contributors paidBy) {
        this.paidBy = paidBy;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Contributors> getContributorsList() {
        return contributorsList;
    }

    public void setContributorsList(List<Contributors> contributorsList) {
        this.contributorsList = contributorsList;
    }


}
