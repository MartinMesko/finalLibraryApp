package sk.itvkurze.Kascak_22.src;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Book {
    private String author;
    private String title;
    private int availableCopies;
    private String isbn;
    private int numberOfPages;

    public Book(String author, String title, int availableCopies, String isbn, int numberOfPages) {
        this.author = author;
        this.title = title;
        this.availableCopies = availableCopies;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
    }

    public void addBookToFile() {
        String bookData = author + "," + title + "," + isbn + "," + numberOfPages + "," + availableCopies;

        try {
            FileWriter fileWriter = new FileWriter("titles.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(bookData);
            printWriter.close();
            System.out.println("Book added successfully.");
        } catch (IOException e) {
            System.out.println("Book not added!!!");
        }
    }

    // Getters and Setters

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }


}
