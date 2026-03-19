package ru.bend.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final int id;
    private final String login;
    private List<Account> accountList;

    public User(int id, String login) {
        this.id = id;
        this.login = login;
        this.accountList = new ArrayList<>();
    }

    public int id() {
        return this.id;
    }

    public String login() {
        return this.login;
    }

    public List<Account> accounts() {
        return accountList;
    }

    public void addAccount(Account account) {
        if (accountList == null) {
            accountList = new ArrayList<>();
        }

        accountList.add(account);
    }

    public void removeAccount(Account account) {
        accountList.remove(account);
    }

    @Override
    public String toString() {
        return "User id: " + id + ". User login: " + login + ".";
    }
}
