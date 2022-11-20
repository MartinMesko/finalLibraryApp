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

                String vymaz = "";

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
                MySQL_Delete delete = new MySQL_Delete(userID, userGuid, userName);

                prikaz.executeUpdate(insert.prikazInsert);
                ResultSet vysledky = prikaz.executeQuery(select.prikazSelect);

                while (vysledky.next()) {
                    userID = vysledky.getInt("USER_ID");
                    userGuid = vysledky.getString("USER_GUID");
                    userName = vysledky.getString("USER_NAME");

                    System.out.println("USER_ID       USER_GUID        USER_NAME");
                    System.out.println("  " + userID + "             " + userGuid + "                " + userName);
                    System.out.println();
                    System.out.println("Pokiaľ chcete vymazať všetky položky z databázy napíšte na klávesnici ,,vymaž,,\n" +
                            "Pokiaľ si prajete záznamy ponechať, stlačte akúkoľvek klávesu");

                }

                while (delete.jeVymaz())
                {
                    prikaz.executeUpdate(delete.prikazDelete);
                    break;
                }


//                while (true) {
//                    vymaz = scanner.next();
//
//                    if (vymaz.equals("vymaž")) {
//                        prikaz.executeUpdate(delete.prikazDelete);
//                    }
//                    break;
//                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }
}