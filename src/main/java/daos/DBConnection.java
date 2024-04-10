package daos;

import java.sql.*;


public class DBConnection
{
    public static String URL = "jdbc:mysql://localhost:3306/";
    public static String Uname = "root";
    public static String Password = "root";

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        return DriverManager.getConnection(URL, Uname, Password);
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = DBConnection.getConnection();
        Statement stmt = connection.createStatement();

//        String sql1 = "DROP DATABASE STUDENTS_DB ";
//        stmt.executeUpdate(sql1);

        String sql2 = "CREATE DATABASE IF NOT EXISTS STUDENTS_DB ";
        stmt.executeUpdate(sql2);

        String sql3 = "CREATE TABLE IF NOT EXISTS STUDENTS_DB.students " +
                "(id INTEGER not NULL, " +
                " first_name VARCHAR(255), " +
                " last_name VARCHAR(255), " +
                " age INTEGER, " +
                " height INTEGER, " +
                " weight INTEGER, " +
                " PRIMARY KEY (id))";
        stmt.executeUpdate(sql3);
    }

}
