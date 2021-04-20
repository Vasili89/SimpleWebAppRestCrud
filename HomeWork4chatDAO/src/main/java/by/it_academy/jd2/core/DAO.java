package by.it_academy.jd2.core;

import by.it_academy.jd2.core.dto.ChatUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

public interface DAO {

    boolean saveUser(ChatUser user);
    ArrayList<String> getMessages(String login);
    void addMessage(HttpSession session, String login, String message);
    void checkUser(HttpServletRequest req, String login, String password);

}
