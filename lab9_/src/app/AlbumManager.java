package app;

import entity.AlbumsEntity;
import entity.ArtistsEntity;
import repo.AlbumRepository;
import repo.ArtistRepository;

import java.util.List;

public class AlbumManager {
    public static void main(String[] args){

        //creating an artist and inserting him in the 'artists' table
        ArtistsEntity artist = new ArtistsEntity();
        artist.setName("Andrei");
        artist.setCountry("Romania");
        ArtistRepository artistRepository = new ArtistRepository();
        artistRepository.create(artist);

        //finding an artist by his id
        ArtistsEntity artistById = artistRepository.findById(1);
        System.out.println(artistById.getName());

        //finding artists by name pattern
        List<ArtistsEntity> artistsEntityList = artistRepository.findByName("andrei");
        for (ArtistsEntity a:
             artistsEntityList) {
            System.out.println(a.getName());
        }

        //creating an album and inserting it in the 'albums' table
        AlbumsEntity album = new AlbumsEntity();
        album.setArtistId(1);
        album.setName("Muzica Mea");
        album.setReleaseYear(2008);
        AlbumRepository albumRepository = new AlbumRepository();
        albumRepository.create(album);

        //finding an album by its id
        AlbumsEntity albumById = albumRepository.findById(1);
        System.out.println(albumById.getReleaseYear());

        //finding albums by name pattern
        List<AlbumsEntity> albumsEntityList = albumRepository.findByName("muzica mea");
        for (AlbumsEntity a:
                albumsEntityList) {
            System.out.println(a.getName());
        }

        //finding albums by artist
        List<AlbumsEntity> albumsEntityListByArtist = albumRepository.findByArtist(artistById);
        for (AlbumsEntity a:
                albumsEntityListByArtist) {
            System.out.println(a.getArtistId());
        }

    }
}
