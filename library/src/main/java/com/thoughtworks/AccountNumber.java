package com.thoughtworks;

public class AccountNumber {
    public AccountNumber(String accountNumber) throws InvalidAccountNumber {
        validateAccountNumber(accountNumber);
    }
    private void validateAccountNumber(String accountNumber) throws InvalidAccountNumber {
        if(!accountNumber.matches("\\d{4}-\\d{4}")){
            throw new InvalidAccountNumber();
        }
    }
}
