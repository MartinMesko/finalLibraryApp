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
    private final List<String> titles = new ArrayList<>();
    private final List<String> titlesDVD = new ArrayList<>();
    private final String lineSeparator = System.lineSeparator();

    public TitlesPage(Scanner scanner) { // konstruktor
        this.scanner = scanner;
        loadTitles();
    }

    private void loadTitles() {
        BufferedReader readerTitles = null;
        BufferedReader readerTitlesDVD = null;
        try {
            File titlesFile = new File("titles.txt");
            File titlesDVDFile = new File("titlesDVD.txt");

            readerTitles = new BufferedReader(new FileReader(titlesFile));
            readerTitlesDVD = new BufferedReader(new FileReader(titlesDVDFile));

            String line;
            while ((line = readerTitles.readLine()) != null) {
                titles.add(line);
            }
            while ((line = readerTitlesDVD.readLine()) != null) {
                titlesDVD.add(line);
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

    public void display() {
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
            display();
            return;
        }
        switch (choice) {
            case 1 -> showAllTitles();
            case 2 -> addTitle();
            case 3 -> deleteTitle();
            case 4 -> goBack();
            default -> {
                System.out.println("Please enter a number in the range from 1 to 4.");
                display();
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
        display();  // Zobrazíme menu s názvami
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
