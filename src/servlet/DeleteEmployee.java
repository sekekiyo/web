package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.EmployeeDAO;

/**
 * Servlet implementation class DeleteEmployee
 */
@WebServlet("/DeleteEmployee")
public class DeleteEmployee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteEmployee() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUserId") == null) {
                response.sendRedirect("Login.jsp");
        } else {
            request.setCharacterEncoding("UTF-8");

            String employeeCode = (String) session.getAttribute("employeeCode");

            EmployeeDAO dao = EmployeeDAO.getInstance();

            int count = 0;

            try {
                    dao.dbConnect();
                    dao.createStm();
                    count = dao.deleteEmployee(employeeCode);
                    session.setAttribute("COUNT", count);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dao.dbDiscon();
            }

            if (count != 1) {
                    response.sendRedirect("DeleteErrorEmployee.jsp");
            } else {
                    response.sendRedirect("DeleteCompletion.jsp");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
