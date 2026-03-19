package ru.bend.processor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bend.service.AccountService;
import ru.bend.service.UserService;

import java.util.Scanner;

@Configuration
public class ProcessorsConfiguration {

    @Bean
    public AccountCloseProcessor accountCloseProcessor(
            AccountService accountService, UserService userService, Scanner scanner
    ) {
        return new AccountCloseProcessor(accountService, userService, scanner);
    }

    @Bean
    public AccountCreateProcessor accountCreateProcessor(
            AccountService accountService, UserService userService, Scanner scanner
    ) {
        return new AccountCreateProcessor(accountService, userService, scanner);
    }

    @Bean
    public AccountDepositProcessor accountDepositProcessor(
            AccountService accountService, Scanner scanner
    ) {
        return new AccountDepositProcessor(accountService, scanner);
    }

    @Bean
    public AccountTransferProcessor accountTransferProcessor(
            AccountService accountService, Scanner scanner
    ) {
        return new AccountTransferProcessor(accountService, scanner);
    }

    @Bean
    public AccountWithdrawProcessor accountWithdrawProcessor(
            AccountService accountService, Scanner scanner
    ) {
        return new AccountWithdrawProcessor(accountService, scanner);
    }

    @Bean
    public ShowAllUsersProcessor showAllUsersProcessor(
        UserService userService
    ) {
        return new ShowAllUsersProcessor(userService);
    }

    @Bean
    public UserCreateProcessor userCreateProcessor(
            Scanner scanner, UserService userService
    ) {
        return new UserCreateProcessor(scanner, userService);
    }
}
