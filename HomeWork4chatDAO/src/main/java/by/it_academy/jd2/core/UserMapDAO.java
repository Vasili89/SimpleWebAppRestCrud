package by.it_academy.jd2.core;

import by.it_academy.jd2.core.dto.ChatUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class UserMapDAO implements DAO {

    private final static UserMapDAO instance = new UserMapDAO();

    public static UserMapDAO getInstance() {
        return instance;
    }

    private UserMapDAO() {
    }

    private final HashMap<String, ChatUser> chatUsers = new HashMap<>();

    public HashMap<String, ChatUser> getChatUsers() {
        return chatUsers;
    }

    @Override
    public boolean saveUser(ChatUser user) {
        if (!this.chatUsers.containsKey(user.getLogin())) {
            this.chatUsers.put(user.getLogin(), user);
        } else throw new IllegalArgumentException("User with this login already exists!");
        return false;
    }

    @Override
    public ArrayList<String> getMessages(String login) {
        return this.chatUsers.get(login).getUsersMessage();
    }

    @Override
    public void addMessage(HttpSession session, String login, String message) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

        String user = (String) session.getAttribute("login");
        if (!this.chatUsers.containsKey(login)) throw new IllegalArgumentException("Incorrect users login");
        else this.chatUsers.get(login).getUsersMessage().add("(" + formatter.format(new Date()) + ")" + " from " + user + ": " + message);
    }

    public void checkUser(HttpServletRequest req, String login, String password) {
        if (login.isEmpty() || password.isEmpty()) throw new IllegalArgumentException("Login or Password incorrect!");
        if (!this.chatUsers.containsKey(login)) throw new IllegalArgumentException("User with this Login is no registered!");
        if (this.chatUsers.containsKey(login)) {
            if (!this.chatUsers.get(login).getPassword().equals(password)) {
                throw new IllegalArgumentException("Password incorrect!");
            }
        }
    }

}
