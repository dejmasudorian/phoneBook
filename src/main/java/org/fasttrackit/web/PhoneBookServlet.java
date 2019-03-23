package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.service.PhoneBookService;
import org.fasttrackit.transfer.CreatePhoneBook;
import org.fasttrackit.transfer.PhoneBookListResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/phone-book")

public class PhoneBookServlet extends HttpServlet{

    private PhoneBookService phoneBookService = new PhoneBookService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PhoneBookListResponse phoneBook = phoneBookService.getPhoneBook();

            ObjectMapper objectMapper = new ObjectMapper();
            String responseJson = objectMapper.writeValueAsString(phoneBook);

            resp.setContentType("application/json");
            setAccessControlHeaders(resp);
            resp.getWriter().print(responseJson);
            resp.getWriter().flush();

        } catch (Exception e) {
            resp.sendError(500, "There was an error processing your request. " +
                    e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        CreatePhoneBook phoneBookRequest =
                objectMapper.readValue(req.getReader(), CreatePhoneBook.class);


        try {

            phoneBookService.createPhoneBook(phoneBookRequest);
            resp.setContentType("application/json");
            setAccessControlHeaders(resp);
            String responseJson = objectMapper.writeValueAsString(phoneBookRequest);
            resp.getWriter().print(responseJson);
            resp.getWriter().flush();

        } catch (Exception e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CreatePhoneBook phoneBookRequest =
                objectMapper.readValue(req.getReader(), CreatePhoneBook.class);
        setAccessControlHeaders(resp);

        try {
            phoneBookService.updatePhoneBook(phoneBookRequest);
        } catch (Exception e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String id = req.getParameter("id");

        setAccessControlHeaders(resp);

        try {
            phoneBookService.deletePhoneBook(id);
        } catch (Exception e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }

    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    }
}