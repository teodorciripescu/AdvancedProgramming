package com.company;

public class Main {

    public static void main(String[] args) {
        //Used ORACLE DATABASE
        Database db = Database.getInstance();
        db.execQuery("create table artists(" +
                "    id integer not null generated always as identity (start with 1, increment by 1)," +
                "    name varchar(100) not null," +
                "    country varchar(100)," +
                "    primary key (id)" +
                ");");
        db.execQuery("create table albums(" +
                "    id integer not null generated always as identity (start with 1, increment by 1)," +
                "    name varchar(100) not null," +
                "    artist_id integer not null references artists on delete restrict," +
                "    release_year integer," +
                "    primary key (id)" +
                ");");
        // creating an artist and finding him in the database
        ArtistController artistController = new ArtistController();
        artistController.create("andrei","Romania");
        int artistId = artistController.findByName("andrei");

        //creating an album and finding it based on the artist's id
        AlbumController albumController  = new AlbumController();
        albumController.create("albumul meu",1,2005);
        int albumId = albumController.findByArtist(1);
    }
}
