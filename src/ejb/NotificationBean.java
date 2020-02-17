package ejb;

import data.Notification;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class NotificationBean extends Repository<Notification> {

    @Override
    public List<Notification> getAll() {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Notification> cq = cb.createQuery(Notification.class);
        Root<Notification> rootEntry = cq.from(Notification.class);
        CriteriaQuery<Notification> all = cq.select(rootEntry);
        TypedQuery<Notification> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Notification findById(long id) {
        EntityManager entityManager = getEntityManager();
        return entityManager.find(Notification.class,id);
    }

    @Override
    public void save(Notification notification) throws TransactionException {
        try {
            EntityManager entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(notification);
            entityManager.getTransaction().commit();
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
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Notification notification = em.find(Notification.class,id);
        em.remove(notification);
        em.getTransaction().commit();
        }
        catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
            throw transactionException;
        }
    }
}
