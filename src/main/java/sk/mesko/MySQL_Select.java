package sk.mesko;

public class MySQL_Select extends NewUser {
    String prikazSelect;


    public MySQL_Select(int userId, String userGuid, String userName) {
        super(userId, userGuid, userName);
        this.prikazSelect = "select * from SUSERS";
    }

}
