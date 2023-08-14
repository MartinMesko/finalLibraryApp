package sk.itvkurze.Lekcia_15;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TitlesPage {
    private final Scanner scanner;
    private final List<String> titlesBooks = new ArrayList<>();
    private final List<String> titlesDVDs = new ArrayList<>();

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
            case 1:
                showAlltitles();
                break;
            case 2:
                addTitle();
                break;
            case 3:
                deleteTitle();
                break;
            case 4:
                goBack();
                break;
            default:
                System.out.println("Please enter a number in the range from 1 to 4.");
                display();
                break;
        }
    }

    public void showAlltitles() {
        System.out.println("All Titles:");
        for (String title : titlesBooks) {
            String[] row = title.split(",");
            if (row.length >= 5) {
                System.out.println("Name: " + row[0] + " - Author: " + row[1] + " | ISBN: " + row[2] + " | Number of pages: " + row[3] + " | Available copies: " + row[4]);
            }
        }
        for (String title : titlesDVDs) {
            String[] row = title.split(",");
            if (row.length >= 5) {
                System.out.println("Name: " + row[0] + " - Author: " + row[1] + " - Number of chapters: " + row[2] + " - Length in minutes: " + row[3] + " | Available copies: " + row[4]);
            }
        }

        System.out.println("Press enter to return to Titles menu...");
        scanner.nextLine();
        display();
    }

    private void addTitle() {
        // TODO: Implementuj metodu pridaj titul
        // Napríklad: titlesBooks.add("Name,Author,ISBN,Pages,Copies"); alebo pridať interaktívne pomocou scanneru
    }

    private void deleteTitle() {
        // TODO: Implementuje metodu zmazania
        // Napríklad: titlesBooks.remove(specificTitle);
    }

    private void goBack() {
        System.out.println("Going back to main menu...");
    }
}
