package web;

import data.User;
import ejb.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public class Utils {

    public static User getUser(HttpServletRequest req, UserBean userBean)
    {
        //return
        if (req.getSession().getAttribute("user")!=null)
        {
            long user_id = ((User)req.getSession().getAttribute("user")).getId();
            return userBean.findById(user_id);
        }

        else
            return null;
    }

    public static void deleteUser(HttpServletRequest req, UserBean userBean)
    {

    }



}
