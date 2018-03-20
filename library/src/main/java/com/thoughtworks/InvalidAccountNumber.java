package com.thoughtworks;

public class InvalidAccountNumber extends Throwable {
    public InvalidAccountNumber() {
        super("Account Number is Incorrect");
    }
}
