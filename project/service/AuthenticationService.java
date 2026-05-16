package service;

import model.User;
import repository.Database;

import java.util.Optional;

public class AuthenticationService {
    private final Database database;

    public AuthenticationService(Database database) {
        this.database = database;
    }

    public Optional<User> authenticate(String userId, String passwordHash) {
        User user = database.getUser(userId);
        if (user == null || !user.getPasswordHash().equals(passwordHash)) {
            return Optional.empty();
        }
        user.login();
        return Optional.of(user);
    }

    public User requireAuthenticated(String userId, String passwordHash) {
        return authenticate(userId, passwordHash)
                .orElseThrow(() -> new SecurityException("Invalid user id or password."));
    }
}
