package ejb;

import data.ServiceType;

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
public class ServiceTypeBean extends Repository<ServiceType> {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<ServiceType> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ServiceType> cq = cb.createQuery(ServiceType.class);
        Root<ServiceType> rootEntry = cq.from(ServiceType.class);
        CriteriaQuery<ServiceType> all = cq.select(rootEntry);
        TypedQuery<ServiceType> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public ServiceType findById(long id) {
        EntityManager entityManager = em;
        return entityManager.find(ServiceType.class,id);
    }

    @Override
    public void save(ServiceType serviceType) throws TransactionException {
        try {
        em.persist(serviceType);
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
            ServiceType service = em.find(ServiceType.class,id);
            em.remove(service);
        }
        catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
            throw transactionException;
        }
    }
}
