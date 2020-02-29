package web;

import data.Service;
import data.User;
import data.UserResponse;
import ejb.ServiceBean;
import ejb.TransactionException;
import ejb.UserBean;
import ejb.UserResponseBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/create-user-response")
public class UserResponseController extends HttpServlet {

    @EJB
    UserBean userBean;

    @EJB
    ServiceBean serviceBean;

    @EJB
    UserResponseBean userResponseBean ;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = Utils.getUser(req,userBean);
        if (!Utils.checkSecurity(1,req,userBean))
        {
            throw new SecurityException();
        }
        Service service = serviceBean.findById(Long.parseLong(req.getParameter("service_id")));
        String message = req.getParameter("message");
        LocalDateTime dateTime = LocalDateTime.now();

        if (service.getStatus()!=1)
        {
            //TODO show error message
            req.setAttribute("message","Un utilisateur a deja répondu à cette offre/demande");
            req.getRequestDispatcher("/Template/error_message").forward(req,resp);
            return;
        }

        UserResponse userResponse = new UserResponse(service,user,dateTime,message);
        try {
            userResponseBean.save(userResponse);
        } catch (TransactionException e) {
            resp.sendError(500);
            e.printStackTrace();
            return;
        }
        //TODO redirect to notifications page
        resp.sendRedirect("Servlet");
    }
}
