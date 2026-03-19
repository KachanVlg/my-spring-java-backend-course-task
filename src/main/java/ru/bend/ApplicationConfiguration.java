package ru.bend;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.bend.processor.OperationProcessor;
import ru.bend.properties.AccountProperties;
import ru.bend.service.AccountService;
import ru.bend.service.OperationsConsoleListener;
import ru.bend.service.UserService;

import java.util.List;
import java.util.Scanner;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

}
