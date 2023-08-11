package sk.itvkurze.Lekcia_21.src;

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

    public void display() {
        System.out.println("Rentals page");
        System.out.println("1 - Rent a title");
        System.out.println("2 - Return a title");
        System.out.println("3 - Prolong the rental");
        System.out.println("4 - Show all rentals");
        System.out.println("5 - Show rentals past due");
        System.out.println("6 - Show Queue");
        System.out.println("7 - Back");
        System.out.print("Choose an option: ");

        String input = scanner.next();
        int choice;

        try {
            choice = Integer.parseInt(input);
            if (choice < 1 || choice > 7) {
                System.out.println("Please enter a number in the range from 1 to 7.");
                display();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid value.");
            display();
            return;
        }

        switch (choice) {
            case 1:
            {
                scanner.nextLine();
                showMembers();
            }
            break;
            case 2:
            {
                scanner.nextLine();
                returnItem();
            }
            break;
            case 3:
            {
                scanner.nextLine();
                prolongRental();
            }
            break;
            case 4:
            {
                scanner.nextLine();
                showAllRentals();
            }
            break;
            case 5:
            {
                scanner.nextLine();
                showRentalsPastDue();
            }
            break;
            case 6:
            {
                scanner.nextLine();
                showQueue();
            }
            break;
            case 7:
            {
                scanner.nextLine();
                LibraryApp.showMainMenu();
            }
            break;
            default:
                display();
        }
    }


    private void showMembers() {
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
            display();
            return;

        }

        // Check if the title is available on stock
        int availableCopies = getAvailableCopies(titleId);
        if (availableCopies == 0) {
            displayQueue(selectedMember,titleId);
            return;
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

    private boolean isTitleRented(int titleId) {
        try (Scanner scanner = new Scanner(new FileReader("rentals.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
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


    public int getAvailableCopies(int titleId) {
        int availableCopies = 0;
        try (Scanner scanner = new Scanner(new FileReader("titles.txt"))) {
            int indexer = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
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


    public boolean isRecentlyRentedByMember(int memberId, int titleId) {
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

    private void addInquiryToQueue(int memberId, int titleId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("queue.txt", true))) {
            String entry = memberId + ";" + formatDate(new Date()) + ";" + titleId + ";0";
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing the inquiry to the queue.");
        }
    }


    private int getTitleRentalDuration(int titleId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("rentals.txt"))) {
            String line;
            int rentalDuration = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int rentedTitleId = Integer.parseInt(parts[1]);
                String rentalDateStr = parts[2];


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


    public Date calculateDueDate(Date currentDate, int rentalDuration) {

        long dueDateTime = currentDate.getTime() + rentalDuration * 24 * 60 * 60 * 1000L;
        return new Date(dueDateTime);
    }

    private void writeRentalEntryToFile(RentalEntry rentalEntry) {
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

    private void returnItem() {
        System.out.println("Return a title");

        display();
    }

    private void prolongRental() {
        System.out.println("Prolong the rental");

        display();
    }

    private void showAllRentals() {
        System.out.println("Show all rentals");

        display();
    }

    private void showRentalsPastDue() {
        System.out.println("Show rentals past due");

        display();
    }

    private void showQueue() {
        System.out.println("Show Queue");

        display();
    }

    private void goBack() {
        System.out.println("Going back...");
    }

    public String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(date);
    }

        private void displayQueue(Member selectedMember, int titleId ) {


        System.out.println("Do you want to add your inquiry to the queue?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        System.out.print("Choose an option: ");
        int inquiryChoice = scanner.nextInt();
        if (inquiryChoice == 1) {
            addInquiryToQueue(selectedMember.getPersonalId(), titleId);
            System.out.println("Your inquiry has been added to the queue.");
        }
        display();
    }
}

//    public InquiryResponse displayQueue(Member selectedMember, int titleId) {
//        System.out.println("Do you want to add your inquiry to the queue?");
//        System.out.println("1 - Yes");
//        System.out.println("2 - No");
//        System.out.print("Choose an option: ");
//        int inquiryChoice = getUserInput();
//
//        if (inquiryChoice == 1) {
//            addInquiryToQueue(selectedMember.getPersonalId(), titleId);
//            return InquiryResponse.ADDED_TO_QUEUE;
//        }
//
//        return InquiryResponse.NOT_ADDED_TO_QUEUE;
//    }
//
//    // Oddelená metóda na získanie vstupu od užívateľa
//    private int getUserInput() {
//        return scanner.nextInt();
//    }
//
//    // Enum pre reprezentovanie možných odpovedí
//    public enum InquiryResponse {
//        ADDED_TO_QUEUE,
//        NOT_ADDED_TO_QUEUE
//    }




