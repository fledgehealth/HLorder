package utils;


import java.sql.*;

public class DBUtil {

    private static final String URL = "jdbc:postgresql://docpharma-dev.cj88goguigez.eu-north-1.rds.amazonaws.com:5432/postgres";
    private static final String USER = "readonly_user";
    private static final String PASSWORD = "Ulj2JkLfNNsb5aB0";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        Statement stmt = getConnection().createStatement();
        return stmt.executeQuery(query);
    }

    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
