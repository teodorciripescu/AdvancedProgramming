package com.company;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.Vector;

public class AlbumController {

    Database db = Database.getInstance();

    public boolean create(String name, int artistId, int releaseYear){
        try{
            PreparedStatement stmt = db.getConn().prepareStatement("INSERT INTO albums (name,artist_id,release_year) VALUES(?,?,?)");
            stmt.setString(1, name);
            stmt.setInt(2, artistId);
            stmt.setInt(3, releaseYear);
            return stmt.execute();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public int findByArtist(int artistId){
        int id = 0;
        try{
            //preparing the statement
            PreparedStatement stmt = db.getConn().prepareStatement("SELECT * FROM albums WHERE artist_id=?");
            stmt.setInt(1, artistId);
            //executing the statement
            stmt.execute();
            //getting the results (we have a vector of lines and every line is a vector of objects)
            Vector<Vector<Object>> v = db.getResultSetData(stmt.getResultSet());
            //printing all the data about the album
            for (int i = 0; i < v.get(0).size(); i++) {
                System.out.println(v.get(0).get(i));
            }

            //getting the albums's id
            id = ((BigDecimal) v.get(0).get(0)).intValue();
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }
}
