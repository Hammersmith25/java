import java.util.Objects;

public class Book extends LibraryEqual {

    private int numberOfPages;

    public Book(String itemId, String title, int year, int pages) {
        super(itemId, title, year);
        this.numberOfPages = pages;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (getClass() != o.getClass()) return false;

        Book book = (Book) o;
        return numberOfPages == book.numberOfPages;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfPages);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + getItemId() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", year=" + getPublicationYear() +
                ", pages=" + numberOfPages +
                '}';
    }
}