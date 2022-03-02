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

import entity.Section;
import models.EmployeeDAO;

/**
 * Servlet implementation class GetSectionEmployee
 */
@WebServlet("/GetSectionEmployee")
public class GetSectionEmployee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSectionEmployee() {
        super();
       }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUserId") == null) {
                response.sendRedirect("Login.jsp");
        } else {
            request.setCharacterEncoding("UTF-8");
            EmployeeDAO empdao = EmployeeDAO.getInstance();

            List<Section> sections = new LinkedList<Section>();

            try {
                empdao.dbConnect();
                empdao.createStm();
                sections = empdao.getSection();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                empdao.dbDiscon();
            }

            session.setAttribute("sections", sections);
            response.sendRedirect("RegistEmployee.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
