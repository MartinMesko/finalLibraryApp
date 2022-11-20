package sk.mesko;

public class MySQL_Delete extends NewUser{

    String prikazDelete;

    public MySQL_Delete(int userId, String userGuid, String userName) {
        super(userId, userGuid, userName);
        this.prikazDelete = "delete from SUSERS where USER_ID >= 1";
    }

}
