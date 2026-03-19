package ru.bend.processor;

import ru.bend.model.Account;
import ru.bend.model.OperationType;
import ru.bend.model.User;
import ru.bend.service.AccountService;
import ru.bend.service.UserService;

import java.util.List;
import java.util.Scanner;

public class AccountCloseProcessor implements OperationProcessor{

    private final AccountService accountService;
    private final UserService userService;
    private final Scanner scanner;

    public AccountCloseProcessor(AccountService accountService, UserService userService, Scanner scanner) {
        this.accountService =  accountService;
        this.userService = userService;
        this.scanner = scanner;
    }


    @Override
    public void processOperation() {

        int accountId;
        System.out.println("Введите айди аккаунта");

        try {
            accountId = scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("Введеный айди должен быть целым положительным числом");
        }

        Account account = accountService.findAccountById(accountId).orElseThrow(
                () -> new IllegalArgumentException("Аккаунт с переданным айди не найден")
        );

        int userId = account.userId();
        User user = userService.findUserById(userId).orElseThrow(
                () -> new IllegalArgumentException("Владелец аккаунта не найден")
        );

        List<Account> accounts = user.accounts();

        int accountIdForSearch = accountId;
        Account accountToDeposit = accounts.stream()
                .filter(acc -> acc.id() != accountIdForSearch)
                .findFirst().orElseThrow(
                        () -> new IllegalArgumentException("У пользователя один аккаунт. Удаление невозможно")
                );

        accountService.closeAccount(accountId);
        accountService.depositAccount(accountToDeposit.id(), account.moneyAmount());

        System.out.printf("Удален аккаунт с айди %d. Средства переведены на аккаунт с id %d",
                accountId, accountToDeposit.id());
    }

    @Override
    public OperationType operationType() {
        return OperationType.ACCOUNT_CLOSE;
    }
}
