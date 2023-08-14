package sk.itvkurze.Lekcia_15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TitlesPage {
    private Scanner scanner;
    private final String lineSeparator = System.lineSeparator();

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
        String input = scanner.next(); // Použi next() namiesto nextInt().
        // Keď používateľ zadá neplatný vstup (napr. písmeno alebo slovo, keď očakávate číslo), metóda nextInt() vyvolá výnimku InputMismatchException.
        // Ak sa  použije next(), program číta vstup ako reťazec a môže sa potom skúsiť konvertovať tento reťazec na číslo pomocou Integer.parseInt(input).
        // Ak sa konverzia nepodarí (pretože vstup nie je číslo), môže sa chyba ošetriť pomocou výnimky NumberFormatException.

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
        try (BufferedReader reader = this.getFIle("titles.txt");
             BufferedReader readerDvd = this.getFIle("titlesDVD.txt")) {

            String line = (reader != null) ? reader.readLine() : null;
            String lineDVD = (readerDvd != null) ? readerDvd.readLine() : null;

            if (line == null && lineDVD == null) {
                result.add("No titles available");
                return result;
            }

            result.add("All Titles");

            // Spracovanie kníh
            while (line != null) {
                String[] row = line.split(",");
                result.add("Name: " + row[0] + " - Author: " + row[1] + " | ISBN: " + row[2] + " | Number of pages: " + row[3] + " | Available copies: " + row[4]);
                line = reader.readLine();
            }

            // Spracovanie DVD
            while (lineDVD != null) {
                String[] row = lineDVD.split(",");
                result.add("Name: " + row[0] + " - Author: " + row[1] + " - Number of chapters: " + row[2] + " - Length in minutes: " + row[3] + " | Available copies: " + row[4]);
                lineDVD = readerDvd.readLine();
            }

            // Výpis titulov
            for (String title : result) {
                System.out.println(title);
            }

            System.out.print(lineSeparator + "Press enter to return to Titles menu...");
            scanner.nextLine();  // To consume the \n character from before
            scanner.nextLine();  // To wait for the user to press Enter
            display();  // Return to Titles menu

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
