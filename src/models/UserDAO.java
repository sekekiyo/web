package models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    private static UserDAO instance = new UserDAO();

    private Connection con;

    private Statement stm;

    private UserDAO() {

    }

    public static UserDAO getInstance() {
        return instance;
    }


    public void dbConnect() throws SQLException {
        ConnectionManager cm = ConnectionManager.getInstance();
        con = cm.connect();
    }

    public void createStm() throws SQLException {
        stm = con.createStatement();
    }

    public void dbDiscon() {
        try {
            if (stm != null)
                    stm.close();
            if (con != null)
                    con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean loginUser(String userId, String password) throws SQLException, NoSuchAlgorithmException {

        boolean loginUserChkFlag = false;

        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] passwordDigest =digest.digest(password.getBytes());
        String sha1 = String.format("%040", new BigInteger(1,passwordDigest));

        String sql = "select * from m_user where user_id='" + userId + "' and password='" + sha1.substring(8) + "';";

        ResultSet rs = stm.executeQuery(sql);

        if (rs.next()) {
                if (userId.equals(rs.getString(1))) {
                        if (sha1.substring(8).equals(rs.getString(2))) {
                            loginUserChkFlag = true;
                        }
                }
        }
        return loginUserChkFlag;
    }


    public boolean insertUser(String userId, String password) throws SQLException, NoSuchAlgorithmException {

        con.setAutoCommit(false);

        boolean insertUserChkFlag =false;


        String sql = "select * from m_user where user_id= '" + userId + "';";


        ResultSet rs = stm.executeQuery(sql);

        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] passwordDigest = digest.digest(password.getBytes());
        String sha1 = String.format("%040x", new BigInteger(1, passwordDigest));

        if (!rs.next() || !userId.equals(rs.getString(1))) {
                sql = "insert into m_user values('" + userId + "', '" + sha1.substring(8) + "', null);";
                int result = stm.executeUpdate(sql);

                if (result > 0) {
                        insertUserChkFlag = true;
                        con.commit();
                }
        }

        return insertUserChkFlag;
    }



}
