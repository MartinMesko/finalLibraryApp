package sk.mesko;

import java.sql.*;

public class MySQL_Select extends AddNewUser{
    String prikazSelect;


    public MySQL_Select(int userId, String userGuid, String userName) {
        super(userId, userGuid, userName);
        this.prikazSelect = "select " + userId + " " + userGuid + " " + userName + " from zadanie_eea";
    }

}
