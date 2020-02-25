package web;


import data.Service;
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

@WebServlet("/validate-service")
public class ValidateServiceController extends HttpServlet {

    @EJB
    ServiceBean serviceBean;

    @EJB
    UserBean userBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = Utils.getUser(req,userBean);
        if (user==null||!user.isAdmin())
        {
            throw new SecurityException();
        }
        Service service = serviceBean.findById(Long.parseLong(req.getParameter("service_id_valider")));
        serviceBean.validate(service);
        resp.sendRedirect("Servlet");

    }

}
