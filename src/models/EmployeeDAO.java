package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import entity.Employee;
import entity.Section;

public class EmployeeDAO {

    private static EmployeeDAO instance = new EmployeeDAO();

    private Connection con;

    private Statement stm;

    private EmployeeDAO() {
    }

    public static EmployeeDAO getInstance() {
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


    public boolean insertEmployee(String lastName, String firstName, String lastKanaName, String firstKanaName,
            int gender, String birthDay, String sectionCode, String hireDate, String password) throws SQLException {
        con.setAutoCommit(false);

        String sql = "SELECT MAX(employee_code) FROM m_employee;";
        ResultSet rs = stm.executeQuery(sql);
        int code = 0;
        String employeeCode;

        if(rs.next()) {
            code = Integer.parseInt(rs.getString(1).substring(1)) + 1;
        }

        employeeCode = "E" + String.format("%04d", code);

        boolean flag = false;
        String sql2 = "insert into m_employee values('"+ employeeCode + "', '" + lastName + "', + '" + lastKanaName +
                "', " + "'" + firstKanaName + "','" + gender + "', '" + birthDay + "', '" + sectionCode + "', '" +
                hireDate + "', null, '" + password + "');";

        int result = stm.executeUpdate(sql2);

        if (result > 0) {
            flag = true;
            con.commit();
        }
        return flag;

    }

    public Employee updateEmployee(Employee employee) throws SQLException {
        con.setAutoCommit(false);

        String sql = "UPDATE m_employee SET last_name = '" + employee.getLastName()
        + "', first_name = '" + employee.getFirstName()
        + "', last_kana_name = '" + employee.getLastKanaName()
        + "', first_kana_name = '" + employee.getFirstKanaName()
        + "', gender = '" + employee.getGenter()
        + "', birth_day = '" + employee.getBirthDay()
        + "', section_code = '" + employee.getSectionCode()
        + "', hire_date = '" + employee.getHireDate()
        + "', WHERE employee_code = '" + employee.getEmployeeCode() + "';";

        int count = stm.executeUpdate(sql);

        if (count > 0) {
            con.commit();
        }

        return employee;
    }


    public Employee selectEmployee(String employeeCode) throws SQLException {
        String sql = "SELECT * m_employee WHERE employee_code = '" + employeeCode + "';";

        ResultSet rs = stm.executeQuery(sql);

        Employee employee = null;

        if (rs.next() && rs.getString(1).equals(employeeCode)) {
            employee = new Employee();
            employee.setEmployeeCode(rs.getString(1));
            employee.setLastName(rs.getString(2));
            employee.setFirstName(rs.getString(3));
            employee.setLastKanaName(rs.getString(4));
            employee.setFirstKanaName(rs.getString(5));
            employee.setGender(rs.getString(6));
            employee.setBirthDay(rs.getString(7));
            employee.setSectionCode(rs.getString(8));
            employee.setHireDate(rs.getString(9));

        }

        return employee;
    }

    public int deleteEmployee(String employeeCode) throws SQLException {
        con.setAutoCommit(false);
        String sql = "DELETE FROM m_employee WHERE employee_code = '" + employeeCode + "';";

        int count = stm.executeUpdate(sql);

        if (count > 0) {
            con.commit();
        }

        return count;

    }

    public List<Section> getSection() throws SQLException {
        String sql = "SELECT * FROM m_section ORDER BY section_code;";
        List<Section> sections = new LinkedList<Section>();
        ResultSet rs = stm.executeQuery(sql);

        while(rs.next()) {
            Section sec = new Section();
            sec.setSectionCode(rs.getString(1));
            sec.setSectionName(rs.getString(2));
            sections.add(sec);

        }

        return sections;
    }




}
