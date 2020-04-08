package com.company;

import java.sql.*;
import java.util.Locale;
import java.util.Vector;

public class Database {
    // static variable database_instance of type Database
    private static Database database_instance = null;
    private Connection conn;
    private final String username = "dba";
    private final String password = "sql";

    // private constructor restricted to this class itself
    private Database()
    {
        CreateConnection();
    }

    // static method to create instance of Singleton class
    public static Database getInstance()
    {
        if (database_instance == null)
            database_instance = new Database();

        return database_instance;
    }

    private void CreateConnection(){
        try {
            //load driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Locale.setDefault(Locale.ENGLISH); //ORA-12705: Cannot access NLS data files or invalid environment specified
            //connect to DB
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:49161:xe", username, password);
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public Connection getConn() {
        return conn;
    }

    public ResultSet execQuery(String sql) {
        try{
            //create the statement
            Statement s=conn.createStatement();
            //execute the sql query
            ResultSet rs = s.executeQuery(sql);
            return rs;
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
    }
    public Vector<Vector<Object>> getResultSetData(ResultSet rs){
        try{
            ResultSetMetaData md = rs.getMetaData();
            //get number of columns
            int columnCount = md.getColumnCount();
            //System.out.println(columnsCount);
            Vector<String> columnNames = new Vector<String>();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(md.getColumnName(column));
                //System.out.print(md.getColumnName(column) + " ");
            }

            // data of the table
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            //getting all the lines from the result
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                //iterating through a line's contents
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                    //System.out.print(rs.getObject(columnIndex) + " ");
                }
                data.add(vector);
            }
            return data;
        }catch(Exception ex){
            System.out.println(ex);
        }

        return null;
    }
}
