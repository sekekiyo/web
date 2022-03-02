package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import entity.ViewListDisplay;

public class ViewListDAO {
    //インスタンス化
    private static ViewListDAO instance = new ViewListDAO();
    //データベースとの接続
    private Connection con;
    //結果を返すため
    private Statement stm;
    //従業員一覧を表示するクラスを生成
    private List<ViewListDisplay> list = new LinkedList<ViewListDisplay>();

    private ViewListDAO() {
    }

    //インスタンスを取得
    public static ViewListDAO getInstance() {
        return instance;
    }

    public void dbConnect() throws SQLException {
        ConnectionManager cm = ConnectionManager.getInstance();
        con = cm.connect();
    }
    //結果を返すためのオブジェクトを生成
    public void createStm() throws SQLException {
        stm = con.createStatement();
    }

    public void dbDiscon() {
        try {
                if (stm != null)
                        stm.close();
                if (con != null)
                        con.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //従業員情報を全て取得し、List<ViewListDisplay>で返す
    public List<ViewListDisplay> showAllList() throws SQLException {
        list.clear();
        String sql = "SELECT e.employee_code," + "concat(e.last_name, e.first_name),"
                + "concat(e.last_kana_name, e.first_kana_name),"
                + "e.gender, e.birth_day,"
                + "s.section_name, hire_date "
                + "FROM m_employee e LEFT OUTER JOIN m_section s"
                + "ON e.section_code = s.section_code";
        ResultSet rs = stm.executeQuery(sql);

        while(rs.next()) {
            ViewListDisplay vld = new ViewListDisplay();

            vld.setEmployeeCode(rs.getString(1));
            vld.setEmployeeName(rs.getString(2));
            vld.setEmployeeKanaName(rs.getString(3));
            vld.setGender(rs.getInt(4));
            vld.setBirthDay(rs.getDate(5));
            vld.setSectionName(rs.getString(6));
            vld.setHireDate(rs.getDate(7));

            list.add(vld);
        }

        return list;

    }
}
