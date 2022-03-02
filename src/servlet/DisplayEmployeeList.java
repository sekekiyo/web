package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.ViewListDisplay;
import models.ViewListDAO;

/**
 * Servlet implementation class DisplayEmployeeList
 */
@WebServlet("/DisplayEmployeeList")
public class DisplayEmployeeList extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public DisplayEmployeeList() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUserId") == null) {
                response.sendRedirect("Login.jsp");
        } else {
            request.setCharacterEncoding("UTF-8");

            ViewListDAO dao = ViewListDAO.getInstance();

            try {
                dao.dbConnect();
                dao.createStm();

                List<ViewListDisplay> vldlist = dao.showAllList();
                session.setAttribute("vldlist", vldlist);

                response.sendRedirect("ShowAllEmployee.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dao.dbDiscon();
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
