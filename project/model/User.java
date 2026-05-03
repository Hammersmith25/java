package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class User implements Observer, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String email;
    private final List<String> notifications = new ArrayList<>();

    protected User(String id, String passwordHash, String firstName, String lastName, String email) {
        this.id = id;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void login() {
        System.out.println(getFullName() + " logged in.");
    }

    public void logout() {
        System.out.println(getFullName() + " logged out.");
    }

    @Override
    public void update(News news) {
        String message = "News update for " + getFullName() + ": " + news.getTitle() + " (" + news.getDate() + ")";
        notifications.add(message);
        System.out.println(message);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getNotifications() {
        return new ArrayList<>(notifications);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id='" + id + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User user)) {
            return false;
        }
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
