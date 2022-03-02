package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AttendanceEmployeeDAO {

    /**インスタンスの生成*/
    public static AttendanceEmployeeDAO instance = new AttendanceEmployeeDAO();

    /**データベースと接続*/
    private Connection con;

    /**結果を繰り返すため*/
    private Statement stm;

    /**時間を出力するため*/
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**日付を出力するため*/
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private AttendanceEmployeeDAO() {
    }

    public static AttendanceEmployeeDAO getInstance() {
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
    /**従業員コードとそれに対応するパスワード
     * 一致していなかったらnullに返す
     */
    public String loginEmployee(String employeeCode, String password) throws SQLException {
        String sql = "select * from m_employee where employee_code'" + employeeCode
                + "' and password='" + password + "';";
        ResultSet rs = stm.executeQuery(sql);
        if (rs.next()) {
            if (employeeCode.equals(rs.getString(1))) {
                if (password.equals(rs.getString(11))) {
                    return employeeCode;
                }
            }
        }
        return null;
    }

    public boolean setStartTime(String employeeCode) throws SQLException {
        con.setAutoCommit(false);
        LocalDateTime now = LocalDateTime.now();
        String sql = "SELECT * from t_work_time WHERE employee_code = '" + employeeCode
                 + "' and work_date = '" + now.format(dateFormat) + "';";
        ResultSet rs = stm.executeQuery(sql);
        if(rs.next()) {
            return false;
        } else {
            sql = "INSERT INTO t_work_time (employee_code, work_date, start_time) VAlUES ('"+ employeeCode
            + "' '" + now.format(dateFormat) + "', '" + now.format(timeFormat) + "');";
            stm.executeUpdate(sql);
            con.commit();
            return true;
        }
    }

    public boolean setFinishTime(String employeeCode) throws SQLException {
        con.setAutoCommit(false);
        LocalDateTime now = LocalDateTime.now();
        String sql = "SELECT * from t_work_time WHERE employee_code = '" + employeeCode
                + "' and work_date = '" + now.format(dateFormat) + "';";
        ResultSet rs = stm.executeQuery(sql);
        if(!rs.next()) {
            return false;
        } else {
            sql = "UPDATE t_work_time SET finish_time = '" + now.format(timeFormat)
            + "' WHERE employee_code = '" + employeeCode + "' and work_date = '" +
                    now.format(dateFormat) + "';";
            stm.executeUpdate(sql);
            con.commit();
            return true;
        }

    }
    public boolean setStartBreakTime(String employeeCode) throws SQLException {
        con.setAutoCommit(false);
        LocalDateTime now = LocalDateTime.now();
        String sql = "SELECT * from t_work_time WHERE employee_code = '" + employeeCode
                + "' and work_date = '" + now.format(dateFormat) + "';";
        ResultSet rs = stm.executeQuery(sql);
        if(!rs.next()) {
            return false;
        } else {
            sql = "UPDATE t_work_time SEt break_start_time = '" + now.format(timeFormat)
            + "' WHERE employee_code = '" + employeeCode + "' and work_date = '" +
                    now.format(dateFormat) + "';";
            stm.executeUpdate(sql);
            con.commit();
            return true;
        }
    }

        public boolean setFinishBreakTime(String employeeCode) throws SQLException {
            con.setAutoCommit(false);
            LocalDateTime now = LocalDateTime.now();
            String sql = "SELECT * from t_work_time WHERE employee_code = '" + employeeCode
                    + "' and work_date = '" + now.format(dateFormat) + "';";
            ResultSet rs = stm.executeQuery(sql);
            if(!rs.next()) {
                return false;
            } else {
                sql = "UPDATE t_work_time SET break_finish_time = '" + now.format(timeFormat)
                + "' WHERE employee_code = '" + employeeCode + "' and work_date = '" + now.format(dateFormat)
                + "';";
                stm.executeUpdate(sql);
                con.commit();
                return true;
            }
        }

}
