package ejb;

import data.ServiceNature;

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
public class ServiceNatureBean extends Repository<ServiceNature> {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<ServiceNature> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ServiceNature> cq = cb.createQuery(ServiceNature.class);
        Root<ServiceNature> rootEntry = cq.from(ServiceNature.class);
        CriteriaQuery<ServiceNature> all = cq.select(rootEntry);
        TypedQuery<ServiceNature> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public ServiceNature findById(long id) {
        EntityManager entityManager = em;
        return entityManager.find(ServiceNature.class,id);
    }

    @Override
    public void save(ServiceNature serviceNature) throws TransactionException {
        try {
        em.persist(serviceNature);
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
            em.getTransaction().begin();
            ServiceNature service = em.find(ServiceNature.class,id);
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
