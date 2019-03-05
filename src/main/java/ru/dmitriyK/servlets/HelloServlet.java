package ru.dmitriyK.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        try {
            //Class.forName()
            String dbUser = "postgres";
            String dbPass = "admin";
            String connectionUrl = "jdbc:postgresql://localhost:5432/users";
            Connection connection = DriverManager.getConnection(connectionUrl, dbUser, dbPass);
            PreparedStatement ps = connection.prepareStatement("INSERT into user VALUES (?, ?, ?)");

            ps.setString(1 , id);
            ps.setString(2, name);
            ps.setString(3, password);

            int i = ps.executeUpdate();
            if(i > 0) {
                out.print("You are successfully registered");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.close();
    }
}