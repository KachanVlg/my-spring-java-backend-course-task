package ru.bend;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bend.service.OperationsConsoleListener;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ru.bend");

        OperationsConsoleListener operationsConsoleListener = context.getBean(OperationsConsoleListener.class);

        operationsConsoleListener.listen();
    }
}