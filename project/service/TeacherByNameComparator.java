package service;

import model.Teacher;

import java.util.Comparator;

public class TeacherByNameComparator implements Comparator<Teacher> {
    @Override
    public int compare(Teacher first, Teacher second) {
        return first.getFullName().compareToIgnoreCase(second.getFullName());
    }
}
