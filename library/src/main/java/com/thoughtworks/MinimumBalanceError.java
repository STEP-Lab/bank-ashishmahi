package com.thoughtworks;

public class MinimumBalanceError extends Throwable {
    public MinimumBalanceError() {
        super("Insufficient Opening Balance");
    }
}
