package by.it_academy.jd2.web.servlets;

import by.it_academy.jd2.core.dto.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

@WebServlet(name = "CookieSession", urlPatterns = "/cs")
public class CookieSession extends HttpServlet {

    public final static String FIRST_NAME_PARAM = "firstName";
    public final static String LAST_NAME_PARAM = "lastName";
    public final static String AGE = "age";
    public final static String HEADER = "header";
    public final static String SESSION = "session";
    public final static String COOKIE = "cookie";
    Person person;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NumberFormatException {

        String header = checkHeader(req, HEADER);
        person = new Person();
        String firstName;
        String lastName;
        String age;

        if (header.equals(COOKIE)) {

            firstName = getValueFrom(req, FIRST_NAME_PARAM);
            lastName = getValueFrom(req, LAST_NAME_PARAM);
            age = getValueFrom(req, AGE);

            saveCookie(resp, FIRST_NAME_PARAM, firstName);
            saveCookie(resp, LAST_NAME_PARAM, lastName);
            saveCookie(resp, AGE, age);

        } else if (header.equals(SESSION)) {

            HttpSession session = req.getSession();

            firstName = getSessionVal(req, session, FIRST_NAME_PARAM);
            lastName = getSessionVal(req, session, LAST_NAME_PARAM);
            age = getSessionVal(req, session, AGE);
        }
        else throw new IllegalArgumentException("Input correct header: cookie or session!");

        person.setFirstName(firstName);
        person.setLastName(lastName);
        try {
            person.setAge(Integer.parseInt(age));
        } catch (Exception e) {
            throw new IllegalArgumentException("Peredayte chislo v parametr age");
        }

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        writer.write("<p>Hello, " + person.getFirstName() + " " + person.getLastName() + ", " + person.getAge() +  " years old!!!</p>");
    }

    public static String getValueFrom(HttpServletRequest req, String key) {
        String val = req.getParameter(key);
        if(val == null) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                val = Arrays.stream(cookies)
                        .filter(c -> key.equalsIgnoreCase(c.getName()))
                        .map(Cookie::getValue)
                        .findFirst()
                        .orElse(null);
            }
        }
        if (val == null) throw new IllegalArgumentException(String.format("Ne peredan parametr %s", key));

        return val;
    }

    public static void saveCookie(HttpServletResponse resp, String key, String value) {
        Cookie c = new Cookie(key, value);
        c.setMaxAge(-1);
        resp.addCookie(c);
    }

    public static String checkHeader(HttpServletRequest req, String header) {
        String headValue = "";
        Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headName = (String) headers.nextElement();
            if (headName.equals(header)) headValue = req.getHeader(headName);
        }
        return headValue;
    }

    public static String getSessionVal(HttpServletRequest req, HttpSession session, String key) {
        String val = req.getParameter(key);
        if (val == null) {
            if (session.isNew()) throw new IllegalArgumentException(String.format("Ne peredan parametr %s", key));
            else val = (String) session.getAttribute(key);
        }
        if (val == null) throw new IllegalArgumentException(String.format("Ne peredan parametr %s", key));
        session.setAttribute(key, val);
        return val;
    }
}
