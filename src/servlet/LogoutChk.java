package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutChk
 */
@WebServlet("/LogoutChk")
public class LogoutChk extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogoutChk() {
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

        session.removeAttribute("loginUserId");

        response.sendRedirect("Lodout.jsp");
    }

}
