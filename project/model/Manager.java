package model;

import enums.ManagerType;
import repository.Database;

import java.io.Serial;
import java.time.LocalDate;
import java.util.List;

public class Manager extends Employee {
    @Serial
    private static final long serialVersionUID = 1L;

    private ManagerType type;

    public Manager(String id,
                   String passwordHash,
                   String firstName,
                   String lastName,
                   String email,
                   double salary,
                   LocalDate hireDate,
                   ManagerType type) {
        super(id, passwordHash, firstName, lastName, email, salary, hireDate);
        this.type = type;
    }

    public void approveRegistration(Student student) {
        List<Course> requestedCourses = student.getPendingCourses();
        for (Course course : requestedCourses) {
            student.approveCourseRegistration(course);
        }
    }

    public void assignCourse(Teacher teacher, Course course) {
        teacher.addCourse(course);
        course.addInstructor(teacher);
    }

    public Report createReport() {
        Database database = Database.getInstance();
        String content = """
                University report
                Users: %d
                Courses: %d
                Projects: %d
                News count: %d
                """.formatted(
                database.getUsers().size(),
                database.getCourses().size(),
                database.getResearchProjects().size(),
                database.getNewsFeed().size()
        );
        return new Report(content);
    }

    public void manageNews(News news) {
        Database database = Database.getInstance();
        for (User user : database.getUsers()) {
            news.subscribe(user);
        }
        database.addNews(news);
        news.publish();
    }

    public ManagerType getType() {
        return type;
    }

    public void setType(ManagerType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + ", type=" + type;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Manager && super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
