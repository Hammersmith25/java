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
import model.EmployeeRequest;
import model.Lesson;
import model.Manager;
import model.Mark;
import model.News;
import model.Report;
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
import service.PaperByTitleComparator;
import service.ResearchService;
import service.StudentByGpaComparator;
import service.TeacherByNameComparator;
import service.UserFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        database.reset();

        UserFactory userFactory = new UserFactory();
        AuthenticationService authenticationService = new AuthenticationService(database);
        ResearchService researchService = new ResearchService();

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

        authenticationService.requireAuthenticated("A001", "admin_hash");

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
        manager.addCourseForRegistration(oop, null, null);

        try {
            seniorStudent.registerForCourse(oop);
            seniorStudent.registerForCourse(researchMethods);
            seniorStudent.registerForCourse(distributedSystems);
            seniorStudent.registerForCourse(machineLearning);
        } catch (CreditLimitExceededException e) {
            System.out.println("Registration error: " + e.getMessage());
        }

        try {
            juniorStudent.registerForCourse(oop);
        } catch (CreditLimitExceededException e) {
            System.out.println("Registration error: " + e.getMessage());
        }

        manager.approveRegistration(seniorStudent);
        manager.approveRegistration(juniorStudent);

        professor.putMark(seniorStudent, oop, new Mark(27, 28, 35));
        professor.putMark(juniorStudent, oop, new Mark(23, 24, 31));

        seniorStudent.rateTeacher(professor, 5);
        juniorStudent.rateTeacher(professor, 4);

        lecturer.sendComplaint(manager, "Need an additional teaching assistant for CS420.");

        seniorStudent.becomeResearcher();

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

        ResearchPaper studentPaper1 = new ResearchPaper(
                "Student-Centric Research Tracking",
                List.of("Sara Senior", "Nora Professor"),
                "Campus Systems Journal",
                3,
                10,
                "10.1000/srt-005",
                LocalDate.of(2024, 11, 1)
        );
        ResearchPaper studentPaper2 = new ResearchPaper(
                "Console Tools for Academic Workflow",
                List.of("Sara Senior"),
                "Software in Education",
                2,
                8,
                "10.1000/ctaw-006",
                LocalDate.of(2025, 3, 18)
        );

        researchService.addPaper(professor.getResearcherRole(), professorPaper1);
        researchService.addPaper(professor.getResearcherRole(), professorPaper2);
        researchService.addPaper(professor.getResearcherRole(), professorPaper3);
        researchService.addPaper(lecturer.getResearcherRole(), lecturerPaper);
        researchService.addPaper(seniorStudent.getResearcherRole(), studentPaper1);
        researchService.addPaper(seniorStudent.getResearcherRole(), studentPaper2);

        System.out.println("\nProfessor papers sorted by title:");
        professor.getResearcherRole().printPapers(new PaperByTitleComparator());

        System.out.println("\nProfessor papers sorted by citations:");
        professor.getResearcherRole().printPapers(new PaperByCitationsComparator());

        System.out.println("\nProfessor papers sorted by article length:");
        professor.getResearcherRole().printPapers(new PaperByPagesComparator());

        System.out.println("\nAll researchers' papers sorted by publish date:");
        researchService.printAllResearchersPapers(database.getUsers(), new PaperByDateComparator());

        ResearchProject aiProject = new ResearchProject("AI for Research Administration");
        aiProject.addPaper(studentPaper1);
        database.addResearchProject(aiProject);

        try {
            aiProject.addParticipant(professor);
            aiProject.addParticipant(seniorStudent);
            aiProject.addParticipant(juniorStudent);
        } catch (NotAResearcherException e) {
            System.out.println("Project participation error: " + e.getMessage());
        }

        try {
            seniorStudent.assignSupervisor(lecturer.getResearcherRole());
        } catch (LowHIndexException e) {
            System.out.println("Supervisor assignment error: " + e.getMessage());
        }

        try {
            juniorStudent.assignSupervisor(professor.getResearcherRole());
        } catch (LowHIndexException | IllegalStateException e) {
            System.out.println("Supervisor assignment error: " + e.getMessage());
        }

        try {
            seniorStudent.assignSupervisor(professor.getResearcherRole());
            System.out.println("Supervisor assigned successfully to " + seniorStudent.getFullName() + ".");
        } catch (LowHIndexException e) {
            System.out.println("Supervisor assignment error: " + e.getMessage());
        }

        News news = new News(
                "Research Grant Call",
                "The university opens a new grant call for interdisciplinary projects.",
                LocalDate.now()
        );
        manager.manageNews(news);

        Report report = manager.createReport();

        System.out.println("\nStudents taught by professor in " + oop.getCode() + ":");
        professor.viewStudents(oop).forEach(System.out::println);

        System.out.println("\nSenior student marks:");
        seniorStudent.viewMarks().forEach(System.out::println);

        System.out.println("\nSenior student transcript:");
        System.out.println(seniorStudent.viewTranscript());

        System.out.println("\nTop cited researcher:");
        researchService.findTopCitedResearcher(database.getUsers())
                .map(Researcher::getResearcherName)
                .ifPresent(System.out::println);

        System.out.println("\nTop cited SITE researcher in 2024:");
        researchService.findTopCitedResearcherBySchoolOfYear(database.getUsers(), School.SITE, 2024)
                .map(Researcher::getResearcherName)
                .ifPresent(System.out::println);

        EmployeeRequest request = lecturer.createRequest("Approve conference travel budget.");
        request.signByDean(manager);
        request.signByRector(admin);

        System.out.println("\nManager messages:");
        manager.getMessages().forEach(System.out::println);

        System.out.println("\nStudents sorted by GPA:");
        manager.viewStudentsSorted(new StudentByGpaComparator()).forEach(System.out::println);

        System.out.println("\nTeachers sorted alphabetically:");
        manager.viewTeachersSorted(new TeacherByNameComparator()).forEach(System.out::println);

        System.out.println("\nSigned employee requests:");
        manager.viewSignedEmployeeRequests().forEach(System.out::println);

        System.out.println("\nStudent notifications:");
        seniorStudent.getNotifications().forEach(System.out::println);

        System.out.println("\nGenerated report:");
        System.out.println(report);

        System.out.println("Admin logs:");
        admin.viewLogs().forEach(System.out::println);

        database.save();
    }
}
