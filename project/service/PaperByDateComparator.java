package service;

import model.ResearchPaper;

import java.util.Comparator;

public class PaperByDateComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper first, ResearchPaper second) {
        return first.getPublishDate().compareTo(second.getPublishDate());
    }
}
