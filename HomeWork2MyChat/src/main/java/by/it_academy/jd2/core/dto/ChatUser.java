package by.it_academy.jd2.core.dto;

import java.util.*;

public class ChatUser {

    public static HashMap<String, ChatUser> chatUsers = new HashMap<>();

    private String name;
    private String birthDate;
    private String login;
    private String password;
    private ArrayList<String> usersMessage;

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<String> getUsersMessage() {
        return usersMessage;
    }

    public void setUsersMessage(ArrayList<String> usersMessage) {
        this.usersMessage = usersMessage;
    }

    public ChatUser(String name, String birthDate, String login, String password) {
        this.name = name;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        usersMessage = new ArrayList<>();
    }

    public static void saveUser(String login, ChatUser user) {
        if (!chatUsers.containsKey(login)) {
            chatUsers.put(login, user);
        } else throw new IllegalArgumentException("User with this login already exists!");
    }

}
