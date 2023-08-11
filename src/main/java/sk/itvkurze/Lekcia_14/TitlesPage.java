package sk.itvkurze.Lekcia_14;

import java.util.Scanner;

public class TitlesPage extends LibraryApp{
    private Scanner scanner;

    public TitlesPage(Scanner scanner) {
        this.scanner = scanner;
    }

    public void display() {
        System.out.println("Titles Page");
        System.out.println("1 - Search titles");
        System.out.println("2 - Add a new title");
        System.out.println("3 - Edit a title");
        System.out.println("4 - Delete a title");
        System.out.println("5 - Go back to main menu");
        System.out.print("Choose an option: ");
        processInput();
    }

    private void processInput() {
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                searchTitles();
                break;
            case "2":
                addTitle();
                break;
            case "3":
                editTitle();
                break;
            case "4":
                deleteTitle();
                break;
            case "5":
                goBack();
                break;
            default:
                System.out.println("Please enter a valid value.");
                display();
                break;
        }
    }

    private void searchTitles() {
        // TODO: Implementuj metodu najst stitul
    }

    private void addTitle() {
        // TODO: Implementuj metodu pridaj titul
    }

    private void editTitle() {
        // TODO: Implementuj metodu edituj titiul
    }

    private void deleteTitle() {
        // TODO: Implementuje metodu zmazania
    }

    private void goBack() {
        System.out.println("Going back to main menu...");
    }
}
