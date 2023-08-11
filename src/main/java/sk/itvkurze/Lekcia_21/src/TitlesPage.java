package sk.itvkurze.Lekcia_21.src;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
        System.out.print("Choose an option: ");
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
            case 2 -> addDvd();
            case 3 -> display();
            default -> {
                System.out.println("Please enter a number in the range from 1 to 3.");
                addTitleMenu();
            }
        }
    }

    private void addBook() {
        System.out.println("Add Book");
        String aslk = scanner.nextLine();

        System.out.print(" Enter title name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Author's name: ");
        String author = scanner.nextLine();

        String isbn;
        while (true) {
            System.out.print("Enter ISBN: ");
            String isbnInput = scanner.nextLine();
            if (validateISBN(isbnInput)) {
                isbn = isbnInput;
                break;
            } else {
                System.out.println("Please enter a valid ISBN.");
            }
        }

        String pages;
        while (true) {
            System.out.print("Enter number of Pages: ");
            String pagesInput = scanner.nextLine();
            try {
                Integer.parseInt(pagesInput);
                pages = pagesInput;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number of pages.");
            }
        }

        String copies;
        while (true) {
            System.out.print("Enter available copies:  ");
            String copiesInput = scanner.nextLine();
            try {
                Integer.parseInt(copiesInput);
                copies = copiesInput;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number of available copies.");
            }
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("titles.txt", true));
            writer.write(name + "," + author + "," + isbn + "," + pages + "," + copies);
            writer.newLine();
            writer.close();
            System.out.println("Book added successfully.");
        } catch (IOException e) {
            System.out.println("Book not added !!!");
            e.printStackTrace();
        }

        display();
    }

    private boolean validateISBN(String isbn) {
        // Príklad jednoduchej validácie ISBN: ISBN musí mať dĺžku 13 a obsahovať iba číslice
        return isbn.matches("\\d{13}");
    }


    private void addDvd() {
        System.out.println("Add DVD");
        String sd = scanner.nextLine();

        System.out.print("Enter title name: ");
        String name = scanner.nextLine();

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

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("titlesDVD.txt", true));
            writer.println(name + "," + author + "," + chapters + "," + length + "," + copies);
            writer.close();
            System.out.println("DVD added successfully.");
        } catch (IOException e) {
            System.out.println("DVD not added !!!");
            e.printStackTrace();
        }

        display();
    }


    private void deleteTitle() {
        try {
            // Získanie súboru titles.txt
            File titlesFile = new File("titles.txt");
            // Načítanie všetkých riadkov zo súboru do zoznamu
            List<String> lines = Files.readAllLines(titlesFile.toPath(), StandardCharsets.UTF_8);

            // Získanie súboru titlesDVD.txt
            File titlesDVDFile = new File("titlesDVD.txt");
            // Načítanie všetkých riadkov zo súboru do zoznamu
            List<String> linesDVD = Files.readAllLines(titlesDVDFile.toPath(), StandardCharsets.UTF_8);

            if (lines.isEmpty() && linesDVD.isEmpty()) {
                System.out.println("No titles to remove.");
                System.out.println("Press any key to return to titles...");
                scanner.nextLine();
                goBack();
                return;
            }

            System.out.println("All titles:");
            for (int i = 0; i < lines.size(); i++) {
                System.out.println((i + 1) + ". " + lines.get(i));
            }

            for (int i = 0; i < linesDVD.size(); i++) {
                System.out.println((lines.size() + i + 1) + ". " + linesDVD.get(i));
            }

            System.out.println("Select a title to delete:");
            int titleNumber;
            while (true) {
                try {
                    String input = scanner.nextLine();
                    titleNumber = Integer.parseInt(input);
                    if (titleNumber >= 1 && titleNumber <= (lines.size() + linesDVD.size())) {
                        break;
                    } else {
                        System.out.println("Please enter a number in the range from 1 to " + (lines.size() + linesDVD.size()));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid value.");
                }
            }

            // Vymazanie vybraného titulu zo zoznamu
            if (titleNumber <= lines.size()) {
                lines.remove(titleNumber - 1);
                // Prepísanie súboru titles.txt s aktualizovanými riadkami
                Files.write(titlesFile.toPath(), lines, StandardCharsets.UTF_8);
            } else {
                int index = titleNumber - lines.size() - 1;
                linesDVD.remove(index);
                // Prepísanie súboru titlesDVD.txt s aktualizovanými riadkami
                Files.write(titlesDVDFile.toPath(), linesDVD, StandardCharsets.UTF_8);
            }

            System.out.println("Title removed successfully.");
            System.out.println("Press any key to continue...");
            scanner.nextLine();
        } catch (IOException e) {
            System.out.println("Title not removed.");
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
