package model;

import enums.ManagerType;
import repository.Database;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Comparator;
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

    public void addCourseForRegistration(Course course, String intendedMajor, Integer intendedYear) {
        course.setIntendedMajor(intendedMajor);
        course.setIntendedYear(intendedYear);
        Database.getInstance().addCourse(course);
    }

    public Report createReport() {
        Database database = Database.getInstance();
        List<Mark> marks = database.getCourses().stream()
                .flatMap(course -> course.getStudents().stream()
                        .map(student -> student.getTranscript().getMark(course))
                        .filter(mark -> mark != null && (mark.getTotal() > 0)))
                .toList();
        double average = marks.stream().mapToDouble(Mark::getTotal).average().orElse(0.0);
        long failed = marks.stream().filter(Mark::isFailed).count();
        String content = """
                Academic performance report
                Users: %d
                Courses: %d
                Projects: %d
                News count: %d
                Graded records: %d
                Average total mark: %.2f
                Failed records: %d
                """.formatted(
                database.getUsers().size(),
                database.getCourses().size(),
                database.getResearchProjects().size(),
                database.getNewsFeed().size(),
                marks.size(),
                average,
                failed
        );
        return new Report(content);
    }

    public List<Student> viewStudentsSorted(Comparator<Student> comparator) {
        return Database.getInstance().getUsers().stream()
                .filter(Student.class::isInstance)
                .map(Student.class::cast)
                .sorted(comparator)
                .toList();
    }

    public List<Teacher> viewTeachersSorted(Comparator<Teacher> comparator) {
        return Database.getInstance().getUsers().stream()
                .filter(Teacher.class::isInstance)
                .map(Teacher.class::cast)
                .sorted(comparator)
                .toList();
    }

    public List<EmployeeRequest> viewSignedEmployeeRequests() {
        return Database.getInstance().getEmployeeRequests().stream()
                .filter(EmployeeRequest::isFullySigned)
                .toList();
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
