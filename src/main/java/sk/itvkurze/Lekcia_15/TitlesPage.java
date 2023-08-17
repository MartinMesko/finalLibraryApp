package sk.itvkurze.Lekcia_15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TitlesPage {
    private final Scanner scanner;
    private static final List<Book> books = new ArrayList<>();
    private static final List<DVD> dvds = new ArrayList<>();
    private final String lineSeparator = System.lineSeparator();
    public static int totalTitlesCount = 0;

    public TitlesPage(Scanner scanner) {
        this.scanner = scanner;
        loadTitles();
    }

    public static int loadTitlesFromFile(String filePath, String type) throws IOException {
        BufferedReader reader = null;
        int addedTitlesCount = 0;

        try {
            File titlesFile = new File(filePath);
            reader = new BufferedReader(new FileReader(titlesFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                try {
                    if (type.equals("Book") && parts.length >= 5) {
                        books.add(new Book(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
                        addedTitlesCount++;
                    } else if (type.equals("DVD") && parts.length >= 5) {
                        int duration = Integer.parseInt(parts[2]);
                        int numberOfTracks = Integer.parseInt(parts[3]);
                        int availableCopies = Integer.parseInt(parts[4]);
                        dvds.add(new DVD(parts[0], parts[1], duration, numberOfTracks, availableCopies));
                        addedTitlesCount++;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing number from line: " + line);
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        totalTitlesCount += addedTitlesCount;
        return addedTitlesCount;
    }

    public void loadTitles() {
        try {
            totalTitlesCount += loadTitlesFromFile("titles.txt", "Book");
            totalTitlesCount += loadTitlesFromFile("titlesDVD.txt", "DVD");
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

        System.out.println("Total number of all titles: " + totalTitlesCount);
        System.out.println(lineSeparator + "Press enter to return to Titles menu...");
        scanner.nextLine();
        displayTitlesMenu();
    }

    private static void addTitle() {
        // TODO: Implementuj metodu pridaj titul
    }

    private static void deleteTitle() {
        // TODO: Implementuje metodu zmazania
    }

    private static void goBack() {
        System.out.println("Going back to main menu...");
    }
}
