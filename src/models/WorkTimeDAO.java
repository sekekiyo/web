package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import entity.WorkTime;

public class WorkTimeDAO {

    private static WorkTimeDAO instance = new WorkTimeDAO();

    private Connection con;

    private Statement stm;

    private WorkTimeDAO() {
    }

    public static WorkTimeDAO getInstance() {
        return instance;
    }

    public void dbConnct() throws SQLException {
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

    public String selectStartTime(String employeeCode) throws SQLException {
        String sql = "SELECT * FROM t_work_time WHERE employee_code = '"
                    + employeeCode + "' AND work_date = '" + LocalDate.now() + "';";
        ResultSet rs = stm.executeQuery(sql);
        if (rs.next() && rs.getString(4) != null) {
                return "disable";
        } else {
            return null;
        }
    }

    public String selectFinishTime(String employeeCode) throws SQLException {
        String sql = "SELECT * FROM t_work_time WHERE employee_code = '"
                    + employeeCode + "'AND work_date = '" + LocalDate.now() + "';";
        ResultSet rs = stm.executeQuery(sql);
        if (rs.next() && rs.getString(4) != null) {
                return "disable";
        } else {
                return null;
        }
    }

    public String selectStartBreak(String employeeCode) throws SQLException {
        String sql = "SELECT * FROM t_work_time WHERE employee_code = '"
                    + employeeCode + "' AND work_date = '" + LocalDate.now() + "';";
        ResultSet rs = stm.executeQuery(sql);
        if (rs.next() && rs.getString(5) != null) {
                return "disable";
        } else {
                return null;
        }
    }

    public String selectFinishBreak(String employeeCode) throws SQLException {
        String sql = "SELECT * FROM t_work_time WHERE employee_code = '"
                + employeeCode + "' AND work_date = '" + LocalDate.now() + "';";
        ResultSet rs = stm.executeQuery(sql);
        if (rs.next() && rs.getString(5) != null) {
            return "disable";
        } else {
            return null;
        }
    }


    public List<WorkTime> selectWorkTimeThisMonthList(String employeeCode,String thisMonth) throws SQLException {
        List<WorkTime> workTimeThisMonthList = new LinkedList<WorkTime>();
        String sql = "SELECT * FROM t_work_time WHERE employee_code = '" + employeeCode
                    + "' AND work_date LIKE '" + thisMonth + "%';";
        ResultSet rs = stm.executeQuery(sql);

        while(rs.next()) {
            WorkTime workTime = new WorkTime();
            workTime.setWorkDate(LocalDate.parse(rs.getString(2),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")) );
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            if (rs.getString(3) != null) {
                LocalTime startTime = LocalTime.parse(rs.getString(3), dtf);
                workTime.setStartTime(startTime);
            }
            if (rs.getString(4) != null) {
                LocalTime finishTime = LocalTime.parse(rs.getString(4), dtf);
                workTime.setFinishTime(finishTime);
            }
            if (rs.getString(5) != null) {
                LocalTime breakStartTime = LocalTime.parse(rs.getString(5), dtf);
                workTime.setBreakStartTime(breakStartTime);
            }
            if (rs.getString(6) != null) {
                LocalTime breakFinishTime = LocalTime.parse(rs.getString(6), dtf);
                workTime.setBreakFinishTime(breakFinishTime);
            }
            if (rs.getString(5) != null && rs.getString(6) != null) {
                //自動計算メソッド
                workTime.calcBreakTime();
            }
            if (rs.getString(3) != null && rs.getString(4) != null) {
                //自動計算メソッド
                workTime.calcWorkingHours();
            }
            workTimeThisMonthList.add(workTime);
        }
        return workTimeThisMonthList;
        }

    }


