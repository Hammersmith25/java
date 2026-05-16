package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResearchPaper implements Serializable, Comparable<ResearchPaper> {
    @Serial
    private static final long serialVersionUID = 1L;

    private String title;
    private List<String> authors;
    private String journal;
    private int citations;
    private int pages;
    private String doi;
    private LocalDate publishDate;

    public ResearchPaper(String title,
                         List<String> authors,
                         String journal,
                         int citations,
                         int pages,
                         String doi,
                         LocalDate publishDate) {
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.journal = journal;
        this.citations = citations;
        this.pages = pages;
        this.doi = doi;
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return new ArrayList<>(authors);
    }

    public void setAuthors(List<String> authors) {
        this.authors = new ArrayList<>(authors);
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public int getCitations() {
        return citations;
    }

    public void setCitations(int citations) {
        this.citations = citations;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "ResearchPaper{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", journal='" + journal + '\'' +
                ", citations=" + citations +
                ", pages=" + pages +
                ", doi='" + doi + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }

    @Override
    public int compareTo(ResearchPaper other) {
        int dateComparison = publishDate.compareTo(other.publishDate);
        if (dateComparison != 0) {
            return dateComparison;
        }
        return title.compareToIgnoreCase(other.title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResearchPaper that)) {
            return false;
        }
        return Objects.equals(doi, that.doi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doi);
    }
}
