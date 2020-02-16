package ejb;

import data.Service;
import data.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServiceBean extends Repository<Service> {



    @Override
    public List<Service> getAll()
    {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Service> cq = cb.createQuery(Service.class);
        Root<Service> rootEntry = cq.from(Service.class);
        CriteriaQuery<Service> all = cq.select(rootEntry);
        TypedQuery<Service> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Service findById(long id)
    {
        EntityManager entityManager = getEntityManager();
        return entityManager.find(Service.class,id);
    }

    @Override
    public void save(Service service)
    {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(service.getLocation());
        if (service.getServiceNature().getNatureIfOther()!=null)
        {
            em.persist(service.getServiceNature());
        }
        em.persist(service);
        em.getTransaction().commit();
    }

    @Override
    public void deleteById(long id)
    {
        //TODO check cascade
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Service service = em.find(Service.class,id);
        em.remove(service);
        em.getTransaction().commit();
    }

}
