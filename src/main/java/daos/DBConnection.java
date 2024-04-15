package daos;

import java.sql.*;


public class DBConnection
{
    public static String URL = "jdbc:mysql://localhost:3306/";
    public static String Uname = "root";
    public static String Password = "root";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return DriverManager.getConnection(URL, Uname, Password);
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Error with SQL connection", e);
        }
    }

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();
    }

}
