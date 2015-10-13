/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author web
 */
public class DB {
   
    public static Connection conn;
    public static Statement statement;
    public static DB db;
    
    private DB() throws SQLException {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "database_name";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "username";
        String password = "password";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException sqle) {
            System.out.println("DB exception in DB.java");
        }
    }
    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized DB getDbCon() throws SQLException {
        if ( db == null ) {
            db = new DB();
        }
        return db;
 
    }
    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException{
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public int insert(String insertQuery) throws SQLException {
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
 
    }
    
}
