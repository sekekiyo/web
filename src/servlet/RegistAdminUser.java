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
 * Servlet implementation class RegistAdminUser
 */
@WebServlet("/RegistAdminUser")
public class RegistAdminUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegistAdminUser() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUserId") == null) {
                response.sendRedirect("Login.Jsp");
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

        boolean insertUserChkFlag = false;

        try {
            userDao.dbConnect();
            userDao.createStm();
            insertUserChkFlag = userDao.insertUser(userId, password);
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                userDao.dbDiscon();
        }

        if (insertUserChkFlag) {
            session.setAttribute("loginUserId", userId);
        } else {
                response.sendRedirect("RegistErrorAdminUser.jsp");
        }


    }

}
