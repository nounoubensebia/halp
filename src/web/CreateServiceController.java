package web;

import data.*;
import ejb.*;
import utils.DateUtils;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/create-service")
public class CreateServiceController extends HttpServlet {

    @EJB
    UserBean userBean;

    @EJB
    ServiceBean serviceBean;

    @EJB
    ServiceTypeBean serviceTypeBean;

    @EJB
    ServiceNatureBean serviceNatureBean;



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = Utils.getUser(req,userBean);
        if (!Utils.checkSecurity(1,req,userBean))
        {
            throw new SecurityException();
        }
        DateTimeFormatter formatter = DateUtils.getStandardFormatter();
        LocalDateTime startDate =  LocalDate.parse(req.getParameter("start_date"),formatter).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(req.getParameter("end_date"),formatter).atStartOfDay();
        LocalDateTime creationDate = LocalDateTime.now();
        String shortDescription = req.getParameter("short_description");
        String longDescription = req.getParameter("long_description");
        boolean isOffer = Boolean.parseBoolean(req.getParameter("isOffer"));
        int status = 0;
        Location location = new Location(req.getParameter("province"),req.getParameter("commune"),
                req.getParameter("city"));
        ServiceType serviceType = serviceTypeBean.findById(Long.parseLong(req.getParameter("service_type_id")));

        boolean isOther = Boolean.parseBoolean(req.getParameter("service_nature_is_other"));

        ServiceNature serviceNature = null;
        if (isOther)
        {
            String serviceNatureString = req.getParameter("service_nature");
            serviceNature = new ServiceNature(serviceNatureString,isOther);
        }
        else
        {
            serviceNature = serviceNatureBean.findById(Long.parseLong(req.getParameter("service_nature_id")));
        }

        Service service = new Service(user,startDate,endDate,creationDate,shortDescription,longDescription,
                isOffer,status,location,serviceType,serviceNature);

        serviceBean.save(service);
        resp.sendRedirect("service?service_id="+service.getId());
    }
}
