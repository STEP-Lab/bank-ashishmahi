package com.thoughtworks;

public class AccountNumber {
    private final String accountNumber;

    private AccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    private static void validateAccountNumber(String accountNumber) throws InvalidAccountNumber {
        if(!accountNumber.matches("\\d{4}-\\d{4}")){
            throw new InvalidAccountNumber();
        }
    }

    public static AccountNumber createAccountNumber(String accountNumber) throws InvalidAccountNumber {
        validateAccountNumber(accountNumber);
        return new AccountNumber(accountNumber);
    }
}
