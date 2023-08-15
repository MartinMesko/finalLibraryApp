package sk.itvkurze.Lekcia_16;

import java.io.*;
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

    public TitlesPage() {
        this.scanner = new Scanner(System.in);
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

    public List<String> getTitles() {
        return new ArrayList<>(titles);
    }

    public List<String> getTitlesDVD() {
        return new ArrayList<>(titlesDVD);
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

    public void addTitle() {
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

    private void addBook() {
        System.out.print("Enter the name of the book: ");
        String name = scanner.nextLine();
        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine();
        System.out.print("Enter the ISBN of the book: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter the number of pages of the book: ");
        int pages = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the number of copies of the book: ");
        int copies = Integer.parseInt(scanner.nextLine());

        boolean result = saveTitle(name, author, "Book", isbn + "," + pages, copies);
        if (result) {
            System.out.println("The book has been added successfully.");
        } else {
            System.out.println("Failed to add the book.");
        }
    }

    private void addDVD() {
        System.out.print("Enter the name of the DVD: ");
        String name = scanner.nextLine();
        System.out.print("Enter the author of the DVD: ");
        String author = scanner.nextLine();
        System.out.print("Enter the number of chapters of the DVD: ");
        int chapters = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the length in minutes of the DVD: ");
        int length = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the number of copies of the DVD: ");
        int copies = Integer.parseInt(scanner.nextLine());

        boolean result = saveTitle(name, author, "DVD", chapters + "," + length, copies);
        if (result) {
            System.out.println("The DVD has been added successfully.");
        } else {
            System.out.println("Failed to add the DVD.");
        }
    }

    boolean saveTitle(String titleName, String author, String type, String description, int availableCopies) {
        String titleString = titleName + "," + author + "," + type + "," + description + "," + availableCopies;

        try {
            String fileName = type.equalsIgnoreCase("DVD") ? "titlesDVD.txt" : "titles.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                writer.write(titleString + lineSeparator);
            }

            if (type.equalsIgnoreCase("DVD")) {
                titlesDVD.add(titleString);
            } else {
                titles.add(titleString);
            }

            return true;  // Return true if title is added successfully
        } catch (IOException e) {
            e.printStackTrace();
            return false;  // Return false if title addition failed
        }
    }



//    private void addTitle() {
//        System.out.println("Add a new title");
//        System.out.println("1 - Add a book");
//        System.out.println("2 - Add a DVD");
//        System.out.println("3 - Back");
//        System.out.print("Choose an option: ");
//        String input = scanner.nextLine();
//
//        int choice;
//        try {
//            choice = Integer.parseInt(input);
//        } catch (NumberFormatException e) {
//            System.out.println("Please enter a valid value.");
//            addTitle();
//            return;
//        }
//
//        switch (choice) {
//            case 1 -> addBook();
//            case 2 -> addDVD();
//            case 3 -> displayTitlesMenu();
//            default -> {
//                System.out.println("Please enter a number in the range from 1 to 3.");
//                addTitle();
//            }
//        }
//    }






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


}
