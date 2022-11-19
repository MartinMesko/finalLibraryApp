package sk.mesko;

public class AddNewUser  {
    int userId;
    String userName, userGuid;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public AddNewUser(int userId, String userGuid, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.userGuid = userGuid;
    }
}






