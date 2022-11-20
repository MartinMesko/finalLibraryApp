package sk.mesko;

import java.util.Scanner;

public class MySQL_Delete extends NewUser{

    String prikazDelete;

    public MySQL_Delete(int userId, String userGuid, String userName) {
        super(userId, userGuid, userName);
        this.prikazDelete = "delete from SUSERS where USER_ID >= 1";


    }

    public boolean jeVymaz()
    {
        Scanner scanner = new Scanner(System.in);
        String vymaz = scanner.next();
        return vymaz.equals("vymaž");
    }


}
