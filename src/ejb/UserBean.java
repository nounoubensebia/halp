package ejb;

import data.User;

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
public class UserBean extends Repository<User> {


    @Override
    public List<User> getAll() {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);
        TypedQuery<User> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public User findById(long id) {
        EntityManager entityManager = getEntityManager();
        return entityManager.find(User.class,id);
    }

    @Override
    public void save(User user) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(user.getAddress());
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void deleteById(long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class,id);
        em.remove(user.getAddress());
        em.remove(user);
        em.getTransaction().commit();
    }
}
