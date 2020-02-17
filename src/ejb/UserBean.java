package ejb;

import data.Notification;
import data.Service;
import data.User;
import data.UserResponse;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
        List<User> relatedUsers = getRelatedUsers(em,user);
        for (User user1:relatedUsers)
        {
            Notification notification = new Notification(user1, LocalDateTime.now(),"L'utilisateur "+user.getUserName()+
                    " a supprimé son compte");
            em.persist(notification);
        }
        em.remove(user);
        em.getTransaction().commit();
    }

    private List<User> getRelatedUsers(EntityManager em,User user)
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserResponse> cq = cb.createQuery(UserResponse.class);
        Root<UserResponse> rootEntry = cq.from(UserResponse.class);
        CriteriaQuery<UserResponse> all = cq.select(rootEntry);
        TypedQuery<UserResponse> allQuery = em.createQuery(all);

        ArrayList<User> users = new ArrayList<>();

        for (UserResponse userResponse:allQuery.getResultList())
        {
            if (userResponse.getUser().getId()==user.getId())
            {
                if (!checkIfExists(userResponse.getService().getUser(),users))
                    users.add(userResponse.getService().getUser());
            }
            if (userResponse.getService().getUser().getId()==user.getId())
            {
                if (!checkIfExists(userResponse.getService().getUser(),users))
                    users.add(userResponse.getUser());
            }
        }
        return users;
    }

    private boolean checkIfExists (User user,List<User> users)
    {
        for (User user1:users)
        {
            if (user1.getId()==user.getId())
                return true;
        }
        return false;
    }

}
