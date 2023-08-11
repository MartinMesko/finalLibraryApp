package sk.itvkurze.Kascak_22.src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TitlesPage {
    private final Scanner scanner;

    public TitlesPage(Scanner scanner) { // konstruktor
        this.scanner = scanner;
    }




    public void display() {
        System.out.println("Titles ");
        System.out.println("1 - Show All Titles");
        System.out.println("2 - Add Title");
        System.out.println("3 - Remove Title");
        System.out.println("4 - Back");
        System.out.println("5 - Go back to main menu");
        System.out.print("Choose an option: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next(); // use next() instead of nextInt()
        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid value.");
            display();
            return;
        }
        switch (choice) {
            case 1 -> showAlltitles();
            case 2 -> addTitleMenu();
            case 3 -> deleteTitle();
            case 4 -> goBack();
            case 5 -> LibraryApp.showMainMenu();
            default -> {
                System.out.println("Please enter a number in the range from 1 to 5.");
                display();
            }
        }
    }


    public static void showAlltitles() {
        BufferedReader readerTitles = null;
        BufferedReader readerTitlesDVD = null;
        try {
            // Otvorenie súborov na čítanie
            File titlesFile = new File("titles.txt");
            File titlesDVDFile = new File("titlesDVD.txt");

            readerTitles = new BufferedReader(new FileReader(titlesFile));
            readerTitlesDVD = new BufferedReader(new FileReader(titlesDVDFile));

            String line;
            int lineNumber = 1;

            System.out.println("All titles:");
            while ((line = readerTitles.readLine()) != null) {
                String[] row = line.split(",");
                if (row.length >= 5) {
                    System.out.println(lineNumber + ". Name: " + row[0] + " - Author: " + row[1] + " | ISBN: " + row[2] + " | Number of pages: " + row[3] + " | Available copies: " + row[4]);
                }
                lineNumber++;
            }
            while ((line = readerTitlesDVD.readLine()) != null) {
                String[] row = line.split(",");
                if (row.length >= 5) {
                    System.out.println(lineNumber + ". Name: " + row[0] + " - Author: " + row[1] + "- Number of chapters: " + row[2] + " - Length in minutes: " + row[3] + " | Available copies: " + row[4]);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (readerTitles != null) {
                    readerTitles.close();
                }
                if (readerTitlesDVD != null) {
                    readerTitlesDVD.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addTitleMenu() {
        System.out.println("Add Title");
        System.out.println("1 - Add Book");
        System.out.println("2 - Add DVD");
        System.out.println("3 - Back");
        System.out.print("Choose an option: ");
        String input = scanner.next();
        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid value.");
            addTitleMenu();
            return;
        }
        switch (choice) {
            case 1 -> addBook();
            case 2 -> addDVD();
            case 3 -> display();
            default -> {
                System.out.println("Please enter a number in the range from 1 to 3.");
                addTitleMenu();
            }
        }
    }

    private void addBook() {
        System.out.println("Enter Author's name: ");
        String author = scanner.nextLine();


        System.out.println("Enter title name: ");
        String title = scanner.nextLine();

        System.out.println("Enter available copies: ");
        int availableCopies = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.println("Enter number of Pages: ");
        int numberOfPages = Integer.parseInt(scanner.nextLine());

        Book book = new Book(author, title, availableCopies, isbn, numberOfPages);
        book.addBookToFile();
    }

    private void addDVD() {
        System.out.println("Add DVD");
        String name = scanner.nextLine();

        System.out.print("Enter title name: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author's name: ");
        String author = scanner.nextLine();

        String chapters;
        while (true) {
            System.out.print("Enter number of chapters:");
            String chaptersInput = scanner.nextLine();
            try {
                Integer.parseInt(chaptersInput);
                chapters = chaptersInput;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number of chapters.");
            }
        }

        String length;
        while (true) {
            System.out.print("Enter Length (minutes):");
            String lengthInput = scanner.nextLine();
            try {
                Integer.parseInt(lengthInput);
                length = lengthInput;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid length in minutes.");
            }
        }

        String copies;
        while (true) {
            System.out.print("Enter available copies:");
            String copiesInput = scanner.nextLine();
            try {
                Integer.parseInt(copiesInput);
                copies = copiesInput;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number of available copies.");
            }
        }

        DVD dvd = new DVD(title, author, chapters, length, copies);
        dvd.addDVDToFile();

        System.out.println("DVD added successfully.");

        display();
    }


    private void deleteTitle() {
        List<String> fileContentTitles = new ArrayList<>();
        List<String> fileContentTitlesDVD = new ArrayList<>();

        try {
            // Otvorenie súborov na čítanie
            File titlesFile = new File("titles.txt");
            File titlesDVDFile = new File("titlesDVD.txt");

            BufferedReader readerTitles = new BufferedReader(new FileReader(titlesFile));
            BufferedReader readerTitlesDVD = new BufferedReader(new FileReader(titlesDVDFile));

            String line;
            int lineNumber = 1;
            boolean foundTitle = false;

            System.out.println("Select a title to delete:");
            while ((line = readerTitles.readLine()) != null) {
                String[] row = line.split(",");
                if (row.length >= 5) {
                    System.out.println(lineNumber + ". Name: " + row[0] + " - Author: " + row[1] + " | ISBN: " + row[2] + " | Number of pages: " + row[3] + " | Available copies: " + row[4]);
                }
                fileContentTitles.add(line);
                lineNumber++;
            }
            readerTitles.close();

            while ((line = readerTitlesDVD.readLine()) != null) {
                String[] row = line.split(",");
                if (row.length >= 5) {
                    System.out.println(lineNumber + ". Name: " + row[0] + " - Author: " + row[1] + "- Number of chapters: " + row[2] + " - Length in minutes: " + row[3] + " | Available copies: " + row[4]);
                }
                fileContentTitlesDVD.add(line);
                lineNumber++;
            }
            readerTitlesDVD.close();

            System.out.print("Enter the number of the title to delete: ");
            int titleNumber = scanner.nextInt();

            if (titleNumber <= fileContentTitles.size()) {
                fileContentTitles.remove(titleNumber - 1);
            } else if (titleNumber <= lineNumber) {
                fileContentTitlesDVD.remove(titleNumber - fileContentTitles.size() - 1);
            } else {
                System.out.println("Title not found.");
                display();
                return;
            }

            BufferedWriter writerTitles = new BufferedWriter(new FileWriter(titlesFile));
            for (String content : fileContentTitles) {
                writerTitles.write(content);
                writerTitles.newLine();
            }
            writerTitles.close();

            BufferedWriter writerTitlesDVD = new BufferedWriter(new FileWriter(titlesDVDFile));
            for (String content : fileContentTitlesDVD) {
                writerTitlesDVD.write(content);
                writerTitlesDVD.newLine();
            }
            writerTitlesDVD.close();

            System.out.println("Title removed successfully!");
            scanner.nextLine(); // Consume the newline character
            scanner.nextLine(); // Wait for key press

            // Znovu zobraziť zoznam pred a po odstránení
            System.out.println("All titles:");
            showAlltitles();
            display();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getId() {
        System.out.print("Enter the number of the title: ");
        int titleNumber = scanner.nextInt();
        scanner.nextLine(); // Consumes the newline character after reading an int

        return titleNumber;
    }


    private static void goBack() {
        System.out.println("Going back to main menu...");
    }


}
