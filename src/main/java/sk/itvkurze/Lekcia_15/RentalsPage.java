package sk.itvkurze.Lekcia_15;

import java.util.Scanner;

public class RentalsPage {
    private Scanner scanner;

    public RentalsPage(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayRentalsMenu() {
        System.out.println(">>>> Rentals Menu <<<<");
        System.out.println("1 - Rent a Book");
        System.out.println("2 - Return a Book");
        System.out.println("3 - List All Rentals");
        System.out.println("4 - Search Rentals by Member ID");
        System.out.println("5 - Back to Main Menu");
        System.out.print("Choose an option: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int choice;

        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid value.");
            displayRentalsMenu();
            return;
        }

        switch (choice) {
            case 1 -> rentBook();
            case 2 -> returnBook();
            case 3 -> listAllRentals();
            case 4 -> searchRentalsByMemberId();
            case 5 -> {
                // Tu môžete prejsť späť do hlavnej ponuky alebo urobiť niečo iné.
            }
            default -> {
                System.out.println("Invalid option. Please enter a number in the range from 1 to 5.");
                displayRentalsMenu();
            }
        }
    }


    private void rentBook() {
        System.out.println("Rent a Book selected.");
        // implementacia pozicania
    }

    private void returnBook() {
        System.out.println("Return a Book selected.");
        // implementacia vratenia
    }

    private void listAllRentals() {
        System.out.println("List All Rentals selected.");
        // implementacia listu pozicania
    }

    private void searchRentalsByMemberId() {
        System.out.println("Search Rentals by Member ID selected.");
        // implementacia hladania podla ID
    }
}
