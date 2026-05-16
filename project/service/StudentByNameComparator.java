package service;

import model.Student;

import java.util.Comparator;

public class StudentByNameComparator implements Comparator<Student> {
    @Override
    public int compare(Student first, Student second) {
        return first.getFullName().compareToIgnoreCase(second.getFullName());
    }
}
