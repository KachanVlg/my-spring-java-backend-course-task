package ru.bend.processor;

import ru.bend.model.OperationType;
import ru.bend.service.AccountService;
import ru.bend.service.UserService;

import java.util.Scanner;

public class AccountTransferProcessor implements OperationProcessor{

    private final AccountService accountService;
    private final Scanner scanner;

    public AccountTransferProcessor(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void processOperation() {

        System.out.println("Введите айди аккаунта снятия");
        int accountIdFrom;
        try {
            accountIdFrom = scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("Введеный айди должен быть целым положительным числом");
        }

        System.out.println("Введите айди аккаунта перевода");
        int accountIdTo;
        try {
            accountIdTo = scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("Введеный айди должен быть целым положительным числом");
        }

        System.out.println("Введите сумму перевода");
        int amountToTransfer;
        try {
            amountToTransfer = scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("Введеная сумма депозита должна быть целым числом");
        }

        accountService.accountTransfer(accountIdFrom, accountIdTo, amountToTransfer);

        System.out.printf("Со счета %d переведена сумма %d на счет %d", accountIdFrom, amountToTransfer, accountIdTo);
    }

    @Override
    public OperationType operationType() {
        return OperationType.ACCOUNT_TRANSFER;
    }
}
