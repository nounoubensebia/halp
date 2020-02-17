package web;

import data.Service;
import ejb.ServiceBean;

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

@WebServlet("/Servlet")
public class IndexServlet extends HttpServlet {

    @EJB
    ServiceBean serviceBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Template/home.jsp");
        List<Service> services = serviceBean.getAll();
        List<Service> validServices = getValidServices(services);
        req.setAttribute("services",validServices);
        requestDispatcher.forward(req,resp);
    }

    public List<Service> getValidServices(List<Service> services){
        List<Service> validServices = new ArrayList<>();
        for (Service service:services) {
            if (service.getStatus() == 0){
                validServices.add(service);
            }
        }
        return validServices;
    }


}
