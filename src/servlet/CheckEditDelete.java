package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckEditDelete
 */
@WebServlet("/CheckEditDelete")
public class CheckEditDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CheckEditDelete() {

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
        request.setCharacterEncoding("UTF-8");

        String employeeCode = request.getParameter("employeeCode");

        session.setAttribute("employeeCode", employeeCode);

        if (request.getParameter("submit").equals("従業員情報を編集")) {
            response.sendRedirect("DeleteEmployee");
        } else {
                response.sendRedirect("ShowAllEmployee.jsp");
        }
    }

}
