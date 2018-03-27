package com.thoughtworks;

import java.util.Date;

public class CreditTransaction extends Transaction {
    public CreditTransaction(double amount, String anotherAccount, double balance , Date date) {
        super(anotherAccount, amount, balance,date);
    }

    public CreditTransaction(double amount, String to,double balance) {
        super(to,amount, balance,new Date());
    }
}
