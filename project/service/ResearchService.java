package service;

import enums.School;
import exceptions.NotAResearcherException;
import model.DefaultResearcher;
import model.ResearchPaper;
import model.ResearchProject;
import model.Researcher;
import model.Student;
import model.Teacher;
import model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ResearchService {
    public void addPaper(Researcher researcher, ResearchPaper paper) {
        researcher.addPaper(paper);
    }

    public void printAllResearchersPapers(Collection<User> users, Comparator<ResearchPaper> comparator) {
        getAllResearchers(users).stream()
                .flatMap(researcher -> researcher.getPapers().stream().map(paper -> Map.entry(researcher, paper)))
                .sorted((left, right) -> comparator.compare(left.getValue(), right.getValue()))
                .forEach(entry -> System.out.println(entry.getKey().getResearcherName() + " -> " + entry.getValue()));
    }

    public Optional<Researcher> findTopCitedResearcher(Collection<User> users) {
        return getAllResearchers(users).stream()
                .max(Comparator.comparingInt(this::getTotalCitations));
    }

    public Optional<Researcher> findTopCitedResearcherBySchool(Collection<User> users, School school) {
        return getAllResearchers(users).stream()
                .filter(researcher -> researcherBelongsToSchool(researcher, school))
                .max(Comparator.comparingInt(this::getTotalCitations));
    }

    public Optional<Researcher> findTopCitedResearcherOfYear(Collection<User> users, int year) {
        return getAllResearchers(users).stream()
                .filter(researcher -> getTotalCitationsForYear(researcher, year) > 0)
                .max(Comparator.comparingInt(researcher -> getTotalCitationsForYear(researcher, year)));
    }

    public Optional<Researcher> findTopCitedResearcherBySchoolOfYear(Collection<User> users, School school, int year) {
        return getAllResearchers(users).stream()
                .filter(researcher -> researcherBelongsToSchool(researcher, school))
                .filter(researcher -> getTotalCitationsForYear(researcher, year) > 0)
                .max(Comparator.comparingInt(researcher -> getTotalCitationsForYear(researcher, year)));
    }

    public List<Researcher> getAllResearchers(Collection<User> users) {
        List<Researcher> researchers = new ArrayList<>();
        for (User user : users) {
            extractResearcher(user).ifPresent(researchers::add);
        }
        return researchers;
    }

    public Optional<Researcher> extractResearcher(User user) {
        if (user instanceof Researcher directResearcher) {
            return Optional.of(directResearcher);
        }
        if (user instanceof Student student && student.isResearcher()) {
            return Optional.of(student.getResearcherRole());
        }
        if (user instanceof Teacher teacher && teacher.isResearcher()) {
            return Optional.of(teacher.getResearcherRole());
        }
        return Optional.empty();
    }

    public void addResearcherToProject(ResearchProject project, User user) throws NotAResearcherException {
        project.addParticipant(user);
    }

    private int getTotalCitations(Researcher researcher) {
        return researcher.getPapers().stream().mapToInt(ResearchPaper::getCitations).sum();
    }

    private int getTotalCitationsForYear(Researcher researcher, int year) {
        return researcher.getPapers().stream()
                .filter(paper -> paper.getPublishDate().getYear() == year)
                .mapToInt(ResearchPaper::getCitations)
                .sum();
    }

    private boolean researcherBelongsToSchool(Researcher researcher, School school) {
        return getResearcherOwner(researcher)
                .map(owner -> owner.getSchool() == school)
                .orElse(false);
    }

    private Optional<User> getResearcherOwner(Researcher researcher) {
        if (researcher instanceof User user) {
            return Optional.of(user);
        }
        if (researcher instanceof DefaultResearcher defaultResearcher) {
            return Optional.of(defaultResearcher.getOwner());
        }
        return Optional.empty();
    }
}
