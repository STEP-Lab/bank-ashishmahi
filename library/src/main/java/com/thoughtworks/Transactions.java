package com.thoughtworks;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Transactions {
    public ArrayList<Transaction> list;

    public Transactions() {
         this.list = new ArrayList<>();
    }

    public void debit(double amount, String to) {
        this.list.add(new DebitTransaction(amount,to));
    }

    public void credit(double amount, String from) {
        this.list.add(new CreditTransaction(amount,from));
    }

    public Transactions filterByAmountGreaterThan(double amount) {
        Transactions transactions = new Transactions();
        for (Transaction transacction: list) {
            if(transacction.getAmount()>amount){
                transactions.list.add(transacction);
            }
        }
        return transactions;
    }

    public void print(PrintWriter writer) {
        for (Transaction transaction:list) {
            writer.println(transaction.toString());
        }
    }
}
