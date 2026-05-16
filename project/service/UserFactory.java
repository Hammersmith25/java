package service;

import enums.DegreeLevel;
import enums.ManagerType;
import enums.School;
import enums.TeacherTitle;
import model.Admin;
import model.Manager;
import model.ResearchEmployee;
import model.Student;
import model.Teacher;
import model.User;

import java.time.LocalDate;
import java.util.Map;

public class UserFactory {
    public User createUser(String type, Map<String, Object> attributes) {
        User user = switch (type.toUpperCase()) {
            case "STUDENT" -> new Student(
                    getString(attributes, "id"),
                    getString(attributes, "passwordHash"),
                    getString(attributes, "firstName"),
                    getString(attributes, "lastName"),
                    getString(attributes, "email"),
                    getString(attributes, "major"),
                    getInt(attributes, "year"),
                    getInt(attributes, "credits"),
                    getDouble(attributes, "gpa")
            );
            case "TEACHER" -> new Teacher(
                    getString(attributes, "id"),
                    getString(attributes, "passwordHash"),
                    getString(attributes, "firstName"),
                    getString(attributes, "lastName"),
                    getString(attributes, "email"),
                    getDouble(attributes, "salary"),
                    (LocalDate) attributes.get("hireDate"),
                    (TeacherTitle) attributes.get("title")
            );
            case "MANAGER" -> new Manager(
                    getString(attributes, "id"),
                    getString(attributes, "passwordHash"),
                    getString(attributes, "firstName"),
                    getString(attributes, "lastName"),
                    getString(attributes, "email"),
                    getDouble(attributes, "salary"),
                    (LocalDate) attributes.get("hireDate"),
                    (ManagerType) attributes.get("type")
            );
            case "ADMIN" -> new Admin(
                    getString(attributes, "id"),
                    getString(attributes, "passwordHash"),
                    getString(attributes, "firstName"),
                    getString(attributes, "lastName"),
                    getString(attributes, "email"),
                    getDouble(attributes, "salary"),
                    (LocalDate) attributes.get("hireDate")
            );
            case "RESEARCH_EMPLOYEE" -> new ResearchEmployee(
                    getString(attributes, "id"),
                    getString(attributes, "passwordHash"),
                    getString(attributes, "firstName"),
                    getString(attributes, "lastName"),
                    getString(attributes, "email"),
                    getDouble(attributes, "salary"),
                    (LocalDate) attributes.get("hireDate")
            );
            default -> throw new IllegalArgumentException("Unsupported user type: " + type);
        };
        if (attributes.containsKey("school")) {
            user.setSchool((School) attributes.get("school"));
        }
        if (user instanceof Student student && attributes.containsKey("degreeLevel")) {
            student.setDegreeLevel((DegreeLevel) attributes.get("degreeLevel"));
        }
        return user;
    }

    private String getString(Map<String, Object> attributes, String key) {
        return (String) attributes.get(key);
    }

    private int getInt(Map<String, Object> attributes, String key) {
        Object value = attributes.get(key);
        return value == null ? 0 : ((Number) value).intValue();
    }

    private double getDouble(Map<String, Object> attributes, String key) {
        Object value = attributes.get(key);
        return value == null ? 0.0 : ((Number) value).doubleValue();
    }
}
