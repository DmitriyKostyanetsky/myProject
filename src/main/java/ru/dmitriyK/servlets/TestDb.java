package ru.dmitriyK.servlets;

import java.sql.*;

public class TestDb {

    public Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public static void main(String[] args) throws SQLException {
        TestDb testDb = new TestDb();
        testDb.getConnection();
        testDb.getUserList();
        testDb.disconnectDb();
    }

    public void setConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String dbUser = "postgres";
            String dbPass = "admin";
            String connectionUrl = "jdbc:postgresql://localhost:5432/users";
            connection = DriverManager.getConnection(connectionUrl, dbUser, dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        setConnection();
        return connection;
    }

    public ResultSet getUserList() {
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Disconnect database
    public void disconnectDb() {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}