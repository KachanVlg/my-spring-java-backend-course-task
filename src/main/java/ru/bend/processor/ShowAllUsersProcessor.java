package ru.bend.processor;

import org.springframework.stereotype.Component;
import ru.bend.model.OperationType;
import ru.bend.service.UserService;

@Component
public class ShowAllUsersProcessor implements OperationProcessor{

    private final UserService userService;

    public ShowAllUsersProcessor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void processOperation() {
        System.out.println(userService.getAllUsers());
    }

    @Override
    public OperationType operationType() {
        return OperationType.SHOW_ALL_USERS;
    }
}
