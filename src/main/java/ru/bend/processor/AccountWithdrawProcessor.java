package ru.bend.processor;

import org.springframework.stereotype.Component;
import ru.bend.model.OperationType;
import ru.bend.service.AccountService;

import java.util.Scanner;

@Component
public class AccountWithdrawProcessor implements OperationProcessor{


    private final AccountService accountService;
    private final Scanner scanner;

    public AccountWithdrawProcessor(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void processOperation() {

        System.out.println("Введите айди аккаунта для снятия");
        int accountId = 0;
        try {
            accountId = scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("Введеный айди должен быть целым положительным числом");
        }

        System.out.println("Введите сумму снятия");
        int amountToWithdraw = 0;
        try {
            amountToWithdraw = scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("Введеная сумма снятия должна быть целым");
        }

        accountService.withdrawAccount(accountId, amountToWithdraw);

        System.out.printf("С аккаунта с айди %d снята сумма %d", accountId, amountToWithdraw);
    }

    @Override
    public OperationType operationType() {
        return OperationType.ACCOUNT_WITHDRAW;
    }
}
