package web;

import data.Location;
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

@WebServlet("/delete-service")
public class DeleteServiceController extends HttpServlet {
    @EJB
    UserBean userBean;

    @EJB
    ServiceBean serviceBean;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = Utils.getUser(req,userBean);
        if (user.isAdmin())
        {
            serviceBean.adminDelete(Long.parseLong(req.getParameter("service_id_delete")));
            resp.sendRedirect("All-Services");
        }else{

            serviceBean.deleteById(Long.parseLong(req.getParameter("service_id_delete")));
            resp.sendRedirect("My-Services");

        }

    }
}
