package com.nnga.utils.helpers;

import java.sql.*;
public class DatabaseHelper {
    static String className = "com.mysql.cj.jdbc.Driver";
    static String dbUrl = "jdbc:mysql://localhost:3306/phone";
    static String username = "root";
    static String password = "Thuynga16022003@";
    static Connection con = null;
    static Statement stmt;
    static PreparedStatement ps;
    public static void setData() throws ClassNotFoundException, SQLException {
        Class.forName(className);
        con = DriverManager.getConnection(dbUrl, username, password);

        System.out.println("Data is set up successful");
    }
    public static ResultSet getQuery(String query) throws SQLException{
        if (con != null) {
            stmt = con.createStatement();
            return stmt.executeQuery(query);
        }
        return null;
    }
    public static void setQuery(String query) throws SQLException{
        if (con != null) {
            ps = con.prepareStatement(query);
            ps.executeUpdate();
        }
    }
    public static void closeData() throws SQLException{
        if (con != null) {
            con.close();
            System.out.println("Data is close successful");
        }
    }

}
