package Servlets;

import dao.PersonDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/main-page")
public class MainPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonDAO personDAO = PersonDAO.getInstance();
        PrintWriter out = resp.getWriter();

        out.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Add new person</title>\n" +
                "</head>\n" +
                "<body>");
        out.write("<b> List of persons </b>");

        out.write("<ul>\n");
        for (int i = 0; i < personDAO.getListOfPersons().size(); i++) {
            out.write("<form method=\"get\" action=\"/person\">\n");
            out.write("<input type=\"hidden\" name=\"name\" value=\"" + personDAO.getListOfPersons().get(i).getName() +"\" />");
            out.write("<input type=\"submit\" value=\"" + personDAO.getListOfPersons().get(i).getName() + "\"/>");
            out.write("</form>");
        }
        out.write("</ul>\n");
        out.write("<form action=\"/new-person\">\n" +
                "    <button type=\"submit\">Add new person</button>\n" +
                "</form>\n" +
                "\n" +
                "</body>\n" +
                "</html>");

    }
}
