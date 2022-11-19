package sk.mesko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL_Select {
    {
        try (Connection spojenie = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/zadanie_eea",
                "root",
                "2030isNow");
             Statement prikaz = spojenie.createStatement()) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
