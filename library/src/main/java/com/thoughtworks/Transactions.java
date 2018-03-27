package com.thoughtworks;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

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
        for (Transaction transaction: list) {
            if(transaction.getAmount()>amount){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public void print(PrintWriter writer) {
        for (Transaction transaction:list) {
            writer.println(transaction.toString());
        }
    }

    public Transactions filterByType(String type) {
        Transactions transactions = new Transactions();
        for (Transaction transaction: list) {
            if(transaction.getType()==type){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

//    public Transactions filterByDateAfter(Date date) {
//        Transactions transactions = new Transactions();
//        for (Transaction transaction: list) {
//            if(transaction.getDate()>date){
//                transactions.list.add(transaction);
//            }
//        }
//        return transactions;
//    }

}
