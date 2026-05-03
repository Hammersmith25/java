package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class DefaultResearcher implements Researcher, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final User owner;
    private final List<ResearchPaper> papers = new ArrayList<>();
    private final List<ResearchProject> projects = new ArrayList<>();

    public DefaultResearcher(User owner) {
        this.owner = owner;
    }

    @Override
    public List<ResearchPaper> getPapers() {
        return new ArrayList<>(papers);
    }

    @Override
    public List<ResearchProject> getProjects() {
        return new ArrayList<>(projects);
    }

    @Override
    public void addPaper(ResearchPaper paper) {
        if (paper != null && !papers.contains(paper)) {
            papers.add(paper);
        }
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        papers.stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }

    @Override
    public int calculateHIndex() {
        List<Integer> citationCounts = papers.stream()
                .map(ResearchPaper::getCitations)
                .sorted(Comparator.reverseOrder())
                .toList();
        int hIndex = 0;
        for (int i = 0; i < citationCounts.size(); i++) {
            if (citationCounts.get(i) >= i + 1) {
                hIndex = i + 1;
            } else {
                break;
            }
        }
        return hIndex;
    }

    @Override
    public void joinProject(ResearchProject project) {
        if (project != null && !projects.contains(project)) {
            projects.add(project);
        }
    }

    @Override
    public String getResearcherName() {
        return owner.getFullName();
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "DefaultResearcher{" +
                "owner=" + owner.getFullName() +
                ", papers=" + papers.size() +
                ", projects=" + projects.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefaultResearcher that)) {
            return false;
        }
        return Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner);
    }
}
