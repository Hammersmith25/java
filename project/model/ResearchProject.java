package model;

import exceptions.NotAResearcherException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResearchProject implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String topic;
    private final List<ResearchPaper> papers = new ArrayList<>();
    private final List<Researcher> participants = new ArrayList<>();

    public ResearchProject(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<ResearchPaper> getPapers() {
        return new ArrayList<>(papers);
    }

    public List<Researcher> getParticipants() {
        return new ArrayList<>(participants);
    }

    public void addPaper(ResearchPaper paper) {
        if (paper != null && !papers.contains(paper)) {
            papers.add(paper);
        }
    }

    public void addParticipant(User user) throws NotAResearcherException {
        Researcher researcher = null;
        if (user instanceof Researcher directResearcher) {
            researcher = directResearcher;
        } else if (user instanceof Student student && student.isResearcher()) {
            researcher = student.getResearcherRole();
        } else if (user instanceof Teacher teacher && teacher.isResearcher()) {
            researcher = teacher.getResearcherRole();
        }

        if (researcher == null) {
            throw new NotAResearcherException(user.getFullName() + " is not a researcher.");
        }

        if (!participants.contains(researcher)) {
            participants.add(researcher);
            researcher.joinProject(this);
        }
    }

    @Override
    public String toString() {
        return "ResearchProject{" +
                "topic='" + topic + '\'' +
                ", papers=" + papers.size() +
                ", participants=" + participants.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResearchProject that)) {
            return false;
        }
        return Objects.equals(topic, that.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic);
    }
}
