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

@WebServlet("/update-service")
public class EditServiceController extends HttpServlet {

    @EJB
    ServiceBean serviceBean;

    @EJB
    UserBean userBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!Utils.checkSecurity(1,req,userBean))
        {
            throw new SecurityException();
        }

        Service service = serviceBean.findById(Long.parseLong(req.getParameter("service_id_edit")));
        DateTimeFormatter formatter = DateUtils.getStandardFormatter();
        LocalDateTime startDate =  LocalDate.parse(req.getParameter("start_date"),formatter).atStartOfDay();
        LocalDateTime endDate = LocalDate.parse(req.getParameter("end_date"),formatter).atStartOfDay();
        String shortDescription = req.getParameter("short_description");
        String longDescription = req.getParameter("long_description");
        Location location = new Location(req.getParameter("province"),req.getParameter("commune"),
                req.getParameter("city"));
        service.setStartDate(startDate);
        service.setEndDate(endDate);
        service.setShortDescription(shortDescription);
        service.setLongDescription(longDescription);
        service.setLocation(location);

        serviceBean.update(service);
        resp.sendRedirect("service?service_id="+service.getId());

    }
}



