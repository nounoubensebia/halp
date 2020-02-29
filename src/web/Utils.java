package web;

import data.User;
import ejb.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public class Utils {


    public static final int SECURITY_MEMBER = 0;
    public static final int SECURITY_USER = 1;
    public static final int SECURITY_ADMIN = 2;

    public static User getUser(HttpServletRequest req, UserBean userBean)
    {
        //return
        if (req.getSession().getAttribute("user")!=null)
        {
            long user_id = ((User)req.getSession().getAttribute("user")).getId();
            req.getSession().setAttribute("user",userBean.findById(user_id));
            return (User)req.getSession().getAttribute("user");
        }

        else
            return null;
    }

    public static void deleteUser(HttpServletRequest req, UserBean userBean)
    {

    }

    public static boolean checkSecurity(int securityRequired, HttpServletRequest req,
                                        UserBean userBean)
    {
        User user = getUser(req,userBean);
        int actualSecurity = 0;
        if (user != null)
        {
            if (user.isAdmin())
            {
                actualSecurity = 2;
            }
            else
            {
                actualSecurity = 1;
            }
        }
        return actualSecurity>=securityRequired;
    }

}
