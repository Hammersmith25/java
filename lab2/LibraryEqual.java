import java.util.Objects;

public class LibraryEqual {

    private String itemId;
    private String title;
    private int publicationYear;

    public LibraryEqual(String itemId, String title, int publicationYear) {
        if (itemId == null || itemId.isBlank()) {
            throw new IllegalArgumentException("Item ID cannot be empty");
        }
        this.itemId = itemId;
        this.title = title;
        this.publicationYear = publicationYear;
    }

    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LibraryEqual that = (LibraryEqual) o;
        return Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId);
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "id='" + itemId + '\'' +
                ", title='" + title + '\'' +
                ", year=" + publicationYear +
                '}';
    }
}