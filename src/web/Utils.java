package web;

import data.User;
import ejb.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public class Utils {

    public static User getUser(HttpServletRequest req, UserBean userBean)
    {
        return (User)req.getSession().getAttribute("user");
        //return userBean.findById(Long.parseLong(req.getParameter("user_id")));
    }

    public static void deleteUser(HttpServletRequest req, UserBean userBean)
    {

    }



}
