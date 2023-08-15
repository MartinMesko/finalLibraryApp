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
    private List<String> titles = new ArrayList<>();
    private List<String> titlesDVD = new ArrayList<>();
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
            titles = loadTitlesFromFile("titles.txt");
            titlesDVD = loadTitlesFromFile("titlesDVD.txt");
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
        for (String title : titles) {
            String[] row = title.split(",");
            if (row.length >= 5) {
                System.out.println("Name: " + row[0] + " - Author: " + row[1] + " | ISBN: " + row[2] + " | Number of pages: " + row[3] + " | Available copies: " + row[4]);
            }
        }
        for (String title : titlesDVD) {
            String[] row = title.split(",");
            if (row.length >= 5) {
                System.out.println("Name: " + row[0] + " - Author: " + row[1] + " - Number of chapters: " + row[2] + " - Length in minutes: " + row[3] + " | Available copies: " + row[4]);
            }
        }
        System.out.println(lineSeparator + "Press enter to return to Titles menu...");
        scanner.nextLine();  // Počká sa, kým používateľ stlačí kláves enter
        displayTitlesMenu();  // Zobrazíme menu s názvami
    }

    private void addTitle() {
        System.out.println("Add a new title");
        System.out.println("1 - Add a book");
        System.out.println("2 - Add a DVD");
        System.out.println("3 - Back");
        System.out.print("Choose an option: ");
        String input = scanner.nextLine();

        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid value.");
            addTitle();
            return;
        }

        switch (choice) {
            case 1 -> addBook();
            case 2 -> addDVD();
            case 3 -> displayTitlesMenu();
            default -> {
                System.out.println("Please enter a number in the range from 1 to 3.");
                addTitle();
            }
        }
    }

    protected boolean addBook(String name, String author, String isbn, String pages, String copies) {
        if (name == null || author == null || isbn == null || pages == null || copies == null) {
            return false;
        }
        String newTitle = name + "," + author + "," + isbn + "," + pages + "," + copies;
        return titles.add(newTitle);
    }

    protected boolean addDVD(String name, String author, String chapters, String length, String copies) {
        if (name == null || author == null || chapters == null || length == null || copies == null) {
            return false;
        }
        String newTitle = name + "," + author + "," + chapters + "," + length + "," + copies;
        return titlesDVD.add(newTitle);
    }

    private void addBook() {
        System.out.println("Add a new book");
        System.out.print("Enter the name of the book: ");
        String name = scanner.nextLine();
        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine();
        System.out.print("Enter the ISBN of the book: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter the number of pages of the book: ");
        String pages = scanner.nextLine();
        System.out.print("Enter the number of copies of the book: ");
        String copies = scanner.nextLine();

        String newTitle = name + "," + author + "," + isbn + "," + pages + "," + copies;
        titles.add(newTitle);

        System.out.println("The book has been added successfully.");
        System.out.println(lineSeparator + "Press enter to return to Titles menu...");
        scanner.nextLine();  // Počká sa, kým používateľ stlačí kláves enter
        displayTitlesMenu();  // Zobrazíme menu s názvami
    }

    private void addDVD() {
        System.out.println("Add a new DVD");
        System.out.print("Enter the name of the DVD: ");
        String name = scanner.nextLine();
        System.out.print("Enter the author of the DVD: ");
        String author = scanner.nextLine();
        System.out.print("Enter the number of chapters of the DVD: ");
        String chapters = scanner.nextLine();
        System.out.print("Enter the length in minutes of the DVD: ");
        String length = scanner.nextLine();
        System.out.print("Enter the number of copies of the DVD: ");
        String copies = scanner.nextLine();

        String newTitle = name + "," + author + "," + chapters + "," + length + "," + copies;
        titlesDVD.add(newTitle);

        System.out.println("The DVD has been added successfully.");
        System.out.println(lineSeparator + "Press enter to return to Titles menu...");
        scanner.nextLine();  // Počká sa, kým používateľ stlačí kláves enter
        displayTitlesMenu();  // Zobrazíme menu s názvami
    }

    private void deleteTitle() {
        System.out.println("Remove a title");
        System.out.print("Enter the name of the title: ");
        String name = scanner.nextLine();

        boolean found = false;
        for (String title : titles) {
            String[] row = title.split(",");
            if (row.length >= 5 && row[0].equals(name)) {
                titles.remove(title);
                found = true;
                break;
            }
        }
        if (!found) {
            for (String title : titlesDVD) {
                String[] row = title.split(",");
                if (row.length >= 5 && row[0].equals(name)) {
                    titlesDVD.remove(title);
                    found = true;
                    break;
                }
            }
        }

        if (found) {
            System.out.println("The title has been removed successfully.");
        } else {
            System.out.println("The title has not been found.");
        }
        System.out.println(lineSeparator + "Press enter to return to Titles menu...");
        scanner.nextLine();  // Počká sa, kým používateľ stlačí kláves enter
        displayTitlesMenu();  // Zobrazíme menu s názvami
    }

    private void goBack() {
        System.out.println("Going back to main menu...");
    }


//    private static void addTitle() {
//        // TODO: Implementuj metodu pridaj titul
//    }
//
//    private static void deleteTitle() {
//        // TODO: Implementuje metodu zmazania
//    }
//
//    private static void goBack() {
//        System.out.println("Going back to main menu...");
//    }
}
