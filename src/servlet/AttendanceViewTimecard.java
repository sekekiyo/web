package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.WorkTimeDAO;

/**
 * Servlet implementation class AttendanceViewTimecard
 */
@WebServlet("/AttendanceViewTimecard")
public class AttendanceViewTimecard extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AttendanceViewTimecard() {
       }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String employeeCode = (String) session.getAttribute("employeeCode");
        WorkTimeDAO workTimeDao = WorkTimeDAO.getInstance();

        try {
            workTimeDao.dbConnct();
            workTimeDao.createStm();
            String startCheck = workTimeDao.selectStartTime(employeeCode);
            session.setAttribute("startWork", startCheck);

            if (startCheck != null) {
                    String finishCheck = workTimeDao.selectFinishTime(employeeCode);
                    session.setAttribute("finishWork", finishCheck);

                    if (finishCheck == null) {
                            String startBreakCheck = workTimeDao.selectStartBreak(employeeCode);
                            session.setAttribute("startBreak", startBreakCheck);

                            if (startBreakCheck != null) {
                                    String finishBreakCheck = workTimeDao.selectFinishBreak(employeeCode);
                                    session.setAttribute("finishBreak", finishBreakCheck);
                            }
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            workTimeDao.dbDiscon();
        }
        response.sendRedirect("AttendanceTimecard.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
