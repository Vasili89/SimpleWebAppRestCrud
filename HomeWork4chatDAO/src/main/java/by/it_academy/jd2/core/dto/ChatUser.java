package by.it_academy.jd2.core.dto;

import java.util.*;

public class ChatUser {

    private String name;
    private String birthDate;
    private String login;
    private String password;
    private ArrayList<String> usersMessage;

    public ChatUser() {
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ChatUser(String name, String birthDate, String login, String password) {
        this.name = name;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        usersMessage = new ArrayList<>();
    }

}
