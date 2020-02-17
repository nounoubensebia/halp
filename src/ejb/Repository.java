package ejb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class Repository<T>  {

    protected EntityManager getEntityManager()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JSFTest");
        return emf.createEntityManager();
    }

    public abstract List<T> getAll();

    public abstract T findById(long id);

    public abstract void save(T t) throws TransactionException;

    public abstract void deleteById(long id) throws TransactionException;
}
