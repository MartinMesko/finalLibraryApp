package sk.itvkurze.Kascak_22.src;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MembersPage {

    public static void showPage() {
        System.out.println(">>> Members Page <<<");
        System.out.println("1 - Show all members");
        System.out.println("2 - Add new member");
        System.out.println("3 - Remove member");
        System.out.println("4 - Back");
        System.out.print("Choose an option: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> showAllMembers();
            case 2 -> addMember();
            case 3 -> deleteMember();
            case 4 -> LibraryApp.showMainMenu();
            default -> {
                System.out.println("Please enter a number in the range from 1 to 4.");
                showPage();
            }
        }
    }

    public static void showAllMembers() {
        List<Member> members = readMembersFromFile();

        if (members.isEmpty()) {
            System.out.println("All members page");
            System.out.println("Member list is empty.");
            System.out.println("Press any key to return to Members page...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            return;
        }

        System.out.println("All members page");

        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            System.out.println((i + 1) + ". " + member);
        }

        System.out.println("Press any key to return to Members page...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    static List<Member> readMembersFromFile() {
        List<Member> members = new ArrayList<>();

        try {
            File file = new File("members.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] memberData = line.split("\\|");

                if (memberData.length == 5) {
                    String firstName = memberData[0].trim();
                    String lastName = memberData[1].trim();
                    String documentNumber = memberData[2].trim();
                    String dateString = memberData[3].trim();
                    int personalId = Integer.parseInt(memberData[4].trim());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date dateOfBirth = dateFormat.parse(dateString);

                    Member member = new Member(firstName, lastName, documentNumber, dateOfBirth, personalId);
                    members.add(member);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: members.txt");
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }

        return members;
    }

    private static void addMember() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add member page");
        System.out.print("Enter member's first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter member's last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter member's date of birth (dd/MM/yyyy): ");
        String dateString = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        Date dateOfBirth;

        try {
            dateOfBirth = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Please enter a valid date in the format dd/MM/yyyy.");
            addMember();
            return;
        }

        System.out.print("Enter member's personal id: ");
        int personalId;

        try {
            personalId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number for personal id.");
            addMember();
            return;
        }

        Member member = new Member(firstName, lastName, "", dateOfBirth, personalId); // Empty document number, as it is not specified in the requirements
        List<Member> members = readMembersFromFile();
        members.add(member);
        saveMembersToFile(members);

        System.out.println("Member created successfully.");
        System.out.println("Press any key to return to Members page...");
        scanner.nextLine();
    }

    private static void deleteMember() {
        List<Member> members = readMembersFromFile();
        Scanner scanner = new Scanner(System.in);

        if (members.isEmpty()) {
            System.out.println("Remove member page");
            System.out.println("Member list is empty.");
            System.out.println("Press any key to return to Members page...");
            scanner.nextLine();
            return;
        }

        System.out.println("Remove member page");

        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            System.out.println((i + 1) + " - " + member);
        }

        System.out.print("Choose an option: ");
        int choice;

        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            deleteMember();
            return;
        }

        if (choice < 1 || choice > members.size()) {
            System.out.println("Please enter a number in the range from 1 to " + members.size() + ".");
            deleteMember();
            return;
        }

        members.remove(choice - 1);
        saveMembersToFile(members);

        System.out.println("Member deleted successfully.");
        System.out.println("Press any key to return to Members page...");
        scanner.nextLine();
    }

    private static void saveMembersToFile(List<Member> members) {
        try {
            File file = new File("members.txt");
            PrintWriter writer = new PrintWriter(file);

            for (Member member : members) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String dateString = dateFormat.format(member.getDateOfBirth());
                writer.println(member.getFirstName() + " | " + member.getLastName() + " | " +
                        member.getDocumentNumber() + " | " + dateString + " | " + member.getPersonalId());
            }

            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error saving members to file: " + e.getMessage());
        }
    }
}
