package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.UserDAO;

/**
 * Servlet implementation class LoginChk
 */
@WebServlet("/LoginChk")
public class LoginChk extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginChk() {
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        UserDAO userDao = UserDAO.getInstance();

        boolean loginUserChkFlag = false;

        try {
            userDao.dbConnect();
            userDao.createStm();
            loginUserChkFlag = userDao.loginUser(userId, password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            userDao.dbDiscon();
        }

        if (loginUserChkFlag) {
            session.setAttribute("loginUserId", userId);
            response.sendRedirect("Menu.jsp");
        } else {
            response.sendRedirect("LoginError.jsp");
        }

    }

}
