package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Employee;
import entity.Section;
import models.EmployeeDAO;

/**
 * Servlet implementation class EditCheckEmployee
 */
@WebServlet("/EditCheckEmployee")
public class EditCheckEmployee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditCheckEmployee() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUserId") == null) {
            response.sendRedirect("Login.jsp");
        } else {
            request.setCharacterEncoding("UTF-8");
            String employeeCode = (String) session.getAttribute("employeeCode");
            EmployeeDAO empdao = EmployeeDAO.getInstance();
            Employee employee = null;
            List<Section> sections = new LinkedList<Section>();

            if (employeeCode != null) {
                try {
                    empdao.dbConnect();
                    empdao.createStm();
                    employee = empdao.selectEmployee(employeeCode);
                    sections = empdao.getSection();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    empdao.dbDiscon();
                }

                session.setAttribute("employee", employee);
                session.setAttribute("sections", sections);

                if (employee == null) {
                        response.sendRedirect("Menu.jsp");
                } else {
                        response.sendRedirect("EditEmployee.jsp");
                }
            } else {
                response.sendRedirect("ShowAllEmployee.jsp");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
