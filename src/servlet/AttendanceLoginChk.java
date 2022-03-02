package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.AttendanceEmployeeDAO;

/**
 * Servlet implementation class AttendanceLoginChk
 */
@WebServlet("/AttendanceLoginChk")
public class AttendanceLoginChk extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendanceLoginChk() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String employeeCode = (String) session.getAttribute("employeeCode");
        if (employeeCode == null) {
                response.sendRedirect("AttendanceLogin.jsp");
        } else {
                response.sendRedirect("AttendanceMenu.jsp");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String employeeCode = request.getParameter("employeeCode");
        String password = request.getParameter("password");

        AttendanceEmployeeDAO attendEmpDao = AttendanceEmployeeDAO.getInstance();

        try {
                attendEmpDao.dbConnect();
                attendEmpDao.createStm();
                employeeCode = attendEmpDao.loginEmployee(employeeCode, password);
                session.setAttribute("employeeCode", employeeCode);
        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
                attendEmpDao.dbDiscon();
        }

        if (employeeCode != null) {
                response.sendRedirect("AttendanceMenu.jsp");
        } else {
                response.sendRedirect("AttendanceLogin.jsp");
        }
    }

}
