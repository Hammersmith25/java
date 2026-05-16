package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String code;
    private String name;
    private int credits;
    private String intendedMajor;
    private Integer intendedYear;
    private final List<Teacher> instructors = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();
    private final List<Lesson> lessons = new ArrayList<>();

    public Course(String code, String name, int credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public Course(String code, String name, int credits, String intendedMajor, Integer intendedYear) {
        this(code, name, credits);
        this.intendedMajor = intendedMajor;
        this.intendedYear = intendedYear;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getIntendedMajor() {
        return intendedMajor;
    }

    public void setIntendedMajor(String intendedMajor) {
        this.intendedMajor = intendedMajor;
    }

    public Integer getIntendedYear() {
        return intendedYear;
    }

    public void setIntendedYear(Integer intendedYear) {
        this.intendedYear = intendedYear;
    }

    public List<Teacher> getInstructors() {
        return new ArrayList<>(instructors);
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public List<Lesson> getLessons() {
        return new ArrayList<>(lessons);
    }

    public void addInstructor(Teacher teacher) {
        if (teacher != null && !instructors.contains(teacher)) {
            instructors.add(teacher);
        }
    }

    public void addStudent(Student student) {
        if (student != null && !students.contains(student)) {
            students.add(student);
        }
    }

    public void addLesson(Lesson lesson) {
        if (lesson != null) {
            lessons.add(lesson);
        }
    }

    public boolean isAvailableFor(Student student) {
        boolean majorMatches = intendedMajor == null || intendedMajor.equalsIgnoreCase(student.getMajor());
        boolean yearMatches = intendedYear == null || intendedYear == student.getYear();
        return majorMatches && yearMatches;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", intendedMajor='" + intendedMajor + '\'' +
                ", intendedYear=" + intendedYear +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course course)) {
            return false;
        }
        return Objects.equals(code, course.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
