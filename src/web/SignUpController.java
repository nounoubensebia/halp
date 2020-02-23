package web;

import data.Address;
import data.User;
import ejb.TransactionException;
import ejb.UserBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignUpController extends UnauthenticatedServlet {


    @EJB
    UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req,resp);
        RequestDispatcher rd = req.getRequestDispatcher("Template/signup.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String email = req.getParameter("email");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String userName = req.getParameter("user_name");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String description = req.getParameter("description");
        boolean isAdmin = false;
        String street = req.getParameter("street");
        String city = req.getParameter("city");
        String supplement = req.getParameter("supplement");

        Address address = new Address(street,city,supplement);
        User user = new User(email,firstName,lastName,userName,phone,
                password,description,isAdmin,address);
        if (userBean.findByEmail(email)!=null)
        {
            //error
            RequestDispatcher rd = req.getRequestDispatcher("Template/signup.jsp");
            req.setAttribute("error-email","email already exists");
            rd.forward(req,resp);
            return;
        }
        try {
            userBean.save(user);
        } catch (TransactionException e) {
            resp.sendError(500);
        }
        RequestDispatcher rd = req.getRequestDispatcher("/signin");
        rd.forward(req,resp);
    }
}
