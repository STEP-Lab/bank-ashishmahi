package com.thoughtworks;

import java.util.Date;
import java.util.Objects;

public abstract class Transaction {
    protected final double amount;
    protected final String source;
    protected final Date date;

    public Transaction(String source, double amount, Date date) {
        this.source = source;
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
                Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, source);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", source='" + source + '\'' +
                ", date=" + date +
                '}';
    }

    public double getAmount() {
        return amount;
    }
}
