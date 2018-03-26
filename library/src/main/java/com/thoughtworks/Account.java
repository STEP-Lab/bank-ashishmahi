package com.thoughtworks;

public class Account {
    private static final int minimumBalance = 1000;
    private final AccountNumber accountNumber;
    private double balance;

    private Account(AccountNumber accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
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
        if(amount<0){
            throw new InvalidAmountException();
        }
        validateMinimumBalance(this.getBalance()-amount);
        this.balance -= amount;
    }

    public static Account createAccount(AccountNumber accountNumber,double balance) throws MinimumBalanceError {
        validateMinimumBalance(balance);
        return new Account(accountNumber,balance);
    }

}
