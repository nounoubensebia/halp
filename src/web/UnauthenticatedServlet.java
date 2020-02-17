package web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnauthenticatedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession()!=null)
        {
            if (req.getSession().getAttribute("user")!=null)
            {
                RequestDispatcher rd = req.getRequestDispatcher("Template/dashboard.html");
                rd.forward(req,resp);
                destroy();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession()!=null)
        {
            if (req.getSession().getAttribute("user")!=null)
            {
                RequestDispatcher rd = req.getRequestDispatcher("Template/dashboard.html");
                rd.forward(req,resp);
            }
        }
    }
}
