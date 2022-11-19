package sk.mesko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL_Select {

    Connection spojenie;

    {
        try {
            spojenie = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/zadanie_eea",
                    "root",
                    "2030isNow");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
