package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Report implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String content;

    public Report(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Report report)) {
            return false;
        }
        return Objects.equals(content, report.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
