package model;

import enums.RequestStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class EmployeeRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Employee requester;
    private String content;
    private final LocalDateTime createdAt;
    private RequestStatus status;
    private String deanSignature;
    private String rectorSignature;

    public EmployeeRequest(Employee requester, String content) {
        this.requester = requester;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.status = RequestStatus.DRAFT;
    }

    public void signByDean(User dean) {
        deanSignature = dean.getFullName();
        status = RequestStatus.SIGNED_BY_DEAN;
    }

    public void signByRector(User rector) {
        rectorSignature = rector.getFullName();
        status = RequestStatus.SIGNED_BY_RECTOR;
    }

    public boolean isFullySigned() {
        return deanSignature != null && rectorSignature != null;
    }

    public Employee getRequester() {
        return requester;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getDeanSignature() {
        return deanSignature;
    }

    public String getRectorSignature() {
        return rectorSignature;
    }

    @Override
    public String toString() {
        return "EmployeeRequest{" +
                "requester=" + requester.getFullName() +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", deanSignature='" + deanSignature + '\'' +
                ", rectorSignature='" + rectorSignature + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeRequest that)) {
            return false;
        }
        return Objects.equals(requester, that.requester)
                && Objects.equals(content, that.content)
                && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requester, content, createdAt);
    }
}
