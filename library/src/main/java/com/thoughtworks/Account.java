package com.thoughtworks;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class Account {
    private static final Money MINIMUM_BALANCE = Money.of(CurrencyUnit.of("INR"), 1000);
    private final AccountNumber accountNumber;
    private Money balance;
    private Transactions transactions;

    private Account(AccountNumber accountNumber, double balance) {
        Money money = Money.of(CurrencyUnit.of("INR"), balance);
        this.accountNumber = accountNumber;
        this.balance = money;
        this.transactions = new Transactions();
    }

    private static void validateMinimumBalance(Money balance) throws MinimumBalanceError {
        if(balance.isLessThan(MINIMUM_BALANCE)) {
            throw new MinimumBalanceError();
        }
    }

    public Money getBalance() {
        return balance;
    }

    public void withDraw(double amount) throws MinimumBalanceError, InvalidAmountException {
        validateAmount(amount);
        validateMinimumBalance(this.getBalance().minus(amount));
        this.balance  = balance.minus(amount);
        this.transactions.debit(amount,accountNumber.getAccountNumber(),this.getBalance().getAmountMajor().doubleValue());
    }

    private void validateAmount(double amount) throws InvalidAmountException {
        if(amount<0){
            throw new InvalidAmountException();
        }
    }

    public static Account createAccount(AccountNumber accountNumber, double balance) throws MinimumBalanceError {
        Money money = Money.of(CurrencyUnit.of("INR"), balance);
        validateMinimumBalance(money);
        return new Account(accountNumber,balance);
    }

    public void credit(double amount) throws InvalidAmountException {
        validateAmount(amount);
        this.balance  = balance.plus(amount);
        this.transactions.credit(amount,accountNumber.getAccountNumber(),this.getBalance().getAmountMajor().doubleValue());
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public String getAccountNumber() {
        return accountNumber.getAccountNumber();
    }
}
