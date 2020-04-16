package repo;

import entity.AlbumsEntity;
import entity.ArtistsEntity;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class AlbumRepository {
    PersistenceUtil pu = PersistenceUtil.getInstance();

    public void create(AlbumsEntity entity){
        EntityManagerFactory factory = pu.getFactory();
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();

        em.close();
    }

    public AlbumsEntity findById(int id){
        EntityManagerFactory factory = pu.getFactory();
        EntityManager em = factory.createEntityManager();

        Query query = em.createQuery(
                "SELECT a FROM AlbumsEntity a WHERE a.id=:albumId")
                .setParameter("albumId", id)
                .setMaxResults(1);
        return (AlbumsEntity)query.getSingleResult();
    }

    public List<AlbumsEntity> findByName(String name) {
        EntityManagerFactory factory = pu.getFactory();
        EntityManager em = factory.createEntityManager();

        Query query = em.createQuery(
                "SELECT a FROM AlbumsEntity a WHERE a.name LIKE :albumName")
                .setParameter("albumName", name)
                .setMaxResults(10);
        return query.getResultList();
    }

    public List<AlbumsEntity> findByArtist(ArtistsEntity artist) {
        EntityManagerFactory factory = pu.getFactory();
        EntityManager em = factory.createEntityManager();

        Query query = em.createQuery(
                "SELECT a FROM AlbumsEntity a WHERE a.artistId=:artistId")
                .setParameter("artistId", artist.getId())
                .setMaxResults(10);
        return query.getResultList();
    }
}
