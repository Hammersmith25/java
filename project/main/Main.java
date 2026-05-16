package main;

import enums.LessonType;
import enums.ManagerType;
import enums.School;
import enums.TeacherTitle;
import exceptions.CreditLimitExceededException;
import exceptions.LowHIndexException;
import exceptions.NotAResearcherException;
import model.Admin;
import model.Course;
import model.Employee;
import model.EmployeeRequest;
import model.Lesson;
import model.Manager;
import model.Mark;
import model.News;
import model.ResearchPaper;
import model.ResearchProject;
import model.Researcher;
import model.Student;
import model.Teacher;
import model.User;
import repository.Database;
import service.AuthenticationService;
import service.PaperByCitationsComparator;
import service.PaperByDateComparator;
import service.PaperByPagesComparator;
import service.ResearchService;
import service.StudentByGpaComparator;
import service.TeacherByNameComparator;
import service.UserFactory;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private final Database database = Database.getInstance();
    private final UserFactory userFactory = new UserFactory();
    private final AuthenticationService authenticationService = new AuthenticationService(database);
    private final ResearchService researchService = new ResearchService();
    private User currentUser;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        seedData();
        printWelcome();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                if (!scanner.hasNextLine()) {
                    break;
                }

                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    continue;
                }
                if (input.equalsIgnoreCase("exit")) {
                    database.save();
                    System.out.println("Saved. Bye.");
                    break;
                }

                handle(input);
            }
        }
    }

    private void printWelcome() {
        System.out.println("University console");
        System.out.println("Type: help");
        System.out.println("Demo logins: login A001 admin_hash | login M001 manager_hash | login T001 prof_hash | login S001 stud_hash");
    }

    private void handle(String input) {
        String[] parts = input.split("\\s+");
        String command = parts[0].toLowerCase();

        try {
            if (command.equals("help")) {
                printHelp();
                return;
            }
            if (command.equals("login")) {
                login(parts);
                return;
            }

            requireLogin();

            switch (command) {
                case "whoami" -> System.out.println(currentUser);
                case "logout" -> logout();
                case "users" -> listUsers();
                case "courses" -> listCourses();
                case "students" -> listStudents();
                case "teachers" -> listTeachers();
                case "register" -> register(parts);
                case "approve" -> approve(parts);
                case "assign" -> assign(parts);
                case "mark" -> mark(parts);
                case "transcript" -> transcript(parts);
                case "rate" -> rate(parts);
                case "papers" -> papers(parts);
                case "top" -> topResearcher();
                case "top-year" -> topResearcherByYear(parts);
                case "top-school-year" -> topResearcherBySchoolYear(parts);
                case "project" -> createProject(input);
                case "join" -> joinProject(parts);
                case "supervisor" -> assignSupervisor(parts);
                case "report" -> report();
                case "news" -> news(input);
                case "request" -> createRequest(input);
                case "sign-dean" -> signDean(parts);
                case "sign-rector" -> signRector(parts);
                case "requests" -> listRequests();
                case "save" -> save();
                default -> System.out.println("Unknown command. Type: help");
            }
        } catch (RuntimeException | CreditLimitExceededException | LowHIndexException | NotAResearcherException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void printHelp() {
        if (currentUser == null) {
            System.out.println("""
                    Available commands:
                    help
                    login <id> <password>
                    exit
                    """);
            return;
        }

        System.out.println("Available commands for " + currentUser.getClass().getSimpleName() + ":");
        System.out.println("""
                help
                whoami | logout | save | exit
                courses
                papers date|citations|pages
                top | top-year <year> | top-school-year <SCHOOL> <year>
                project <topic>
                join <projectNumber> <userId>
                """);

        if (currentUser instanceof Admin) {
            System.out.println("""
                    users
                    requests
                    sign-rector <requestNumber>
                    """);
        }

        if (currentUser instanceof Manager) {
            System.out.println("""
                    users | students | teachers
                    approve <studentId>
                    assign <teacherId> <courseCode>
                    report
                    news <title and text>
                    requests
                    sign-dean <requestNumber>
                    request <employeeId> <text>
                    """);
        }

        if (currentUser instanceof Teacher) {
            System.out.println("""
                    students
                    mark <studentId> <courseCode> <att1> <att2> <final>
                    transcript <studentId>
                    supervisor <studentId> <teacherId>
                    request <text>
                    """);
        }

        if (currentUser instanceof Student) {
            System.out.println("""
                    register <courseCode>
                    transcript
                    rate <teacherId> <1-5>
                    supervisor <studentId> <teacherId>
                    """);
        }

        if (currentUser instanceof Employee
                && !(currentUser instanceof Admin)
                && !(currentUser instanceof Teacher)
                && !(currentUser instanceof Manager)) {
            System.out.println("request <text>");
        }
    }

    private void login(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Usage: login <id> <password>");
            return;
        }
        Optional<User> user = authenticationService.authenticate(parts[1], parts[2]);
        if (user.isPresent()) {
            currentUser = user.get();
            System.out.println("Current user: " + currentUser.getFullName());
        } else {
            System.out.println("Wrong id or password.");
        }
    }

    private void logout() {
        currentUser.logout();
        currentUser = null;
    }

    private void requireLogin() {
        if (currentUser == null) {
            throw new SecurityException("Please login first.");
        }
    }

    private void requireManager() {
        if (!(currentUser instanceof Manager)) {
            throw new SecurityException("Only a manager can do this.");
        }
    }

    private void requireAdmin() {
        if (!(currentUser instanceof Admin)) {
            throw new SecurityException("Only an admin can do this.");
        }
    }

    private void listUsers() {
        database.getUsers().forEach(System.out::println);
    }

    private void listCourses() {
        database.getCourses().forEach(course -> System.out.println(course + ", instructors=" + course.getInstructors().size()));
    }

    private void listStudents() {
        Manager manager = managerForSorting();
        manager.viewStudentsSorted(new StudentByGpaComparator()).forEach(System.out::println);
    }

    private void listTeachers() {
        Manager manager = managerForSorting();
        manager.viewTeachersSorted(new TeacherByNameComparator()).forEach(System.out::println);
    }

    private Manager managerForSorting() {
        return database.getUsers().stream()
                .filter(Manager.class::isInstance)
                .map(Manager.class::cast)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No manager found."));
    }

    private void register(String[] parts) throws CreditLimitExceededException {
        if (currentUser instanceof Student student && parts.length == 2) {
            Course course = getCourse(parts[1]);
            student.registerForCourse(course);
            System.out.println(student.getFullName() + " requested " + course.getCode());
            return;
        }
        if (parts.length < 3) {
            System.out.println("Usage: register <courseCode> or register <studentId> <courseCode>");
            return;
        }
        Student student = getStudent(parts[1]);
        Course course = getCourse(parts[2]);
        student.registerForCourse(course);
        System.out.println(student.getFullName() + " requested " + course.getCode());
    }

    private void approve(String[] parts) {
        requireManager();
        if (parts.length < 2) {
            System.out.println("Usage: approve <studentId>");
            return;
        }
        ((Manager) currentUser).approveRegistration(getStudent(parts[1]));
        System.out.println("Approved pending courses.");
    }

    private void assign(String[] parts) {
        requireManager();
        if (parts.length < 3) {
            System.out.println("Usage: assign <teacherId> <courseCode>");
            return;
        }
        ((Manager) currentUser).assignCourse(getTeacher(parts[1]), getCourse(parts[2]));
        System.out.println("Teacher assigned.");
    }

    private void mark(String[] parts) {
        if (!(currentUser instanceof Teacher teacher)) {
            throw new SecurityException("Only a teacher can put marks.");
        }
        if (parts.length < 6) {
            System.out.println("Usage: mark <studentId> <courseCode> <att1> <att2> <final>");
            return;
        }
        Student student = getStudent(parts[1]);
        Course course = getCourse(parts[2]);
        double attestation1 = Double.parseDouble(parts[3]);
        double attestation2 = Double.parseDouble(parts[4]);
        double finalExam = Double.parseDouble(parts[5]);
        teacher.putMark(student, course, new Mark(attestation1, attestation2, finalExam));
        System.out.println("Mark saved.");
    }

    private void transcript(String[] parts) {
        if (currentUser instanceof Student student && parts.length == 1) {
            System.out.println(student.viewTranscript());
            return;
        }
        if (parts.length < 2) {
            System.out.println("Usage: transcript or transcript <studentId>");
            return;
        }
        System.out.println(getStudent(parts[1]).viewTranscript());
    }

    private void rate(String[] parts) {
        if (!(currentUser instanceof Student student)) {
            throw new SecurityException("Only a student can rate a teacher.");
        }
        if (parts.length < 3) {
            System.out.println("Usage: rate <teacherId> <1-5>");
            return;
        }
        student.rateTeacher(getTeacher(parts[1]), Integer.parseInt(parts[2]));
        System.out.println("Rating saved.");
    }

    private void papers(String[] parts) {
        Comparator<ResearchPaper> comparator = new PaperByDateComparator();
        if (parts.length > 1 && parts[1].equalsIgnoreCase("citations")) {
            comparator = new PaperByCitationsComparator();
        } else if (parts.length > 1 && parts[1].equalsIgnoreCase("pages")) {
            comparator = new PaperByPagesComparator();
        }
        researchService.printAllResearchersPapers(database.getUsers(), comparator);
    }

    private void topResearcher() {
        researchService.findTopCitedResearcher(database.getUsers())
                .map(Researcher::getResearcherName)
                .ifPresentOrElse(System.out::println, () -> System.out.println("No researchers found."));
    }

    private void topResearcherByYear(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Usage: top-year <year>");
            return;
        }
        int year = Integer.parseInt(parts[1]);
        researchService.findTopCitedResearcherOfYear(database.getUsers(), year)
                .map(Researcher::getResearcherName)
                .ifPresentOrElse(System.out::println, () -> System.out.println("No researcher found for that year."));
    }

    private void topResearcherBySchoolYear(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Usage: top-school-year <SCHOOL> <year>");
            return;
        }
        School school = School.valueOf(parts[1].toUpperCase());
        int year = Integer.parseInt(parts[2]);
        researchService.findTopCitedResearcherBySchoolOfYear(database.getUsers(), school, year)
                .map(Researcher::getResearcherName)
                .ifPresentOrElse(System.out::println, () -> System.out.println("No researcher found."));
    }

    private void createProject(String input) {
        String topic = textAfterCommand(input);
        if (topic.isBlank()) {
            System.out.println("Usage: project <topic>");
            return;
        }
        ResearchProject project = new ResearchProject(topic);
        database.addResearchProject(project);
        System.out.println("Project #" + database.getResearchProjects().size() + " created.");
    }

    private void joinProject(String[] parts) throws NotAResearcherException {
        if (parts.length < 3) {
            System.out.println("Usage: join <projectNumber> <userId>");
            return;
        }
        ResearchProject project = getProject(Integer.parseInt(parts[1]));
        project.addParticipant(getUser(parts[2]));
        System.out.println("Participant added.");
    }

    private void assignSupervisor(String[] parts) throws LowHIndexException {
        if (parts.length < 3) {
            System.out.println("Usage: supervisor <studentId> <teacherId>");
            return;
        }
        Student student = getStudent(parts[1]);
        Teacher teacher = getTeacher(parts[2]);
        student.assignSupervisor(teacher.getResearcherRole());
        System.out.println("Supervisor assigned.");
    }

    private void report() {
        Manager manager = managerForSorting();
        System.out.println(manager.createReport());
    }

    private void news(String input) {
        requireManager();
        String content = textAfterCommand(input);
        if (content.isBlank()) {
            System.out.println("Usage: news <title and text>");
            return;
        }
        ((Manager) currentUser).manageNews(new News(content, content, LocalDate.now()));
        System.out.println("News published.");
    }

    private void createRequest(String input) {
        String[] parts = input.split("\\s+", 3);
        if (currentUser instanceof Employee employee && !(currentUser instanceof Manager) && !(currentUser instanceof Admin)) {
            String content = textAfterCommand(input);
            if (content.isBlank()) {
                System.out.println("Usage: request <text>");
                return;
            }
            employee.createRequest(content);
            System.out.println("Request #" + database.getEmployeeRequests().size() + " created.");
            return;
        }
        if (parts.length < 3) {
            System.out.println("Usage: request <employeeId> <text>");
            return;
        }
        User user = getUser(parts[1]);
        if (!(user instanceof Employee employee)) {
            throw new IllegalArgumentException("User is not an employee.");
        }
        employee.createRequest(parts[2]);
        System.out.println("Request #" + database.getEmployeeRequests().size() + " created.");
    }

    private void signDean(String[] parts) {
        requireManager();
        if (parts.length < 2) {
            System.out.println("Usage: sign-dean <requestNumber>");
            return;
        }
        getRequest(Integer.parseInt(parts[1])).signByDean(currentUser);
        System.out.println("Signed by dean/manager.");
    }

    private void signRector(String[] parts) {
        requireAdmin();
        if (parts.length < 2) {
            System.out.println("Usage: sign-rector <requestNumber>");
            return;
        }
        getRequest(Integer.parseInt(parts[1])).signByRector(currentUser);
        System.out.println("Signed by rector/admin.");
    }

    private void listRequests() {
        List<EmployeeRequest> requests = database.getEmployeeRequests();
        for (int i = 0; i < requests.size(); i++) {
            System.out.println("#" + (i + 1) + " " + requests.get(i));
        }
    }

    private void save() {
        database.save();
        System.out.println("Saved.");
    }

    private String textAfterCommand(String input) {
        int firstSpace = input.indexOf(' ');
        return firstSpace < 0 ? "" : input.substring(firstSpace + 1).trim();
    }

    private User getUser(String id) {
        User user = database.getUser(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + id);
        }
        return user;
    }

    private Student getStudent(String id) {
        User user = getUser(id);
        if (!(user instanceof Student student)) {
            throw new IllegalArgumentException("User is not a student: " + id);
        }
        return student;
    }

    private Teacher getTeacher(String id) {
        User user = getUser(id);
        if (!(user instanceof Teacher teacher)) {
            throw new IllegalArgumentException("User is not a teacher: " + id);
        }
        return teacher;
    }

    private Course getCourse(String code) {
        Course course = database.getCourse(code);
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + code);
        }
        return course;
    }

    private ResearchProject getProject(int number) {
        List<ResearchProject> projects = database.getResearchProjects();
        if (number < 1 || number > projects.size()) {
            throw new IllegalArgumentException("Project number not found.");
        }
        return projects.get(number - 1);
    }

    private EmployeeRequest getRequest(int number) {
        List<EmployeeRequest> requests = database.getEmployeeRequests();
        if (number < 1 || number > requests.size()) {
            throw new IllegalArgumentException("Request number not found.");
        }
        return requests.get(number - 1);
    }

    private void seedData() {
        database.reset();

        Admin admin = (Admin) userFactory.createUser("ADMIN", Map.of(
                "id", "A001",
                "passwordHash", "admin_hash",
                "firstName", "Alice",
                "lastName", "Admin",
                "email", "alice.admin@university.edu",
                "school", School.GENERAL,
                "salary", 350000.0,
                "hireDate", LocalDate.of(2020, 1, 10)
        ));

        Manager manager = (Manager) userFactory.createUser("MANAGER", Map.of(
                "id", "M001",
                "passwordHash", "manager_hash",
                "firstName", "Mark",
                "lastName", "Manager",
                "email", "mark.manager@university.edu",
                "school", School.SITE,
                "salary", 300000.0,
                "hireDate", LocalDate.of(2021, 2, 15),
                "type", ManagerType.OR
        ));

        Teacher professor = (Teacher) userFactory.createUser("TEACHER", Map.of(
                "id", "T001",
                "passwordHash", "prof_hash",
                "firstName", "Nora",
                "lastName", "Professor",
                "email", "nora.professor@university.edu",
                "school", School.SITE,
                "salary", 420000.0,
                "hireDate", LocalDate.of(2018, 9, 1),
                "title", TeacherTitle.PROFESSOR
        ));

        Teacher lecturer = (Teacher) userFactory.createUser("TEACHER", Map.of(
                "id", "T002",
                "passwordHash", "lect_hash",
                "firstName", "Leo",
                "lastName", "Lecturer",
                "email", "leo.lecturer@university.edu",
                "school", School.SITE,
                "salary", 260000.0,
                "hireDate", LocalDate.of(2022, 3, 1),
                "title", TeacherTitle.LECTOR
        ));
        lecturer.becomeResearcher();

        Student seniorStudent = (Student) userFactory.createUser("STUDENT", Map.of(
                "id", "S001",
                "passwordHash", "stud_hash",
                "firstName", "Sara",
                "lastName", "Senior",
                "email", "sara.senior@university.edu",
                "school", School.SITE,
                "major", "Computer Science",
                "year", 4,
                "credits", 0,
                "gpa", 3.2
        ));
        seniorStudent.becomeResearcher();

        Student juniorStudent = (Student) userFactory.createUser("STUDENT", Map.of(
                "id", "S002",
                "passwordHash", "junior_hash",
                "firstName", "Jules",
                "lastName", "Junior",
                "email", "jules.junior@university.edu",
                "school", School.SITE,
                "major", "Data Science",
                "year", 3,
                "credits", 0,
                "gpa", 3.0
        ));

        admin.addUser(admin);
        admin.addUser(manager);
        admin.addUser(professor);
        admin.addUser(lecturer);
        admin.addUser(seniorStudent);
        admin.addUser(juniorStudent);

        Course oop = new Course("CS301", "Advanced OOP", 5);
        Course researchMethods = new Course("CS410", "Research Methods", 4);
        Course distributedSystems = new Course("CS420", "Distributed Systems", 6);
        Course machineLearning = new Course("CS430", "Machine Learning", 7);
        oop.addLesson(new Lesson(LessonType.LECTURE));
        oop.addLesson(new Lesson(LessonType.PRACTICE));

        database.addCourse(oop);
        database.addCourse(researchMethods);
        database.addCourse(distributedSystems);
        database.addCourse(machineLearning);

        manager.assignCourse(professor, oop);
        manager.assignCourse(lecturer, oop);
        manager.assignCourse(professor, researchMethods);
        manager.assignCourse(lecturer, distributedSystems);

        try {
            seniorStudent.registerForCourse(oop);
            seniorStudent.registerForCourse(researchMethods);
            juniorStudent.registerForCourse(oop);
        } catch (CreditLimitExceededException e) {
            throw new IllegalStateException(e);
        }
        manager.approveRegistration(seniorStudent);
        manager.approveRegistration(juniorStudent);

        professor.putMark(seniorStudent, oop, new Mark(27, 28, 35));
        professor.putMark(juniorStudent, oop, new Mark(23, 24, 31));

        ResearchPaper professorPaper1 = new ResearchPaper(
                "Scalable Knowledge Graphs",
                List.of("Nora Professor"),
                "Journal of Distributed Intelligence",
                12,
                18,
                "10.1000/kg-001",
                LocalDate.of(2021, 5, 12)
        );
        ResearchPaper professorPaper2 = new ResearchPaper(
                "Adaptive Query Scheduling",
                List.of("Nora Professor"),
                "Systems Review",
                9,
                15,
                "10.1000/aqs-002",
                LocalDate.of(2022, 7, 5)
        );
        ResearchPaper professorPaper3 = new ResearchPaper(
                "Edge-Aware Research Platforms",
                List.of("Nora Professor"),
                "Computing Frontiers",
                7,
                20,
                "10.1000/earp-003",
                LocalDate.of(2023, 10, 10)
        );
        ResearchPaper lecturerPaper = new ResearchPaper(
                "Introductory Methods for Classroom Analytics",
                List.of("Leo Lecturer"),
                "Teaching Informatics",
                1,
                12,
                "10.1000/ti-004",
                LocalDate.of(2024, 2, 2)
        );
        ResearchPaper studentPaper = new ResearchPaper(
                "Student-Centric Research Tracking",
                List.of("Sara Senior", "Nora Professor"),
                "Campus Systems Journal",
                3,
                10,
                "10.1000/srt-005",
                LocalDate.of(2024, 11, 1)
        );

        researchService.addPaper(professor.getResearcherRole(), professorPaper1);
        researchService.addPaper(professor.getResearcherRole(), professorPaper2);
        researchService.addPaper(professor.getResearcherRole(), professorPaper3);
        researchService.addPaper(lecturer.getResearcherRole(), lecturerPaper);
        researchService.addPaper(seniorStudent.getResearcherRole(), studentPaper);

        ResearchProject project = new ResearchProject("AI for Research Administration");
        project.addPaper(studentPaper);
        try {
            project.addParticipant(professor);
            project.addParticipant(seniorStudent);
        } catch (NotAResearcherException e) {
            throw new IllegalStateException(e);
        }
        database.addResearchProject(project);
    }
}
