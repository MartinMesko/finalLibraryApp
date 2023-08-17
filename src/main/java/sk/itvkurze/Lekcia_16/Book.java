package sk.itvkurze.Lekcia_16;

public class Book {
    private String authorName;
    private String title;
    private int pageCount;
    private String isbn;
    private int availableCopies;


    // konštruktor
    public Book(String title, String authorName, String isbn,int pageCount, int availableCopies) {
        this.authorName = authorName;
        this.title = title;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.availableCopies = availableCopies;

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


}
