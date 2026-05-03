package model;

import repository.Database;

import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Admin extends Employee {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<String> logs = new ArrayList<>();

    public Admin(String id,
                 String passwordHash,
                 String firstName,
                 String lastName,
                 String email,
                 double salary,
                 LocalDate hireDate) {
        super(id, passwordHash, firstName, lastName, email, salary, hireDate);
    }

    public void addUser(User user) {
        Database.getInstance().addUser(user);
        logs.add("Added user: " + user.getId());
    }

    public void removeUser(User user) {
        Database.getInstance().removeUser(user.getId());
        logs.add("Removed user: " + user.getId());
    }

    public void updateUser(User user) {
        Database.getInstance().updateUser(user);
        logs.add("Updated user: " + user.getId());
    }

    public List<String> viewLogs() {
        return new ArrayList<>(logs);
    }

    @Override
    public String toString() {
        return super.toString() + ", logs=" + logs.size();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Admin && super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
