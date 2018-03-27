package com.thoughtworks;

public class Account {
    private static final int minimumBalance = 1000;
    private final AccountNumber accountNumber;
    private double balance;
    private Transactions transactions;

    private Account(AccountNumber accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new Transactions();
    }

    private static void validateMinimumBalance(double balance) throws MinimumBalanceError {
        if(balance< minimumBalance){
            throw new MinimumBalanceError();
        }
    }

    public double getBalance() {
        return balance;
    }

    public void withDraw(double amount) throws MinimumBalanceError, InvalidAmountException {
        validateAmount(amount);
        validateMinimumBalance(this.getBalance()-amount);
        this.balance -= amount;
        this.transactions.debit(amount,accountNumber.getAccountNumber(),this.getBalance());
    }

    private void validateAmount(double amount) throws InvalidAmountException {
        if(amount<0){
            throw new InvalidAmountException();
        }
    }

    public static Account createAccount(AccountNumber accountNumber,double balance) throws MinimumBalanceError {
        validateMinimumBalance(balance);
        return new Account(accountNumber,balance);
    }

    public void credit(double amount) throws InvalidAmountException {
        validateAmount(amount);
        this.balance += amount;
        this.transactions.credit(amount,accountNumber.getAccountNumber(),this.getBalance());
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public String getAccountNumber() {
        return accountNumber.getAccountNumber();
    }
}
