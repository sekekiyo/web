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
 * Servlet implementation class AttendanceTimeCard
 */
@WebServlet("/AttendanceTimeCard")
public class AttendanceTimeCard extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("employeeCode") == null) {
            response.sendRedirect("AttendanceLogin.jsp");
        } else {
            response.sendRedirect("AttendanceMenu.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        String employeeCode = (String) session.getAttribute("employeeCode");
        String attendance = request.getParameter("attendance");
        AttendanceEmployeeDAO attendEmpDao = AttendanceEmployeeDAO.getInstance();

        boolean Flag = false;
        try {
            attendEmpDao.dbConnect();
            attendEmpDao.createStm();
            if (attendance.equals("出勤処理")) {
                    Flag = attendEmpDao.setStartTime(employeeCode);
            } else if (attendance.equals("退勤処理")) {
                    Flag = attendEmpDao.setFinishTime(employeeCode);
            } else if (attendance.equals("休憩開始処理")) {
                    Flag = attendEmpDao.setStartBreakTime(employeeCode);
            } else if (attendance.equals("休憩終了処理")) {
                    Flag = attendEmpDao.setFinishBreakTime(employeeCode);
            }
            } catch (SQLException e) {
                    e.printStackTrace();
            } finally {
                    attendEmpDao.dbDiscon();
            }
        if (Flag) {
                session.setAttribute("attendance", attendance);
                response.sendRedirect("AttendanceCompletion.jsp");
        } else {
                response.sendRedirect("AttendanceTimecardError.jsp");
        }

    }

}
