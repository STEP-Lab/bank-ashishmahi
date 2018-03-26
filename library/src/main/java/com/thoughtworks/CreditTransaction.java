package com.thoughtworks;

import java.util.Date;

public class CreditTransaction extends Transaction {

    public CreditTransaction(double amount, String anotherAccount,Date date) {
        super(anotherAccount, amount, date);

    }

    public CreditTransaction(double amount, String to) {
        super(to,amount, new Date());
    }
}
