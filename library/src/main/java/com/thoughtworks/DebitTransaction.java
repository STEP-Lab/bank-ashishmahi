package com.thoughtworks;

import java.util.Date;

public class DebitTransaction extends Transaction {
    public DebitTransaction(double amount, String anotherAccount,double balance,Date date) {
        super(anotherAccount, amount, balance,date);

    }

    public DebitTransaction(double amount, String to,double balance) {
        super(to,amount, balance,new Date());
    }
}
