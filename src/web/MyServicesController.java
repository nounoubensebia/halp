package web;

import data.Service;
import data.User;
import ejb.ServiceBean;
import ejb.UserBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/My-Services")
public class MyServicesController extends HttpServlet {

    @EJB
    UserBean userBean;
    @EJB
    ServiceBean serviceBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Template/mesServices.jsp");
        User user = Utils.getUser(req,userBean);
        List<Service> serviceList = serviceBean.getUserServices(user);
        req.setAttribute("mesServices",serviceList);
        requestDispatcher.forward(req,resp);
    }
}
