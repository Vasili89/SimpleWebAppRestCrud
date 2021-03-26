package by.it_academy.jd2.web.servlets;

import by.it_academy.jd2.core.dto.ChatUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegistrationChat", urlPatterns = "/regchat")
public class RegistrationChat  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("FIO");
        String birthDate = req.getParameter("birthDate");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        checkField(name);
        checkField(birthDate);
        checkField(login);
        checkField(password);

        ChatUser.saveUser(login, new ChatUser(name, birthDate, login, password));

        String path = req.getContextPath() + "/signIn";
        resp.sendRedirect(path);

    }

    public void checkField(String param) {
        if (param.equals("")) {
            throw new IllegalArgumentException(String.format("Not all fields are full!"));
        }
    }
}