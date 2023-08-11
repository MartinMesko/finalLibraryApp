package sk.itvkurze.Lekcia_14;

import java.util.Scanner;
public class MembersPage {

    public static void showPage() {
        System.out.println(">>> Members Page <<<");
        System.out.println("1 - Search Members");
        System.out.println("2 - Add Member");
        System.out.println("3 - Edit Member");
        System.out.println("4 - Delete Member");
        System.out.println("5 - Go back to Main Menu");
        System.out.print("Choose an option: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                searchMembers();
                break;
            case 2:
                addMember();
                break;
            case 3:
                editMember();
                break;
            case 4:
                deleteMember();
                break;
            case 5:
                LibraryApp.showMainMenu();
                break;
            default:
                System.out.println("Please enter a number in the range from 1 to 5.");
                showPage();
                break;
        }
    }

    private static void searchMembers() {
        // implementacia hladania clena
    }

    private static void addMember() {
        // implementacia  pridania Clena
    }

    private static void editMember() {
        // implementacia editacie clena
    }

    private static void deleteMember() {
        // implementacia
    }
}

