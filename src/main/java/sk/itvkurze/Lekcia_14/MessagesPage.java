package sk.itvkurze.Lekcia_14;

import java.util.ArrayList;
import java.util.Scanner;

public class MessagesPage {

    private ArrayList<String> messages; // zoznam oznámení

    public MessagesPage() {
        this.messages = new ArrayList<String>();
    }

    // Metóda pre pridanie nového oznámenia
    public void addMessage(String message) {
        //this.messages.add(message);
        //System.out.println("Message added: " + message);
    }

    // Metóda pre výpis všetkých oznámení
    public void listMessages() {
        if (this.messages.size() == 0) {
            System.out.println("There are no messages.");
            return;
        }

        System.out.println("List of messages:");
        for (String message : this.messages) {
            System.out.println("- " + message);
        }
    }

    // Metóda pre odstránenie konkrétneho oznámenia
    public void removeMessage(int index) {
        if (index < 1 || index > this.messages.size()) {
            System.out.println("Invalid message index. Please try again.");
            return;
        }

        String message = this.messages.get(index - 1);
        this.messages.remove(index - 1);
        System.out.println("Message removed: " + message);
    }

    // Metóda pre spracovanie vstupov a zobrazenie možností pre prácu s oznámeniami
    public void display() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n>>>> Messages <<<<\n");
            System.out.println("1 - List messages");
            System.out.println("2 - Add message");
            System.out.println("3 - Remove message");
            System.out.println("4 - Go back");

            System.out.print("\nChoose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    listMessages();
                    break;
                case "2":
                    System.out.print("\nEnter message: ");
                    String message = scanner.nextLine();
                    addMessage(message);
                    break;
                case "3":
                    System.out.print("\nEnter message index: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    removeMessage(index);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
