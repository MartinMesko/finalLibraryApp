package sk.itvkurze.Lekcia_15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TitlesPage {
    private Scanner scanner;

    public TitlesPage(Scanner scanner) { // konstruktor
        this.scanner = scanner;
    }

    public void display() {
        System.out.println("Titles ");
        System.out.println("1 - Show All Titles");
        System.out.println("2 - Add Title");
        System.out.println("3 - Remove Title");
        System.out.println("4 - Back");
        System.out.print("Choose an option: ");
//        Scanner scanner = new Scanner(System.in);
        String input = scanner.next(); // use next() instead of nextInt()
        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid value.");
            display();
            return;
        }
        switch (choice) {
            case 1 -> showAlltitles();
            case 2 -> addTitle();
            case 3 -> deleteTitle();
            case 4 -> goBack();
            default -> {
                System.out.println("Please enter a number in the range from 1 to 4.");
                display();
            }
        }
    }


    List<String> showAlltitles() {
        List<String> result = new ArrayList<>();
        BufferedReader reader = this.getFIle("titles.txt");
        BufferedReader readerDvd = this.getFIle("titlesDVD.txt");
        try {
            if (reader != null && readerDvd != null) {
                result.add("All titles");
                String line = reader.readLine();
                String lineDVD = readerDvd.readLine();
                if (line == null && lineDVD == null) {
                    result.add("No titles available");
                }

                while (line != null) {
                    String[] row = line.split(",");
                    result.add("Name: " + row[0] + " - Author: " + row[1] + " | ISBN: " + row[2] + " | Number of pages: " + row[3] + " | Available copies: " + row[4]);
                    line = reader.readLine();
                }

                while (lineDVD != null) {
                    String[] row = lineDVD.split(",");
                    result.add("Name: " + row[0] + " - Author: " + row[1] + "- Number of chapters: " + row[2] + " - Length in minutes: " + row[3] + " | Available copies: " + row[4]);
                    lineDVD = readerDvd.readLine();
                }

                reader.close();
                readerDvd.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



    private BufferedReader getFIle(String fileName) {
        try {
            return new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    } // pristupuje k textovemu suboru

    private static void addTitle() {
        // TODO: Implementuj metodu pridaj titul
    }

    private static void deleteTitle() {
        // TODO: Implementuje metodu zmazania
    }

    private static void goBack() {
        System.out.println("Going back to main menu...");
    }
}
