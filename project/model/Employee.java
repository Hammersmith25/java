package model;

import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Employee extends User {
    @Serial
    private static final long serialVersionUID = 1L;

    private double salary;
    private LocalDate hireDate;
    private final List<Message> messages = new ArrayList<>();

    protected Employee(String id,
                       String passwordHash,
                       String firstName,
                       String lastName,
                       String email,
                       double salary,
                       LocalDate hireDate) {
        super(id, passwordHash, firstName, lastName, email);
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public void sendMessage(Employee receiver, Message message) {
        if (receiver == null || message == null) {
            return;
        }
        messages.add(message);
        receiver.receiveMessage(message);
    }

    protected void receiveMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return super.toString() + ", salary=" + salary + ", hireDate=" + hireDate;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Employee && super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
