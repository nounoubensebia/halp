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

    public User findByEmail(String email) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("email"), email));;
        TypedQuery<User> allQuery = entityManager.createQuery(all);
        if (allQuery.getResultList().size()>0)
            return allQuery.getResultList().get(0);
        else
            return null;
    }

    @Override
    public void save(User user) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(user.getAddress());
        em.persist(user);
        em.getTransaction().commit();
    }

    public void update(User user)
    {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.merge(user.getAddress());
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
