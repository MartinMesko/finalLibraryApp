package sk.mesko;

public class MySQL_Insert extends AddNewUser{

    String prikazInsert;

    public MySQL_Insert(int userId, String userGuid, String userName, String prikazInsert) {
        super(userId, userGuid, userName);
        this.prikazInsert = "insert into zadanie_eea value (" + userId + ", " + "'" + userGuid + ", " + "'" + userName + ")";
    }

}
