package ru.bend.processor;

import ru.bend.model.Account;
import ru.bend.model.OperationType;
import ru.bend.service.AccountService;

import java.util.Scanner;

public class AccountDepositProcessor implements OperationProcessor{

    private final AccountService accountService;
    private final Scanner scanner;

    public AccountDepositProcessor(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }


    @Override
    public void processOperation() {

        System.out.println("Введите айди аккаунта для пополнения");
        int accountId;
        try {
            accountId = scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("Введеный айди должен быть целым положительным числом");
        }

        System.out.println("Введите сумму пополнения");
        int amountToDeposit;
        try {
            amountToDeposit = scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("Введеная сумма депозита должна быть целым числом");
        }

        accountService.depositAccount(accountId, amountToDeposit);

        System.out.printf("Аккаунт с айди %d пополнен на сумму %d%n", accountId, amountToDeposit);
    }

    @Override
    public OperationType operationType() {
        return OperationType.ACCOUNT_DEPOSIT;
    }
}
