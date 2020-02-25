package web;

import data.User;
import ejb.ServiceBean;
import ejb.TransactionException;
import ejb.UserBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-account")
public class DeleteAccountController extends HttpServlet {
    @EJB
    UserBean userBean;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = Utils.getUser(req,userBean);
        userBean.deleteById(user.getId());
        resp.sendRedirect("logout");
    }
}
