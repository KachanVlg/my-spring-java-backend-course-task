package ru.bend.processor;

import ru.bend.model.OperationType;
import ru.bend.model.User;
import ru.bend.service.AccountService;
import ru.bend.service.UserService;

import java.util.Scanner;

public class UserCreateProcessor implements OperationProcessor{

    private final Scanner scanner;
    private final UserService userService;

    public UserCreateProcessor(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }


    @Override
    public void processOperation() {
        String login;

        System.out.println("Введите логин: ");
        login = scanner.nextLine();

        if (login.isBlank()) {
            throw new IllegalArgumentException("Логин не должен быть пустым");
        }

        User user = userService.createUser(login);
        System.out.println("Создан пользователь: \n" + user.toString());
    }

    @Override
    public OperationType operationType() {
        return OperationType.USER_CREATE;
    }
}
