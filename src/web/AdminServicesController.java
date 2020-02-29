package web;

import data.Service;
import data.User;
import ejb.ServiceBean;
import ejb.UserBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/All-Services")
public class AdminServicesController extends HttpServlet {
    @EJB
    ServiceBean serviceBean;

    @EJB
    UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!Utils.checkSecurity(2,req,userBean))
        {
            throw new SecurityException();
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Template/tousServices.jsp");
        List<Service> services = serviceBean.getAll();
        List<Service> AValiderServices = getAValiderServices(services);
        req.setAttribute("tousServices",AValiderServices);
        requestDispatcher.forward(req,resp);
    }
    public List<Service> getAValiderServices(List<Service> services){
        List<Service> AValiderServices = new ArrayList<>();
        for (Service service:services) {
            if (service.getStatus() == 0 ){
                AValiderServices.add(service);
            }
        }
        return AValiderServices;
    }
}
