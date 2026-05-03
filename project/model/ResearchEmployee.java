package model;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class ResearchEmployee extends Employee implements Researcher {
    @Serial
    private static final long serialVersionUID = 1L;

    private final DefaultResearcher researcherRole;

    public ResearchEmployee(String id,
                            String passwordHash,
                            String firstName,
                            String lastName,
                            String email,
                            double salary,
                            LocalDate hireDate) {
        super(id, passwordHash, firstName, lastName, email, salary, hireDate);
        this.researcherRole = new DefaultResearcher(this);
    }

    @Override
    public List<ResearchPaper> getPapers() {
        return researcherRole.getPapers();
    }

    @Override
    public List<ResearchProject> getProjects() {
        return researcherRole.getProjects();
    }

    @Override
    public void addPaper(ResearchPaper paper) {
        researcherRole.addPaper(paper);
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        researcherRole.printPapers(comparator);
    }

    @Override
    public int calculateHIndex() {
        return researcherRole.calculateHIndex();
    }

    @Override
    public void joinProject(ResearchProject project) {
        researcherRole.joinProject(project);
    }

    @Override
    public String getResearcherName() {
        return researcherRole.getResearcherName();
    }
}
