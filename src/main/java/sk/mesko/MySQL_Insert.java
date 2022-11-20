package sk.mesko;

public class MySQL_Insert extends NewUser {

    String prikazInsert;

    public MySQL_Insert(int userId, String userGuid, String userName) {
        super(userId, userGuid, userName);
        this.prikazInsert = "insert into SUSERS value (" + userId + ", " + "'" + userGuid + "', " + "'" + userName + "')";
    }

}
