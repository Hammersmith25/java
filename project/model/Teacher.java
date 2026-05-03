package model;

import enums.TeacherTitle;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Teacher extends Employee {
    @Serial
    private static final long serialVersionUID = 1L;

    private TeacherTitle title;
    private final List<Course> courses = new ArrayList<>();
    private Researcher researcherRole;
    private final Map<Student, Integer> ratings = new LinkedHashMap<>();

    public Teacher(String id,
                   String passwordHash,
                   String firstName,
                   String lastName,
                   String email,
                   double salary,
                   LocalDate hireDate,
                   TeacherTitle title) {
        super(id, passwordHash, firstName, lastName, email, salary, hireDate);
        this.title = title;
        if (title == TeacherTitle.PROFESSOR) {
            becomeResearcher();
        }
    }

    public void putMark(Student student, Course course, Mark mark) {
        if (!courses.contains(course)) {
            throw new IllegalStateException("Teacher is not assigned to this course.");
        }
        if (!course.getStudents().contains(student)) {
            throw new IllegalStateException("Student is not registered for this course.");
        }
        mark.calculateTotal();
        student.updateMark(course, mark);
    }

    public List<Student> viewStudents(Course course) {
        if (!courses.contains(course)) {
            throw new IllegalStateException("Teacher is not assigned to this course.");
        }
        return course.getStudents();
    }

    public void sendComplaint(Manager manager, String complaint) {
        Message message = new Message(this, manager, "Complaint: " + complaint, LocalDateTime.now());
        sendMessage(manager, message);
    }

    public void becomeResearcher() {
        if (researcherRole == null) {
            researcherRole = new DefaultResearcher(this);
        }
    }

    public boolean isResearcher() {
        return researcherRole != null;
    }

    public void addCourse(Course course) {
        if (course != null && !courses.contains(course)) {
            courses.add(course);
        }
    }

    public void acceptRating(Student student, int rating) {
        ratings.put(student, rating);
    }

    public double getAverageRating() {
        return ratings.values().stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public TeacherTitle getTitle() {
        return title;
    }

    public void setTitle(TeacherTitle title) {
        this.title = title;
        if (title == TeacherTitle.PROFESSOR) {
            becomeResearcher();
        }
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public Researcher getResearcherRole() {
        return researcherRole;
    }

    @Override
    public String toString() {
        return super.toString() + ", title=" + title + ", avgRating=" + String.format("%.2f", getAverageRating());
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Teacher && super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
