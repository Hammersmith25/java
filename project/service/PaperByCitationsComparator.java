package service;

import model.ResearchPaper;

import java.util.Comparator;

public class PaperByCitationsComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper first, ResearchPaper second) {
        int citationComparison = Integer.compare(second.getCitations(), first.getCitations());
        if (citationComparison != 0) {
            return citationComparison;
        }
        return first.getTitle().compareToIgnoreCase(second.getTitle());
    }
}
