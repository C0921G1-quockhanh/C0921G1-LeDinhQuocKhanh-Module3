package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    public static Connection connection;
    //sua ten database
    private static String jdbcURL = "jdbc:mysql://localhost:3306/furama_resort_web";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "khanh12345";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}