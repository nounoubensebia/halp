package web;

import data.User;
import ejb.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public class Utils {

    public static User getUser(HttpServletRequest req, UserBean userBean)
    {
        //return
        if (req.getParameter("user_id")!=null)
            return (User)req.getSession().getAttribute("user");
        else
            return null;
    }

    public static void deleteUser(HttpServletRequest req, UserBean userBean)
    {

    }



}
