package sk.itvkurze.Lekcia_15;

import java.util.Scanner;

public class LibraryApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        TitlesPage titlesPage = new TitlesPage(scanner);

        do {
            System.out.println(">>>> Welcome to our Library <<<<");
            System.out.println("1 - Titles");
            System.out.println("2 - Members");
            System.out.println("3 - Rentals");
            System.out.println("4 - Messages");
            System.out.println("5 - Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid value.");
                choice = 0; // aby sa cyklus opakoval
                continue;
            }

            switch (choice) {
                case 1:
                    titlesPage.display();
                    break;
                case 2:
                    MembersPage.showPage();
                    break;
                case 3:
                    // prejde Rentals submenu
                    break;
                case 4:
                    // prejde Messages submenu
                    break;
                case 5:
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Please enter a number in the range from 1 to 5");
                    break;
            }
        } while (choice != 5);

        // scanner.close();  // v prípade, že sa scanner zavrie tu, môže spôsobiť problémy v iných častiach programu, kde sa tento scanner používa
    }

    public static void showMainMenu() {
        // Táto metóda je prázdna, môžete ju buď doplniť alebo odstrániť, ak nie je potrebná.
    }
}
