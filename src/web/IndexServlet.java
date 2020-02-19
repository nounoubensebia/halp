package web;

import data.Service;
import data.ServiceNature;
import data.ServiceType;
import ejb.ServiceBean;
import ejb.ServiceNatureBean;
import ejb.ServiceTypeBean;

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
    @EJB
    ServiceTypeBean serviceTypeBean;
    @EJB
    ServiceNatureBean serviceNatureBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Template/home.jsp");
        List<Service> services = serviceBean.getAll();
        List<Service> validServices = getValidServices(services);
        List<ServiceType> serviceTypes = serviceTypeBean.getAll();
        List<ServiceNature> serviceNatures = serviceNatureBean.getAll();
        List<ServiceNature> validNatures = getValidNatures(serviceNatures);
        req.setAttribute("services",validServices);
        req.setAttribute("types",serviceTypes);
        req.setAttribute("natures",validNatures);
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

    public List<ServiceNature> getValidNatures(List<ServiceNature> natures){
        List<ServiceNature> validNatures = new ArrayList<>();
        for (ServiceNature nature:natures) {
            if (!nature.isOther()){
                validNatures.add(nature);
            }
        }
        return validNatures;
    }





}
