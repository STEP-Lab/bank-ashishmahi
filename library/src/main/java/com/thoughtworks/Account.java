package com.thoughtworks;

public class Account {
    private static final int minimunBalance = 1000;
    private AccountNumber accountNumber;
    private double balance;

    public Account(AccountNumber accountNumber, double balance) throws MinimumBalanceError {
        this.accountNumber = accountNumber;
        validateMinimumBalance(balance);
        this.balance = balance;
    }

    private void validateMinimumBalance(double balance) throws MinimumBalanceError {
        if(balance< minimunBalance){
            throw new MinimumBalanceError();
        }
    }

    public double getBalance() {
        return balance;
    }

    public void withDraw(double amount) throws MinimumBalanceError {
        validateMinimumBalance(this.getBalance()-amount);
        this.balance -= amount;
    }

}
