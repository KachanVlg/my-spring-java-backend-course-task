package ru.bend;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.bend.processor.OperationProcessor;
import ru.bend.service.AccountService;
import ru.bend.service.OperationsConsoleListener;
import ru.bend.service.UserService;

import java.util.List;
import java.util.Scanner;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {


    @Bean
    public AccountService accountService(
            @Value("${account.default-amount}") int defaultAmount,
            @Value("${account.transfer-commission}") int transferCommission
    ) {
        return new AccountService(defaultAmount, transferCommission);
    }

    @Bean
    public UserService userService(AccountService accountService) {
        return new UserService(accountService);
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public OperationsConsoleListener operationsConsoleListener(List<OperationProcessor> processors, Scanner scanner) {
        return new OperationsConsoleListener(processors, scanner);
    }
}
