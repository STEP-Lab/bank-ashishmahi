package com.thoughtworks;

import java.util.Date;
import java.util.Objects;

public abstract class Transaction {
    protected final double amount;
    protected final String to;
    protected final Date date;

    public Transaction(String anotherAccount, double amount, Date date) {
        this.to = anotherAccount;
        this.amount = amount;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {

        return Objects.hash(amount, to);
    }
}
