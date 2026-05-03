package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class News implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String title;
    private String content;
    private LocalDate date;
    private transient List<Observer> observers = new ArrayList<>();

    public News(String title, String content, LocalDate date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void subscribe(Observer observer) {
        ensureObservers();
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void unsubscribe(Observer observer) {
        ensureObservers();
        observers.remove(observer);
    }

    public void notifyObservers() {
        ensureObservers();
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public void publish() {
        notifyObservers();
    }

    private void ensureObservers() {
        if (observers == null) {
            observers = new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof News news)) {
            return false;
        }
        return Objects.equals(title, news.title)
                && Objects.equals(content, news.content)
                && Objects.equals(date, news.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, date);
    }
}
