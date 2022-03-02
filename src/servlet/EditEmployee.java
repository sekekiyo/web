package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Employee;
import models.EmployeeDAO;

/**
 * Servlet implementation class EditEmployee
 */
@WebServlet("/EditEmployee")
public class EditEmployee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditEmployee() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUserId") == null) {
            response.sendRedirect("Login.jsp");
        } else {
            response.sendRedirect("Menu.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");

        request.setCharacterEncoding("UTF-8");
        String employeeCode = request.getParameter("employeeCode");
        employee.setEmployeeCode(employeeCode);

        String lastName = request.getParameter("lastName");
        employee.setLastName(lastName);

        String lastKanaName = request.getParameter("lastKanaName");
        employee.setLastKanaName(lastKanaName);

        String firstName = request.getParameter("firstName");
        employee.setFirstName(firstName);

        String firstKanaName = request.getParameter("firstKanaName");
        employee.setFirstKanaName(firstKanaName);

        int gender = Integer.parseInt(request.getParameter("gender"));
        employee.setGender(gender);

        String birthDay = request.getParameter("birthDay");
        employee.setBirthDay(birthDay);

        String hireDate = request.getParameter("hireDate");
        employee.setHireDate(hireDate);

        String sectionCode = request.getParameter("section_code");
        employee.setSectionCode(sectionCode);

        EmployeeDAO empdao = EmployeeDAO.getInstance();
        try {
            empdao.dbConnect();
            empdao.createStm();
            employee = empdao.updateEmployee(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            empdao.dbDiscon();
        }

        session.removeAttribute("employeeCode");
        session.setAttribute("employee", employee);
        response.sendRedirect("EditCompletion.jsp");
    }

}
