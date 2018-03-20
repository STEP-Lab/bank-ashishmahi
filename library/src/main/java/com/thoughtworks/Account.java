package com.thoughtworks;

public class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) throws MinimumBalanceError, InvalidAccountNumber {
        if(!accountNumber.matches("\\d{4}-\\d{4}")){
            throw new InvalidAccountNumber();
        }
        this.accountNumber = accountNumber;
        if(balance<1000){
            throw new MinimumBalanceError();
        }
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void withDraw(double amount) throws MinimumBalanceError {
        if((this.getBalance()-amount)<1000){
            throw new MinimumBalanceError();
        }
        this.balance -= amount;
    }
}
