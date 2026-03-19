package ru.bend.processor;

import org.springframework.stereotype.Component;
import ru.bend.model.Account;
import ru.bend.model.OperationType;
import ru.bend.model.User;
import ru.bend.service.AccountService;
import ru.bend.service.UserService;

import java.util.Optional;
import java.util.Scanner;

@Component
public class AccountCreateProcessor implements OperationProcessor{

    private final AccountService accountService;
    private final UserService userService;
    private final Scanner scanner;

    public AccountCreateProcessor(AccountService accountService, UserService userService, Scanner scanner) {
        this.accountService = accountService;
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void processOperation() {

        int userId;
        System.out.println("Введите айди пользователя");

        try {
            userId = scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("Введеный айди должен быть целым положительным числом");
        }

        User user = userService.findUserById(userId).orElseThrow(
                () -> new IllegalArgumentException("Пользователь с переданным айди не найден")
        );

        Account account = accountService.createAccount(userId);
        user.addAccount(account);

        System.out.println("Создан аккаунт:\n" + account);
    }

    @Override
    public OperationType operationType() {
        return OperationType.ACCOUNT_CREATE;
    }
}
