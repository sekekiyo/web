package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.EmployeeDAO;

/**
 * Servlet implementation class RegistEmployee
 */
@WebServlet("/RegistEmployee")
public class RegistEmployee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegistEmployee() {
        super();
       }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUserId") == null) {
                response.sendRedirect("Login.jsp");
        } else {
            response.sendRedirect("Menu.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String lastKanaName = request.getParameter("lastKanaName");
        String firstkanaName = request.getParameter("firstKanaName");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String birthDay = request.getParameter("birthDay");
        String sectionCode = request.getParameter("sectionCode");
        String hireDate = request.getParameter("hireDate");
        String password = request.getParameter("password");

        EmployeeDAO empDao = EmployeeDAO.getInstance();

        boolean insertUserChkFlag = false;

        try {
            empDao.dbConnect();
            empDao.createStm();

            insertUserChkFlag = empDao.insertEmployee(lastName, firstName, lastKanaName, firstkanaName, gender, birthDay, sectionCode, hireDate, password);

        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                empDao.dbDiscon();
        }
        if (!insertUserChkFlag) {
                lastName = null;

        }

        session.setAttribute("lastName", lastName);

        if (insertUserChkFlag) {
                response.sendRedirect("Completion.jsp");
        } else {
                response.sendRedirect("RegistErrorEmployee.jsp");
        }
    }

}
