package by.it_academy.jd2.web.servlets;

import by.it_academy.jd2.core.DAO;
import by.it_academy.jd2.core.UserDbDAO;
import by.it_academy.jd2.core.UserMapDAO;
import by.it_academy.jd2.core.dto.ChatUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "InChat", urlPatterns = "/inchat")
public class InChat  extends HttpServlet {

    public static String INLOGIN = "login";
    private static String INPASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String inLogin = req.getParameter(INLOGIN);
        String inPassword = req.getParameter(INPASSWORD);

        DAO storage = UserDbDAO.getInstance();

        storage.checkUser(req, inLogin, inPassword);

        HttpSession session = req.getSession();
        session.setAttribute(INLOGIN, inLogin);

        String path = req.getContextPath() + "/message";
        resp.sendRedirect(path);
    }
}