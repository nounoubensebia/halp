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
import java.util.Enumeration;
import java.util.List;

@WebServlet("/service")
public class ServiceServlet extends HttpServlet {

    @EJB
    ServiceBean serviceBean;
    @EJB
    ServiceTypeBean serviceTypeBean;
    @EJB
    ServiceNatureBean serviceNatureBean;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("service_id");
        Enumeration<String> params = req.getParameterNames();
        long ida = Long.parseLong(id);
        Service service = serviceBean.findById(ida);
        List<ServiceType> serviceTypes = serviceTypeBean.getAll();
        List<ServiceNature> serviceNatures = serviceNatureBean.getAll();
        List<ServiceNature> validNatures = getValidNatures(serviceNatures);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Template/service.jsp");
        req.setAttribute("Service",service);
        req.setAttribute("types",serviceTypes);
        req.setAttribute("natures",validNatures);
        requestDispatcher.forward(req,resp);
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
