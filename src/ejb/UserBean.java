package ejb;

import data.Notification;
import data.Service;
import data.User;
import data.UserResponse;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserBean extends Repository<User> {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<User> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);
        TypedQuery<User> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public User findById(long id) {
        EntityManager entityManager = em;
        return entityManager.find(User.class,id);
    }

    public User findByEmail(String email) {
        EntityManager entityManager = em;
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
    public void save(User user) throws TransactionException {
        try {
            em.persist(user.getAddress());
            em.persist(user);
        }
        catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
            throw transactionException;
        }
    }

    public void update(User user) throws TransactionException
    {
        try {
            em.merge(user);
            em.merge(user.getAddress());
        } catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
            throw transactionException;
        }
    }

    @Override
    public void deleteById(long id) throws TransactionException {
        try {
        User user = em.find(User.class,id);
        List<User> relatedUsers = getRelatedUsers(em,user);
        for (User user1:relatedUsers)
        {
            Notification notification = new Notification(user1, LocalDateTime.now(),"L'utilisateur "+user.getUserName()+
                    " a supprimé son compte");
            em.persist(notification);
        }
        em.remove(user);
        } catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
            throw transactionException;
        }

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
