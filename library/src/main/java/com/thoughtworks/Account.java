package com.thoughtworks;

public class Account {
    private String accountNumber;
    private final int balance;

    public Account(String accountNumber, int balance) throws MinimumBalanceError, InvalidAccountNumber {
        if(!accountNumber.matches("\\d{4}-\\d{4}")){
            throw new InvalidAccountNumber();
        }
        this.accountNumber = accountNumber;
        if(balance<1000){
            throw new MinimumBalanceError();
        }
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
