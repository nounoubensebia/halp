package ejb;

import data.ServiceType;

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
public class ServiceTypeBean extends Repository<ServiceType> {
    @Override
    public List<ServiceType> getAll() {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ServiceType> cq = cb.createQuery(ServiceType.class);
        Root<ServiceType> rootEntry = cq.from(ServiceType.class);
        CriteriaQuery<ServiceType> all = cq.select(rootEntry);
        TypedQuery<ServiceType> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public ServiceType findById(long id) {
        EntityManager entityManager = getEntityManager();
        return entityManager.find(ServiceType.class,id);
    }

    @Override
    public void save(ServiceType serviceType) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(serviceType);
        em.getTransaction().commit();
    }

    @Override
    public void deleteById(long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        ServiceType service = em.find(ServiceType.class,id);
        em.remove(service);
        em.getTransaction().commit();
    }
}
