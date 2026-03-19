package ru.bend.service;


import org.springframework.stereotype.Component;
import ru.bend.model.OperationType;
import ru.bend.processor.OperationProcessor;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class OperationsConsoleListener {

    private final Map<OperationType, OperationProcessor> processors;
    private final Scanner scanner;

    public OperationsConsoleListener(List<OperationProcessor> processors, Scanner scanner) {
        this.processors = new EnumMap<>(OperationType.class);
        processors.forEach(processor -> this.processors.put(processor.operationType(), processor));
        this.scanner = scanner;
    }

    public void listen() {

        while(true) {

            System.out.println("Введите команду");
            for (OperationType operationType: OperationType.values()) {
                System.out.println(operationType.toString());
            }

            String command = scanner.nextLine();

            boolean invalidCommand = false;
            try {
                OperationType.valueOf(command);
            } catch (Exception e) {
                invalidCommand = true;
            }

            if (invalidCommand) {
                System.out.println("Некорректная комманда");
            } else {

                try {
                 processors.get(OperationType.valueOf(command)).processOperation();
                } catch (Exception e) {
                    System.out.printf("Ошибка при выполнении команды: %s\n", e.getMessage());
                }

                System.out.println("Нажмите enter для продолжения");
                scanner.nextLine();
            }
        }
    }

}
