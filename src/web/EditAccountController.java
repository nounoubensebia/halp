package web;

import data.Address;
import data.User;
import ejb.UserBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit-account")
public class EditAccountController extends HttpServlet {


    @EJB
    UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = Utils.getUser(req,userBean);
        //super.doPost(req, resp);

        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String description = req.getParameter("description");
        String street = req.getParameter("street");
        String city = req.getParameter("city");
        String supplement = req.getParameter("supplement");

        user.setPhone(phone);
        user.setPassword(password);
        user.setDescription(description);
        user.getAddress().setStreet(street);
        user.getAddress().setCity(city);
        user.getAddress().setSupplement(supplement);

        userBean.update(user);
        RequestDispatcher rd = req.getRequestDispatcher("Template/dashboard.html");
        rd.forward(req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = Utils.getUser(req,userBean);

        userBean.deleteById(user.getId());

        Utils.deleteUser(req,userBean);
    }
}
