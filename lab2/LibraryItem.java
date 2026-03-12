public abstract class LibraryItem {

    private String title;
    private String author;
    private int publicationYear;

    public LibraryItem(String title, String author, int publicationYear) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        if (publicationYear <= 0) {
            throw new IllegalArgumentException("Invalid publication year");
        }

        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
    }

    public void setAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        this.author = author;
    }

    public void setPublicationYear(int publicationYear) {
        if (publicationYear <= 0) {
            throw new IllegalArgumentException("Invalid publication year");
        }
        this.publicationYear = publicationYear;
    }

    public abstract String getItemType();

    @Override
    public String toString() {
        return getItemType() + ": \"" + title + "\" by " + author +
               " (" + publicationYear + ")";
    }

    
    public static void main(String[] args){
        LibraryItem item = new Book("1984", "George Orwell", 1949, 328);
        System.out.println(item);
    }
}

class Book extends LibraryItem {

    private int numberOfPages;

    public Book(String title, String author, int year, int numberOfPages) {
        super(title, author, year);

        if (numberOfPages <= 0) {
            throw new IllegalArgumentException("Number of pages must be positive");
        }

        this.numberOfPages = numberOfPages;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        if (numberOfPages <= 0) {
            throw new IllegalArgumentException("Number of pages must be positive");
        }
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String getItemType() {
        return "Book";
    }

    @Override
    public String toString() {
        return super.toString() + ", Pages: " + numberOfPages;
    }

    
}