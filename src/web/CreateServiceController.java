package web;

import data.Address;
import data.Service;
import data.User;
import ejb.ServiceBean;
import ejb.UserBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-service")
public class CreateServiceController extends HttpServlet {

    @EJB
    UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Address address = new Address("stree","dera","doba");
        User user = new User("ema","ema","ema","ema","ema","pass",false,address);
        userBean.save(user);

    }
}
