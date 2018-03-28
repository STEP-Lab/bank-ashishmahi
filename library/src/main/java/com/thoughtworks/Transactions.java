package com.thoughtworks;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Transactions {
    public ArrayList<Transaction> list;

    public Transactions() {
         this.list = new ArrayList<>();
    }

    public void debit(double amount, String to,double balance) {
        this.list.add(new DebitTransaction(amount,to,balance));
    }

    public void credit(double amount, String from,double balance) {
        this.list.add(new CreditTransaction(amount,from,balance));
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

    public Transactions getAllCredit() {
        Transactions transactions = new Transactions();
        for (Transaction transaction : list) {
            if(transaction instanceof CreditTransaction){
                transactions.list.add(transaction);
            }
        }
            return transactions;
    }
    public Transactions getAllDebit() {
        Transactions transactions = new Transactions();
        for (Transaction transaction : list) {
            if(transaction instanceof DebitTransaction){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public void printCsv(PrintWriter writer) {
        for (Transaction transaction:list) {
            writer.println(transaction.toCsv());
        }
    }

    public Transactions getAllTransactionAfter(Date date) {
        Transactions transactions = new Transactions();
        for (Transaction transaction : list) {
            if(transaction.getDate().compareTo(date)==1){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }
}
