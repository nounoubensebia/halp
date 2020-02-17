package web;


import data.Service;
import ejb.ServiceBean;
import ejb.TransactionException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/validate-service")
public class ValidateServiceController extends HttpServlet {

    @EJB
    ServiceBean serviceBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("service_id"));
        Service service = serviceBean.findById(id);
        try {
            serviceBean.validate(service);
        } catch (TransactionException e) {
            resp.sendError(500);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("service_id"));
        //Service service = serviceBean.findById(id);
        try {
        serviceBean.adminDelete(id);
        }
        catch (TransactionException e)
        {
            resp.sendError(500);
        }
    }
}
