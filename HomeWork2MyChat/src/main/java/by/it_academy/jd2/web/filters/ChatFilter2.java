package by.it_academy.jd2.web.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebFilter(urlPatterns = {"/signIn", "/signUp"})
public class ChatFilter2 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();

        if ((session != null) && (session.getAttribute("login") != null)) {
            //редирект на страницу отправки сообщений
            res.sendRedirect(contextPath + "/message");
        } else {
            chain.doFilter(request, response);
        }
    }
}

