package com.company;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ArtistController {

    Database db = Database.getInstance();

    public boolean create(String name, String country){
        try{
            PreparedStatement stmt = db.getConn().prepareStatement("INSERT INTO artists (name,country) VALUES(?,?)");
            stmt.setString(1, name);
            stmt.setString(2, country);
            return stmt.execute();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



    public int findByName(String name){
        int id=0;
        try{
            //preparing the statement
            PreparedStatement stmt = db.getConn().prepareStatement("SELECT * FROM artists WHERE name=?");
            stmt.setString(1, name);
            //executing the statement
            stmt.execute();
            //getting the results (we have a vector of lines and every line is a vector of objects)
            Vector<Vector<Object>> v = db.getResultSetData(stmt.getResultSet());
            //printing all the data about the artist
            for (int i = 0; i < v.get(0).size(); i++) {
                System.out.println(v.get(0).get(i));
            }

            //getting the artist's id
            id = ((BigDecimal) v.get(0).get(0)).intValue();
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }
}
