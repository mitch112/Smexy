package com.rs.sql;

import java.sql.*;
import java.security.MessageDigest;

public class SQL {

        public static Connection con = null;
        public static Statement stm;

        public static void createConnection() {
                try {
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        con = DriverManager.getConnection("jdbc:mysql://ip/dbname", "dbusername", "dbpass");
                        stm = con.createStatement();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        public static ResultSet query(String s) throws SQLException {
                try {
                        if (s.toLowerCase().startsWith("select")) {
                                ResultSet rs = stm.executeQuery(s);
                                return rs;
                        } else {
                                stm.executeUpdate(s);
                        }
                        return null;
                } catch (Exception e) {
                        System.out.println("MySQL Error:"+s);
                        e.printStackTrace();
                }
                return null;
        }

        public static void destroyCon() {
                try {
                        stm.close();
                        con.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        
        public static boolean checkVotes(String playerName)
        {
                try {
                        Statement statement = con.createStatement();
                        String query = "SELECT * FROM Votes WHERE username = '" + playerName.replaceAll("_", " ") + "'";
                        ResultSet results = statement.executeQuery(query);
                        while(results.next()) {
                                int recieved = results.getInt("recieved");
                                if(recieved == 0)
                                {
                                return true;
                                }
                                
                        }
                } catch(SQLException e) {
                        e.printStackTrace();
                }
                return false;
        }
        public static boolean voteGiven(String playerName)
        {
                try
                {
                        query("UPDATE Votes SET recieved = 1 WHERE username = '" + playerName.replaceAll("_", " ") + "'");
                } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                }
                return true;
        }
}