package by.it_academy.jd2.web.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter(urlPatterns = {"/aircheck", "/flights"})
public class DataFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();

        if ((req.getParameter("airpDeparture").isEmpty())
                || (req.getParameter("dateFrom").isEmpty())
                || (req.getParameter("airpArrival").isEmpty())
                || (req.getParameter("dateTo").isEmpty())
                || (req.getParameter("airpDeparture").equals(req.getParameter("airpArrival")))) {
            //редирект на страницу /error
            res.sendRedirect(contextPath + "/error");
        } else {
            chain.doFilter(request, response);
        }
    }
}
