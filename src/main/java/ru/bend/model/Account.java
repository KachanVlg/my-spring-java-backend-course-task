package ru.bend.model;

public class Account {

    private final int id;
    private final int userId;
    private int moneyAmount;

    public Account(int id, int userId, int moneyAmount) {
        this.id = id;
        this.userId = userId;
        this.moneyAmount = moneyAmount;
    }

    public int id() {
        return this.id;
    }

    public int userId() {
        return this.userId;
    }

    public int moneyAmount() {
        return this.moneyAmount;
    }

    public void decreaseMoneyAmount(int decreaseAmount) {
        this.moneyAmount -= decreaseAmount;
    }

    public void increaseMoneyAmount(int increaseAmount) {
        this.moneyAmount += increaseAmount;
    }

    @Override
    public String toString() {
        return "Account id: " + id + ". Account userId: " + userId + ". Account moneyAmount: " + moneyAmount;
    }
}
