package lab1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public final class EntityManagerProvider {

    private static volatile EntityManagerFactory factory;

    private EntityManagerProvider() {
    }

    private static EntityManagerFactory getFactory() {
        EntityManagerFactory result = factory;
        if (result != null) {
            return result;
        }
        synchronized(EntityManager.class) {
            if (factory == null) {
                Map<String, String> props = new HashMap<>();
                props.put("javax.persistence.jdbc.password", System.getenv("DB_PASS"));
                factory = Persistence.createEntityManagerFactory("default", props);
            }
            return factory;
        }
    }

    public static EntityManager provide() {
        return getFactory().createEntityManager();
    }
}
