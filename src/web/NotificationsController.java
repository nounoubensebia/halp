package web;

import data.Notification;
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
import java.util.List;

@WebServlet("/notifications")
public class NotificationsController extends HttpServlet {

    @EJB
    UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = Utils.getUser(req,userBean);
        List<Notification> seenNotifications = user.getSeenNotifications();
        List<Notification> unseenNotifications = user.getUnseenNotifications();
        req.setAttribute("seenNotifications",seenNotifications);
        req.setAttribute("unseenNotifications",unseenNotifications);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Template/notifications.jsp");
        requestDispatcher.forward(req,resp);
        userBean.setAllNotificationsToSeen(user);
    }
}
