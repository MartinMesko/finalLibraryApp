package sk.itvkurze.Lekcia_16;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TitlesPage {
    private final Scanner scanner;
    private final List<Book> books = new ArrayList<>();
    private final List<DVD> dvds = new ArrayList<>();
    private final String lineSeparator = System.lineSeparator();

    public TitlesPage(Scanner scanner) {
        this.scanner = scanner;
        loadTitles();
    }

    public TitlesPage() {
        this.scanner = new Scanner(System.in);
        loadTitles();
    }

    private void loadTitlesFromFile(String filePath, String type) throws IOException {
        BufferedReader reader = null;
        try {
            File titlesFile = new File(filePath);
            reader = new BufferedReader(new FileReader(titlesFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (type.equals("Book") && parts.length >= 6) {
                    books.add(new Book(parts[0], parts[1], Integer.parseInt(parts[3]), parts[2], Integer.parseInt(parts[4]), Integer.parseInt(parts[5])));
                } else if (type.equals("DVD") && parts.length >= 6) {
                    dvds.add(new DVD(parts[0], parts[1], Integer.parseInt(parts[3]), Integer.parseInt(parts[2]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5])));
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public void loadTitles() {
        try {
            loadTitlesFromFile("titles.txt", "Book");
            loadTitlesFromFile("titlesDVD.txt", "DVD");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public List<DVD> getDVDs() {
        return new ArrayList<>(dvds);
    }

    public void displayTitlesMenu() {
        System.out.println("Titles ");
        System.out.println("1 - Show All Titles");
        System.out.println("2 - Add Title");
        System.out.println("3 - Remove Title");
        System.out.println("4 - Back");
        System.out.print("Choose an option: ");
        String input = scanner.nextLine();

        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid value.");
            displayTitlesMenu();
            return;
        }

        switch (choice) {
            case 1 -> showAllTitles();
            case 2 -> addTitle();
            case 3 -> deleteTitle();
            case 4 -> goBack();
            default -> {
                System.out.println("Please enter a number in the range from 1 to 4.");
                displayTitlesMenu();
            }
        }
    }

    public void showAllTitles() {
        System.out.println("All Titles:");
        for (Book book : books) {
            System.out.println("Name: " + book.getTitle() + " - Author: " + book.getAuthorName() + " | ISBN: " + book.getIsbn() + " | Number of pages: " + book.getPageCount() + " | Available copies: " + book.getAvailableCopies());
        }
        for (DVD dvd : dvds) {
            System.out.println("Name: " + dvd.getTitle() + " - Author: " + dvd.getAuthorName() + " - Number of chapters: " + dvd.getNumberOfTracks() + " - Length in minutes: " + dvd.getDurationInMinutes() + " | Available copies: " + dvd.getAvailableCopies());
        }

        System.out.println(lineSeparator + "Press enter to return to Titles menu...");
        scanner.nextLine();
        displayTitlesMenu();
    }

    public void addTitle() {
        System.out.println("Add a new title");
        System.out.println("1 - Add a book");
        System.out.println("2 - Add a DVD");
        System.out.println("3 - Back");
        System.out.print("Choose an option: ");
        String input = scanner.nextLine();

        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid value.");
            addTitle();
            return;
        }

        switch (choice) {
            case 1 -> addBook();
            case 2 -> addDVD();
            case 3 -> displayTitlesMenu();
            default -> {
                System.out.println("Please enter a number in the range from 1 to 3.");
                addTitle();
            }
        }
    }

    private void addBook() {
        System.out.print("Enter the name of the book: ");
        String name = scanner.nextLine();
        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine();
        System.out.print("Enter the ISBN of the book: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter the number of pages of the book: ");
        int pages = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the number of copies of the book: ");
        int copies = Integer.parseInt(scanner.nextLine());

        boolean result = saveTitle(new Book(author, name, pages, isbn, copies, copies)); // You can modify this if you have a separate variable for total copies
        if (result) {
            System.out.println("The book has been added successfully.");
        } else {
            System.out.println("Failed to add the book.");
        }
    }

    private void addDVD() {
        System.out.print("Enter the name of the DVD: ");
        String name = scanner.nextLine();
        System.out.print("Enter the author of the DVD: ");
        String author = scanner.nextLine();
        System.out.print("Enter the number of chapters of the DVD: ");
        int chapters = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the length in minutes of the DVD: ");
        int length = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the number of copies of the DVD: ");
        int copies = Integer.parseInt(scanner.nextLine());

        boolean result = saveTitle(new DVD(author, name, length, chapters, copies, copies)); // You can modify this if you have a separate variable for total copies
        if (result) {
            System.out.println("The DVD has been added successfully.");
        } else {
            System.out.println("Failed to add the DVD.");
        }
    }

    boolean saveTitle(Object title) {
        try {
            String fileName;
            String titleString;

            if (title instanceof Book) {
                Book book = (Book) title;
                fileName = "titles.txt";
                titleString = book.getAuthorName() + "," + book.getTitle() + "," + book.getIsbn() + "," + book.getPageCount() + "," + book.getAvailableCopies() + "," + book.getTotalCopies();
                books.add(book);
            } else {
                DVD dvd = (DVD) title;
                fileName = "titlesDVD.txt";
                titleString = dvd.getAuthorName() + "," + dvd.getTitle() + "," + dvd.getNumberOfTracks() + "," + dvd.getDurationInMinutes() + "," + dvd.getAvailableCopies() + "," + dvd.getTotalCopies();
                dvds.add(dvd);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                writer.write(titleString + lineSeparator);
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

//    private void deleteTitle() {
//        System.out.println("Remove a title");
//        System.out.print("Enter the name of the title: ");
//        String name = scanner.nextLine();
//
//        boolean found = false;
//        for (String title : titles) {
//            String[] row = title.split(",");
//            if (row.length >= 5 && row[0].equals(name)) {
//                titles.remove(title);
//                found = true;
//                break;
//            }
//        }
//        if (!found) {
//            for (String title : titlesDVD) {
//                String[] row = title.split(",");
//                if (row.length >= 5 && row[0].equals(name)) {
//                    titlesDVD.remove(title);
//                    found = true;
//                    break;
//                }
//            }
//        }
//
//        if (found) {
//            System.out.println("The title has been removed successfully.");
//        } else {
//            System.out.println("The title has not been found.");
//        }
//        System.out.println(lineSeparator + "Press enter to return to Titles menu...");
//        scanner.nextLine();  // Počká sa, kým používateľ stlačí kláves enter
//        displayTitlesMenu();  // Zobrazíme menu s názvami
//    }

    private void deleteTitle() {
        //TODO: implement
    }

    private void goBack() {
        System.out.println("Going back to main menu...");
    }


}
