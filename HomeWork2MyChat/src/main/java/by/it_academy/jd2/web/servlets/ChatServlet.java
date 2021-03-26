package by.it_academy.jd2.web.servlets;

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
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "ChatServlet", urlPatterns = "/mess")
public class ChatServlet  extends HttpServlet {

    private HttpSession session;
    public static String myLogin;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String toLogin = req.getParameter("loginto");
        String message = req.getParameter("message");
        session = req.getSession();
        myLogin = (String) session.getAttribute("login");

        addMessage(session, toLogin, message);

        String path = req.getContextPath() + "/message";
        resp.sendRedirect(path);
    }

    public void addMessage(HttpSession session, String login, String message) {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

        String user = (String) session.getAttribute("login");
        if (!ChatUser.chatUsers.containsKey(login)) throw new IllegalArgumentException("Incorrect users login");
        else ChatUser.chatUsers.get(login).getUsersMessage().add("(" + formatter.format(new Date()) + ")" + " from " + user + ": " + message);
    }

}

