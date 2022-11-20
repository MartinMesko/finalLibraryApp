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

                prikaz.executeUpdate(insert.prikazInsert);
                ResultSet vysledky = prikaz.executeQuery(select.prikazSelect);

                while (vysledky.next()) {
                    userID = vysledky.getInt("USER_ID");
                    userGuid = vysledky.getString("USER_GUID");
                    userName = vysledky.getString("USER_NAME");

                    System.out.println(userID + "xxx" + userGuid + "xxx" + userName);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


    }
}