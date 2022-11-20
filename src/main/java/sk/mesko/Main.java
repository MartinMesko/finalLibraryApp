package sk.mesko;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Connection spojenie = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/zadanie_eea",
                "root",
                "2030isNow");
             Statement prikaz = spojenie.createStatement()) {
            Scanner scanner = new Scanner(System.in);
            scanner.useLocale(Locale.ENGLISH);
            System.out.println("Zadajte USER_ID: ");
            int userID = scanner.nextInt();
            System.out.println("Zadajte USER_GUID: ");
            String userGuid = scanner.next();
            System.out.println("Zadajte USER_NAME: ");
            String userName = scanner.next();
            MySQL_Insert insert = new MySQL_Insert(userID, userGuid, userName);
            MySQL_Select select = new MySQL_Select(userID, userGuid, userName);
            System.out.println(insert.prikazInsert);
            System.out.println("--------------------------------------------");
            System.out.println(select.prikazSelect);
//            prikaz.executeUpdate(String.valueOf(insert));

            System.out.println("Zaznamy tabulky zadanie_eea su: ");
            int pocetRiadkov = 0;

//            while (vysledky.next())
//            {
//                userID = vysledky.getInt("USER_ID");
//                userGuid = vysledky.getString("USER_GUID");
//                userName = vysledky.getString("USER_NAME");
//
//                System.out.printf("%-50s%8.2f%4d\n", userID, userGuid, userName);
//                pocetRiadkov++;
//            }
            System.out.println("Bolo načítaných " + pocetRiadkov + " záznamov.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}