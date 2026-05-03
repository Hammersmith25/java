package model;

import enums.LessonType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Lesson implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private LessonType type;

    public Lesson(LessonType type) {
        this.type = type;
    }

    public LessonType getType() {
        return type;
    }

    public void setType(LessonType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lesson lesson)) {
            return false;
        }
        return type == lesson.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
