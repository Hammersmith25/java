package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class Transcript implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<Course, Mark> records = new LinkedHashMap<>();

    public Map<Course, Mark> getRecords() {
        return new LinkedHashMap<>(records);
    }

    public void addCourse(Course course) {
        records.putIfAbsent(course, new Mark());
    }

    public void updateRecord(Course course, Mark mark) {
        records.put(course, mark);
    }

    public Mark getMark(Course course) {
        return records.get(course);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        records.forEach((course, mark) -> joiner.add(course.getCode() + " - " + course.getName() + ": " + mark));
        return joiner.length() == 0 ? "Transcript is empty." : joiner.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transcript that)) {
            return false;
        }
        return Objects.equals(records, that.records);
    }

    @Override
    public int hashCode() {
        return Objects.hash(records);
    }
}
