package ejb;

import data.ServiceNature;

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
public class ServiceNatureBean extends Repository<ServiceNature> {
    @Override
    public List<ServiceNature> getAll() {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ServiceNature> cq = cb.createQuery(ServiceNature.class);
        Root<ServiceNature> rootEntry = cq.from(ServiceNature.class);
        CriteriaQuery<ServiceNature> all = cq.select(rootEntry);
        TypedQuery<ServiceNature> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public ServiceNature findById(long id) {
        EntityManager entityManager = getEntityManager();
        return entityManager.find(ServiceNature.class,id);
    }

    @Override
    public void save(ServiceNature serviceNature) throws TransactionException {
        try {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(serviceNature);
        em.getTransaction().commit();
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
            ServiceNature service = em.find(ServiceNature.class,id);
            em.remove(service);
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
