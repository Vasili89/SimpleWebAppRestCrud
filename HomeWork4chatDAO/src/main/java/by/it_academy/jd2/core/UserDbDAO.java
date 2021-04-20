package by.it_academy.jd2.core;

import by.it_academy.jd2.core.dto.ChatUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserDbDAO implements DAO {

    private static UserDbDAO instance;
    Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/chatusers", "postgres", "postgres");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
            instance = new UserDbDAO();
        } catch (ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private UserDbDAO() {
    }

    public static UserDbDAO getInstance() {
        if (instance == null) {
            instance = new UserDbDAO();
        }
        return instance;
    }

    @Override
    public boolean saveUser(ChatUser user) {
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO chatusers.chatuser(login, name, birthdate, password) VALUES (?, ?, ?, ?);" +
                        "CREATE TABLE chatusers." + user.getLogin() + " " +
                        "(id integer NOT NULL GENERATED ALWAYS AS IDENTITY, message text, PRIMARY KEY (id)); " +
                        "ALTER TABLE chatusers." + user.getLogin() + " OWNER to postgres;")) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getName());
            ps.setString(3, user.getBirthDate());
            ps.setString(4, user.getPassword());
            if (ps.executeUpdate() > 0) result = true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<String> getMessages(String login) {
        ArrayList<String> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM chatusers." + login)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("message"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public void addMessage(HttpSession session, String login, String message) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        String user = (String) session.getAttribute("login");
        String message1 = "(" + formatter.format(new Date()) + ")" + " from " + user + ": " + message;
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO chatusers." + login + " (message) VALUES (?)")) {
            ps.setString(1, message1);
            int rs = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void checkUser(HttpServletRequest req, String login, String password) {
        if (login.isEmpty() || password.isEmpty()) throw new IllegalArgumentException("Login or Password incorrect!");
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT login, password FROM chatusers.chatuser WHERE login = ? AND password = ?")) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new IllegalArgumentException("User with this Login or password is no registered!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
