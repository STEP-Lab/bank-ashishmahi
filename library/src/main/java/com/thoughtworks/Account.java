package com.thoughtworks;

public class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) throws MinimumBalanceError, InvalidAccountNumber {
        validateAccountNumber(accountNumber);
        this.accountNumber = accountNumber;
        validateMinimumBalance(balance);
        this.balance = balance;
    }

    private void validateMinimumBalance(double balance) throws MinimumBalanceError {
        if(balance<1000){
            throw new MinimumBalanceError();
        }
    }

    private void validateAccountNumber(String accountNumber) throws InvalidAccountNumber {
        if(!accountNumber.matches("\\d{4}-\\d{4}")){
            throw new InvalidAccountNumber();
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void withDraw(double amount) throws MinimumBalanceError {
        validateMinimumBalance(this.getBalance()-amount);
        this.balance -= amount;
    }
}
