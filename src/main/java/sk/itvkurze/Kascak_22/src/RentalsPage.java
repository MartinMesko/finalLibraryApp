package sk.itvkurze.Kascak_22.src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RentalsPage {
    private final Scanner scanner;

    public RentalsPage(Scanner scanner) {
        this.scanner = scanner;
    }

    public static void display() {
        System.out.println("Rentals page");
        System.out.println("1 - Rent a title");
        System.out.println("2 - Return a title");
        System.out.println("3 - Prolong the rental");
        System.out.println("4 - Show all rentals");
        System.out.println("5 - Show rentals past due");
        System.out.println("6 - Show Queue");
        System.out.println("7 - Back");
        System.out.print("Choose an option: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                showMembers();
                break;
            case 2:
                returnItem();
                break;
            case 3:
                prolongRental();
                break;
            case 4:
                showAllRentals();
                break;
            case 5:
                showRentalsPastDue();
                break;
            case 6:
                showQueue();
                break;
            case 7:
                goBack();
                break;
            default:
                System.out.println("Please enter a number in the range from 1 to 7.");
                display();
        }
    }

    private static void showMembers() {
        List<Member> members = MembersPage.readMembersFromFile();

        if (members.isEmpty()) {
            System.out.println("List of all library members:");
            System.out.println("Member list is empty.");
            display();
            return;
        }

        System.out.println("List of all library members:");
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            System.out.println((i + 1) + " - " + member.toString());
        }

        System.out.print("Choose a member to rent to: ");
        Scanner scanner = new Scanner(System.in);
        int memberChoice = scanner.nextInt();

        // Validate member choice
        if (memberChoice < 1 || memberChoice > members.size()) {
            System.out.println("Invalid member choice.");
            display();
            return;
        }

        // Get selected member
        Member selectedMember = members.get(memberChoice - 1);

        // Check if the selected member has already rented the maximum number of titles
        if (selectedMember.getRentedTitles().size() >= 2) {
            System.out.println("The selected member has already rented the maximum number of titles.");
            display();
            return;
        }

        // Display available titles
        TitlesPage.showAlltitles();

        // Validate title choice
        TitlesPage titlesPage = new TitlesPage(scanner);
        int titleId = titlesPage.getId();

        // Check if the title is already rented
        if (isTitleRented(titleId)) {
            System.out.println("The selected title is already rented.");

        }

        // Check if the title is available on stock
        int availableCopies = getAvailableCopies(titleId);
        if (availableCopies == 0) {
            displayQueue(selectedMember,titleId);
        }

        // Check if the member recently rented the same title
        if (isRecentlyRentedByMember(selectedMember.getPersonalId(), titleId)) {
            System.out.println("The selected member recently rented the same title and has not returned it yet.");
            display();
            return;
        }

        // Get the rental duration based on the title type
        int rentalDuration = getTitleRentalDuration(titleId);

        // Calculate due date
        Date currentDate = new Date();
        Date dueDate = calculateDueDate(currentDate, rentalDuration);

        // Store the rented title in a suitable data structure for connecting with an SQL database
        RentalEntry rentalEntry = new RentalEntry(
                String.valueOf(selectedMember.getPersonalId()),
                String.valueOf(titleId),
                formatDate(currentDate),
                formatDate(dueDate)
        );
        writeRentalEntryToFile(rentalEntry);

        System.out.println("Title rented successfully.");

        display();
    }

    private static boolean isTitleRented(int titleId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("rentals.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int rentedTitleId = Integer.parseInt(parts[1]);
                if (rentedTitleId == titleId) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static int getAvailableCopies(int titleId) {
        int availableCopies = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("titles.txt"))) {
            String line;
            int indexer = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = indexer;
                if (id == titleId) {
                    availableCopies = Integer.parseInt(parts[4]);
                    break;
                }
                indexer++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return availableCopies;
    }

    public static boolean isRecentlyRentedByMember(int memberId, int titleId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("rentals.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int rentedTitleId = Integer.parseInt(parts[1]);
                int rentedMemberId = Integer.parseInt(parts[0]);
                if (rentedTitleId == titleId && rentedMemberId == memberId) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void addInquiryToQueue(int memberId, int titleId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("queue.txt", true))) {
            String entry = memberId + ";" + formatDate(new Date()) + ";" + titleId + ";0";
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing the inquiry to the queue.");
        }
    }


    private static int getTitleRentalDuration(int titleId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("rentals.txt"))) {
            String line;
            int rentalDuration = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int rentedTitleId = Integer.parseInt(parts[1]);
                String rentalDateStr = parts[2];

                // Check if the line matches the given titleId
                if (rentedTitleId == titleId) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date rentalDate = dateFormat.parse(rentalDateStr);
                    Date currentDate = new Date();
                    long rentalDurationMillis = currentDate.getTime() - rentalDate.getTime();
                    rentalDuration = (int) (rentalDurationMillis / (24 * 60 * 60 * 1000));
                    break;
                }
            }
            return rentalDuration;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    private static Date calculateDueDate(Date currentDate, int rentalDuration) {
        // Calculate the due date based on the current date and rental duration
        long dueDateTime = currentDate.getTime() + rentalDuration * 24 * 60 * 60 * 1000L;
        return new Date(dueDateTime);
    }

    private static void writeRentalEntryToFile(RentalEntry rentalEntry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rentals.txt", true))) {
            String entry = rentalEntry.getMemberId() + ";" +
                    rentalEntry.getTitleId() + ";" +
                    rentalEntry.getRentalDate() + ";" +
                    rentalEntry.getDueDate();

            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing the rental entry to the file.");
        }
    }

    private static void returnItem() {
        System.out.println("Return a title");
        // Code for returning a title
        display();
    }

    private static void prolongRental() {
        System.out.println("Prolong the rental");
        // Code for prolonging a rental
        display();
    }

    private static void showAllRentals() {
        System.out.println("Show all rentals");
        // Code to display all rentals
        display();
    }

    private static void showRentalsPastDue() {
        System.out.println("Show rentals past due");
        // Code to display rentals past due
        display();
    }

    private static void showQueue() {
        System.out.println("Show Queue");
        // Code to display the queue
        display();
    }

    private static void goBack() {
        System.out.println("Going back...");
    }

    private static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(date);
    }

    private static void displayQueue(Member selectedMember, int titleId ) {

        System.out.println("The selected title is not available on stock.");
        System.out.println("Do you want to add your inquiry to the queue?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        System.out.print("Choose an option: ");
        int inquiryChoice = new Scanner(System.in).nextInt();
        if (inquiryChoice == 1) {
            addInquiryToQueue(selectedMember.getPersonalId(), titleId);
            System.out.println("Your inquiry has been added to the queue.");
        }
        display();


    }
}
