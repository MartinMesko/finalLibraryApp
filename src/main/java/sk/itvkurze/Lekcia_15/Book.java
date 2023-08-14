package sk.itvkurze.Lekcia_15;

public class Book {
    private String authorName;
    private String title;
    private int pageCount;
    private String isbn;
    private int availableCopies;
    private int totalCopies;

    // konštruktor
    public Book(String authorName, String title, int pageCount, String isbn, int availableCopies, int totalCopies) {
        this.authorName = authorName;
        this.title = title;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
    }

    // getter a setter metódy
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }
}
