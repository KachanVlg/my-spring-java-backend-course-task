package ru.bend.service;
import org.springframework.stereotype.Component;
import ru.bend.model.Account;
import ru.bend.model.User;

import java.util.*;

@Component
public class UserService {

    private int lastUserId;
    private final Map<Integer, User> users;
    private final Set<String> takenLogins;
    private final AccountService accountService;

    public UserService(AccountService accountService) {
        this.users = new HashMap<>();
        this.takenLogins = new HashSet<>();
        this.lastUserId = 0;
        this.accountService = accountService;
    }

    public Optional<User> findUserById(int id) {
        return Optional.ofNullable(users.get(id));
    }

    public User createUser(String login) {
        if (takenLogins.contains(login)) {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует");
        }

        takenLogins.add(login);
        lastUserId++;
        User user = new User(lastUserId, login);
        users.put(lastUserId, user);

        Account account = accountService.createAccount(lastUserId);
        user.addAccount(account);

        return user;
    }

    public List<User> getAllUsers() {
        return users.values().stream().toList();
    }
}
