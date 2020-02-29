package web;


import data.User;
import ejb.UserBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signin")
public class LoginController extends HttpServlet {


    @EJB
    UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Utils.getUser(req,userBean)!=null)
        {
            resp.sendRedirect("Servlet");
        }
        RequestDispatcher rd = req.getRequestDispatcher("Template/signin.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Utils.getUser(req,userBean)!=null)
        {
            resp.sendRedirect("Servlet");
        }
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userBean.findByEmail(email);
        RequestDispatcher requestDispatcher = null;
        if (user!=null && user.getPassword().equals(password))
        {
            req.getSession().setAttribute("user",user);
            resp.sendRedirect("Servlet");
        }
        else
        {
            RequestDispatcher rd = req.getRequestDispatcher("Template/signin.jsp");
            req.setAttribute("error","");
            rd.forward(req,resp);
        }
    }
}
