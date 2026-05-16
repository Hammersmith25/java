package service;

import model.Student;

import java.util.Comparator;

public class StudentByGpaComparator implements Comparator<Student> {
    @Override
    public int compare(Student first, Student second) {
        return first.compareTo(second);
    }
}
