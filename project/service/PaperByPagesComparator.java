package service;

import model.ResearchPaper;

import java.util.Comparator;

public class PaperByPagesComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper first, ResearchPaper second) {
        int pageComparison = Integer.compare(second.getPages(), first.getPages());
        if (pageComparison != 0) {
            return pageComparison;
        }
        return first.getTitle().compareToIgnoreCase(second.getTitle());
    }
}
