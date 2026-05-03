package service;

import model.ResearchPaper;

import java.util.Comparator;

public class PaperByTitleComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper first, ResearchPaper second) {
        return first.getTitle().compareToIgnoreCase(second.getTitle());
    }
}
