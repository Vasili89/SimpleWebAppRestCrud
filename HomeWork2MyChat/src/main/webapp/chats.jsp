<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, by.it_academy.jd2.core.dto.ChatUser, jakarta.servlet.http.*, by.it_academy.jd2.web.servlets.ChatServlet" %>

<html>
 <head>
   <title>TheBestChat</title>
   <meta charset="utf-8">
 </head>
 <body>
    <h3>The Best chat!</h3>

    <% ArrayList<String> messages = ChatUser.chatUsers.get(session.getAttribute("login")).getUsersMessage(); %>

    <%= "Messages for " + session.getAttribute("login") + ":" %>

    <br>
    <br>

    <%
    for(int i = 0; i < messages.size(); i++) {
        out.println(messages.get(i)); %>
        <br/>
    <%  }
    %>

    <br/>

    <form action="message" method="GET">
                <input type="submit" value="Back to Chat">
            </form>

    <form action="exitChat" method="GET">
                <input type="submit" value="Exit">
            </form>
 </body>
</html>