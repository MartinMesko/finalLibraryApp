package sk.itvkurze.Lekcia_15;

import sk.itvkurze.Lekcia_16.Book;
import sk.itvkurze.Lekcia_16.DVD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TitlesPage {
    private final Scanner scanner;
    private final List<Book> books = new ArrayList<>();
    private final List<DVD> dvds = new ArrayList<>();
    private final String lineSeparator = System.lineSeparator();

    public TitlesPage(Scanner scanner) { // konstruktor
        this.scanner = scanner;
        loadTitles();
    }

    private List<String> loadTitlesFromFile(String filePath) throws IOException {
        List<String> result = new ArrayList<>();
        BufferedReader reader = null;
        try {
            File titlesFile = new File(filePath);
            reader = new BufferedReader(new FileReader(titlesFile));

            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }

    public void loadTitles() {
        try {
            loadTitlesFromFile("titles.txt");
            loadTitlesFromFile("titlesDVD.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void displayTitlesMenu() {
        System.out.println("Titles ");
        System.out.println("1 - Show All Titles");
        System.out.println("2 - Add Title");
        System.out.println("3 - Remove Title");
        System.out.println("4 - Back");
        System.out.print("Choose an option: ");
        String input = scanner.nextLine();

        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid value.");
            displayTitlesMenu();
            return;
        }

        switch (choice) {
            case 1 -> showAllTitles();
            case 2 -> addTitle();
            case 3 -> deleteTitle();
            case 4 -> goBack();
            default -> {
                System.out.println("Please enter a number in the range from 1 to 4.");
                displayTitlesMenu();
            }
        }
    }

    public void showAllTitles() {
        System.out.println("All Titles:");
        for (Book book : books) {
            System.out.println("Name: " + book.getTitle() + " - Author: " + book.getAuthorName() + " | ISBN: " + book.getIsbn() + " | Number of pages: " + book.getPageCount() + " | Available copies: " + book.getAvailableCopies());
        }
        for (DVD dvd : dvds) {
            System.out.println("Name: " + dvd.getTitle() + " - Author: " + dvd.getAuthorName() + " - Number of chapters: " + dvd.getNumberOfTracks() + " - Length in minutes: " + dvd.getDurationInMinutes() + " | Available copies: " + dvd.getAvailableCopies());
        }

        System.out.println(lineSeparator + "Press enter to return to Titles menu...");
        scanner.nextLine();
        displayTitlesMenu();
    }

    private void addTitle() {
        //TODO: Implementuj metodu pridaj titul
    }

    private void deleteTitle() {
        //TODO: Implementuje metodu zmazania
    }

    private void goBack() {
        System.out.println("Going back to main menu...");
    }
}
