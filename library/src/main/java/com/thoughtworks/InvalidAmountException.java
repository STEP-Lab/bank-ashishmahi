package com.thoughtworks;

public class InvalidAmountException extends Throwable {
    public InvalidAmountException() {
        super("Invalid amount to deal with");
    }
}
