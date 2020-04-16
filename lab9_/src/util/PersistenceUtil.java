package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    private static PersistenceUtil single_instance = null;
    private EntityManagerFactory factory;

    private PersistenceUtil()
    {
        factory = Persistence.createEntityManagerFactory("MusicAlbumsPU");
    }

    public static PersistenceUtil getInstance()
    {
        if (single_instance == null)
            single_instance = new PersistenceUtil();

        return single_instance;
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }
}
