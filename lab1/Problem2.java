public class Problem2 {

    public enum BookStatus {
        AVAILABLE,
        BORROWED,
        LOST
    }

    public static final int MAX_BORROW_DAYS = 14;
    private static int totalBooks = 0;
    private final int bookId;
    private String title;
    private String author;
    private BookStatus status;

    {
        totalBooks++;
        bookId = totalBooks;
        status = BookStatus.AVAILABLE;
    }

    public Problem2(String title, String author) {
        this.title = title;    
        this.author = author;
    }

    public void borrow() {
        if (status == BookStatus.AVAILABLE) {
            status = BookStatus.BORROWED;
        }
    }

    public void borrow(int days) {
        if (status == BookStatus.AVAILABLE && days <= MAX_BORROW_DAYS) {
            status = BookStatus.BORROWED;
        }
    }

    public void returnBook() {
        if (status == BookStatus.BORROWED) {
            status = BookStatus.AVAILABLE;
        }
    }

    public void markAsLost() {
        status = BookStatus.LOST;
    }

    public Problem2 changeTitle(String newTitle) {
        this.title = newTitle;
        return this;
    }

    public int getBookId() {
        return bookId;
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    @Override
    public String toString() {
        return "Book #" + bookId +
                " | Title: " + title +
                " | Author: " + author +
                " | Status: " + status;
    }
    public static void main(String args[]){
        Problem2 book1 = new Problem2("1984", "George Orwell");

        book1.borrow();
        book1.returnBook();
        book1.borrow(10);

        System.out.println(book1);

        System.out.println("Total books: " + Problem2.getTotalBooks());
    }
}
