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

            if (scanner.hasNextInt()) { // kontrola, či je nasledujúce vstupné číslo
                choice = scanner.nextInt();
                scanner.nextLine();
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
            } else { // používateľ zadal neplatný vstup
                System.out.println("Please enter a valid value.");
                scanner.next(); // prečítanie neplatného vstupu z konzoly, aby sa zabránilo nekonečnému cyklovaniu
                choice = 0; // nastavenie choice na 0, aby sa cyklus opakoval
            }
        } while (choice != 5);

       // scanner.close();
    }

    public static void showMainMenu() {
    }

}
