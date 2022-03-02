package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Employee;
import entity.WorkTime;
import models.EmployeeDAO;
import models.WorkTimeDAO;

/**
 * Servlet implementation class AttendanceSelectTimesheet
 */
@WebServlet("/AttendanceSelectTimesheet")
public class AttendanceSelectTimesheet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public AttendanceSelectTimesheet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("employeeCode") == null) {
            response.sendRedirect("AttendanceLogin.jsp");
    }  else {
            response.sendRedirect("AttendanceMenu.jsp");
    }
}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String employeeCode = (String) session.getAttribute("employeeCode");
        String thisMonth = request.getParameter("thisMonth");
        Calendar thisMonthCalendar = Calendar.getInstance();
        thisMonthCalendar.set(Calendar.YEAR, Integer.parseInt(thisMonth.substring(0, 4)));
        thisMonthCalendar.set(Calendar.MONTH, Integer.parseInt(thisMonth.substring(5)));

        WorkTimeDAO workTimeDao = WorkTimeDAO.getInstance();
        EmployeeDAO empdao = EmployeeDAO.getInstance();
        try {
            workTimeDao.dbConnct();
            workTimeDao.createStm();
            List<WorkTime> workTimeThisMonthList = workTimeDao.selectWorkTimeThisMonthList(employeeCode, thisMonth);
            session.setAttribute("workTimeThisMonthList", workTimeThisMonthList);
            empdao.dbConnect();
            empdao.createStm();
            Employee employee = empdao.selectEmployee(employeeCode);
            String employeeName = employee.getLastName() + " " + employee.getFirstName();
            session.setAttribute("employeeName", employeeName);
        } catch (SQLException e) {
                e.printStackTrace();
        }

        session.setAttribute("thisMonth", thisMonthCalendar);
        response.sendRedirect("AttendanceViewTimesheet.jsp");
    }

}
