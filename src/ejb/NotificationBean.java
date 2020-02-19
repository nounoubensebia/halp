package ejb;

import data.Notification;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NotificationBean extends Repository<Notification> {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Notification> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Notification> cq = cb.createQuery(Notification.class);
        Root<Notification> rootEntry = cq.from(Notification.class);
        CriteriaQuery<Notification> all = cq.select(rootEntry);
        TypedQuery<Notification> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Notification findById(long id) {
        EntityManager entityManager = em;
        return entityManager.find(Notification.class,id);
    }

    @Override
    public void save(Notification notification) throws TransactionException {
        try {
            EntityManager entityManager = em;
            entityManager.persist(notification);
        }
        catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
            throw transactionException;
        }
    }

    @Override
    public void deleteById(long id) throws TransactionException {
        try {
        Notification notification = em.find(Notification.class,id);
        em.remove(notification);
        }
        catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
            throw transactionException;
        }
    }
}
