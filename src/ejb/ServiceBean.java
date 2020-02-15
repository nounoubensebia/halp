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
public class ServiceBean {

    private EntityManager getEntityManager()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JSFTest");
        return emf.createEntityManager();
    }

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

}
