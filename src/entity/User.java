package entity;

public class User {
    /**ID*/
    private String userId;
    /**パスワード*/
    private String password;
    /**コンストラクタ*/
    public User() {
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
