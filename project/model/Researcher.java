package model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public interface Researcher extends Serializable {
    List<ResearchPaper> getPapers();

    List<ResearchProject> getProjects();

    void addPaper(ResearchPaper paper);

    void printPapers(Comparator<ResearchPaper> comparator);

    int calculateHIndex();

    void joinProject(ResearchProject project);

    String getResearcherName();
}
