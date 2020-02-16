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
import java.util.Enumeration;

@WebServlet("/service")
public class ServiceServlet extends HttpServlet {

    @EJB
    ServiceBean serviceBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Enumeration<String> params = req.getParameterNames();
        long ida = Long.parseLong(id);
        Service service = serviceBean.findById(ida);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Template/Service.jsp");
        req.setAttribute("Service",service);
        requestDispatcher.forward(req,resp);
    }
}
