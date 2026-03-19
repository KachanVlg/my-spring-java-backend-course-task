package ru.bend.service;


import ru.bend.model.Account;
import ru.bend.properties.AccountProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountService {


    private int lastAccountId;
    private final Map<Integer, Account> accounts;
    private final AccountProperties accountProperties;

    public AccountService(AccountProperties accountProperties) {
        this.lastAccountId = 0;
        this.accounts = new HashMap<>();
        this.accountProperties = accountProperties;
    }

    public Account createAccount(int userId) {
        lastAccountId++;
        Account account = new Account(lastAccountId, userId, accountProperties.getDefaultAmount());
        accounts.put(lastAccountId, account);

        return account;
    }

    public Optional<Account> findAccountById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public void depositAccount(int accountId, int amountToDeposit) {
        Account account = findAccountByIdOrThrow(accountId);

        if (amountToDeposit <= 0) {
            throw new IllegalArgumentException("Сумма депозита должна быть > 0");
        }

        account.increaseMoneyAmount(amountToDeposit);
    }

    public void withdrawAccount(int accountId, int amountToWithdraw) {
        Account account = findAccountByIdOrThrow(accountId);

        if (amountToWithdraw <= 0) {
            throw new IllegalArgumentException("Сумма снятия должна быть > 0");
        }

        int curAmount = account.moneyAmount();

        if (curAmount < amountToWithdraw) {
            throw new IllegalArgumentException("Сумма снятия больше баланса аккаунте");
        }

        account.decreaseMoneyAmount(amountToWithdraw);
    }

    public void closeAccount(int id) {
        Account account = findAccountByIdOrThrow(id);

        accounts.remove(id);
    }

    private Account findAccountByIdOrThrow(int id) {
        return findAccountById(id).orElseThrow(
                () -> new IllegalArgumentException("Аккаунт с id=%s не найден".formatted(id)));
    }

    int applyCommission(int amountToTransfer) {
        return amountToTransfer * accountProperties.getTransferCommission() / 100;
    }

    public void accountTransfer(int idFrom, int idTo, int amountToTransfer) {
        Account accountFrom = findAccountByIdOrThrow(idFrom);
        Account accountTo = findAccountByIdOrThrow(idTo);

        if (idFrom == idTo) {
            throw new IllegalArgumentException("Невозможно осущесвить перевод между одним и тем жесчетом");
        }

        if (accountFrom.userId() != accountTo.userId()) {
            amountToTransfer = applyCommission(amountToTransfer);
        }

        withdrawAccount(idFrom, amountToTransfer);
        depositAccount(idTo, amountToTransfer);
    }
}
