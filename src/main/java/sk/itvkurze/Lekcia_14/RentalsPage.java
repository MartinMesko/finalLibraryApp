package sk.itvkurze.Lekcia_14;

import java.util.Scanner;

public class RentalsPage {
    private Scanner scanner;

    public RentalsPage(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        System.out.println(">>>> Rentals Menu <<<<");
        System.out.println("1 - Rent a Book");
        System.out.println("2 - Return a Book");
        System.out.println("3 - List All Rentals");
        System.out.println("4 - Search Rentals by Member ID");
        System.out.println("5 - Back to Main Menu");
        System.out.println("Choose an option:");
    }

    public void processOption(int option) {
        switch (option) {
            case 1:
                rentBook();
                break;
            case 2:
                returnBook();
                break;
            case 3:
                listAllRentals();
                break;
            case 4:
                searchRentalsByMemberId();
                break;
            case 5:
                break;
            default:
                System.out.println("Invalid option. Please enter a number in the range from 1 to 5.");
                break;
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
