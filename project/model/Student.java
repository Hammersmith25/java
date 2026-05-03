package model;

import exceptions.CreditLimitExceededException;
import exceptions.LowHIndexException;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final int MAX_CREDITS = 21;

    private String major;
    private int year;
    private int credits;
    private double gpa;
    private Transcript transcript;
    private Researcher researcherRole;
    private Researcher supervisor;
    private final List<Course> registeredCourses = new ArrayList<>();
    private final List<Course> pendingCourses = new ArrayList<>();

    public Student(String id,
                   String passwordHash,
                   String firstName,
                   String lastName,
                   String email,
                   String major,
                   int year,
                   int credits,
                   double gpa) {
        super(id, passwordHash, firstName, lastName, email);
        this.major = major;
        this.year = year;
        this.credits = credits;
        this.gpa = gpa;
        this.transcript = new Transcript();
    }

    public void registerForCourse(Course course) throws CreditLimitExceededException {
        if (course == null || registeredCourses.contains(course) || pendingCourses.contains(course)) {
            return;
        }
        int plannedCredits = credits + pendingCourses.stream().mapToInt(Course::getCredits).sum() + course.getCredits();
        if (plannedCredits > MAX_CREDITS) {
            throw new CreditLimitExceededException("Credit limit exceeded. Maximum allowed credits is " + MAX_CREDITS + ".");
        }
        pendingCourses.add(course);
    }

    public void approveCourseRegistration(Course course) {
        if (course != null && pendingCourses.remove(course) && !registeredCourses.contains(course)) {
            registeredCourses.add(course);
            credits += course.getCredits();
            course.addStudent(this);
            transcript.addCourse(course);
        }
    }

    public List<Mark> viewMarks() {
        return transcript.getRecords().values().stream()
                .filter(this::isGraded)
                .toList();
    }

    public Transcript viewTranscript() {
        return transcript;
    }

    public void rateTeacher(Teacher teacher, int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        teacher.acceptRating(this, rating);
    }

    public void becomeResearcher() {
        if (researcherRole == null) {
            researcherRole = new DefaultResearcher(this);
        }
    }

    public boolean isResearcher() {
        return researcherRole != null;
    }

    public void assignSupervisor(Researcher supervisor) throws LowHIndexException {
        if (year != 4) {
            throw new IllegalStateException("Supervisor can be assigned only to 4th year students.");
        }
        if (supervisor == null || supervisor.calculateHIndex() < 3) {
            throw new LowHIndexException("Supervisor must have an h-index of at least 3.");
        }
        this.supervisor = supervisor;
    }

    public void updateMark(Course course, Mark mark) {
        transcript.updateRecord(course, mark);
        recalculateGpa();
    }

    private void recalculateGpa() {
        List<Mark> marks = viewMarks();
        if (marks.isEmpty()) {
            gpa = 0.0;
            return;
        }
        double averageTotal = marks.stream().mapToDouble(Mark::getTotal).average().orElse(0.0);
        gpa = Math.min(4.0, averageTotal / 25.0);
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public Researcher getResearcherRole() {
        return researcherRole;
    }

    public Researcher getSupervisor() {
        return supervisor;
    }

    public List<Course> getRegisteredCourses() {
        return new ArrayList<>(registeredCourses);
    }

    public List<Course> getPendingCourses() {
        return new ArrayList<>(pendingCourses);
    }

    private boolean isGraded(Mark mark) {
        return mark.getAttestation1() > 0 || mark.getAttestation2() > 0 || mark.getFinalExam() > 0;
    }

    @Override
    public String toString() {
        return super.toString() + ", major='" + major + '\'' + ", year=" + year + ", credits=" + credits + ", gpa=" + gpa;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Student && super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
