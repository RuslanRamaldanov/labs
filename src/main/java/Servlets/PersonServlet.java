package Servlets;

import Entity.Person;
import dao.PersonDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/person")
public class PersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonDAO personDAO = PersonDAO.getInstance();
        PrintWriter out = resp.getWriter();
        out.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>" + req.getParameter("name") + "</title>\n" +
                "</head>\n" +
                "<body>");
        out.write("<b>" + req.getParameter("name") + "</b>\n");
        String personName = req.getParameter("name");
        Person person = personDAO.getPerson(personName);
        out.write("<b>List of phone numbers</b>\n");
        if (person != null) {
            if (person.getPhoneNumbers() != null) {
                for (int i = 0; i < person.getPhoneNumbers().size(); i++) {
                    out.write("<ul>\n" +
                            "<li>" + person.getPhoneNumbers().get(i) + "</li>\n" +
                            "</ul>");
                }
            }

        }

        out.write("<form action=\"/person\" method=\"post\">\n" +
                "    <label> Input new phone number\n" +
                "        <input name=\"number\">\n" +
                "    </label>\n" +
                "<input type=\"hidden\" name=\"name\" value=\""+ personName + "\""+
                "    <button type=\"submit\">Add</button>\n" +
                "</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonDAO personDAO = PersonDAO.getInstance();
        String personName = req.getParameter("name");
        String phoneNumber = req.getParameter("number");
        Person person = personDAO.getPerson(personName);
        person.addPhoneNumber(phoneNumber);
        personDAO.refreshFile();
        resp.sendRedirect("/main-page");
    }
}
