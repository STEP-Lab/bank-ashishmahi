package com.thoughtworks;

import java.util.Date;

public class DebitTransaction extends Transaction {

    public DebitTransaction(double amount, String anotherAccount,Date date) {
        super(anotherAccount, amount, date);

    }

    public DebitTransaction(double amount, String to) {
        super(to,amount, new Date());
    }
}
