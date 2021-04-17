package com.bill.sharing.entity;

import org.springframework.stereotype.Component;

@Component
public class Contributors {
    private String emailId;
    private int contribution;

    public Contributors(){}

    public Contributors(String emailId, int contribution) {
        this.emailId = emailId;
        this.contribution = contribution;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getContribution() {
        return contribution;
    }

    public void setContribution(int contribution) {
        this.contribution = contribution;
    }
}