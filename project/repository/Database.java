package repository;

import model.Course;
import model.EmployeeRequest;
import model.News;
import model.ResearchProject;
import model.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Database implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String FILE_NAME = "database.ser";
    private static Database INSTANCE = loadDatabase();

    private final Map<String, User> users = new LinkedHashMap<>();
    private final Map<String, Course> courses = new LinkedHashMap<>();
    private final List<ResearchProject> researchProjects = new ArrayList<>();
    private final List<News> newsFeed = new ArrayList<>();
    private final List<EmployeeRequest> employeeRequests = new ArrayList<>();

    private Database() {
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public synchronized void addUser(User user) {
        users.put(user.getId(), user);
    }

    public synchronized void removeUser(String userId) {
        users.remove(userId);
    }

    public synchronized void updateUser(User user) {
        users.put(user.getId(), user);
    }

    public synchronized List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public synchronized User getUser(String userId) {
        return users.get(userId);
    }

    public synchronized void addCourse(Course course) {
        courses.put(course.getCode(), course);
    }

    public synchronized List<Course> getCourses() {
        return new ArrayList<>(courses.values());
    }

    public synchronized Course getCourse(String courseCode) {
        return courses.get(courseCode);
    }

    public synchronized void addResearchProject(ResearchProject project) {
        if (!researchProjects.contains(project)) {
            researchProjects.add(project);
        }
    }

    public synchronized List<ResearchProject> getResearchProjects() {
        return new ArrayList<>(researchProjects);
    }

    public synchronized void addNews(News news) {
        if (!newsFeed.contains(news)) {
            newsFeed.add(news);
        }
    }

    public synchronized List<News> getNewsFeed() {
        return new ArrayList<>(newsFeed);
    }

    public synchronized void addEmployeeRequest(EmployeeRequest request) {
        if (!employeeRequests.contains(request)) {
            employeeRequests.add(request);
        }
    }

    public synchronized List<EmployeeRequest> getEmployeeRequests() {
        return new ArrayList<>(employeeRequests);
    }

    public synchronized void reset() {
        users.clear();
        courses.clear();
        researchProjects.clear();
        newsFeed.clear();
        employeeRequests.clear();
        save();
    }

    public synchronized void save() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            outputStream.writeObject(this);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to save database.", e);
        }
    }

    private static Database loadDatabase() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object object = inputStream.readObject();
            if (object instanceof Database database) {
                return database;
            }
        } catch (IOException | ClassNotFoundException ignored) {
            return new Database();
        }
        return new Database();
    }

    @Serial
    private Object readResolve() {
        INSTANCE = this;
        return INSTANCE;
    }
}
