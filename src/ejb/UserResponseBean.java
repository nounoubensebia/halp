package ejb;

import data.UserDetailsNotification;
import data.UserResponse;
import data.UserResponseNotification;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserResponseBean extends Repository<UserResponse> {


    @Override
    public List<UserResponse> getAll() {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserResponse> cq = cb.createQuery(UserResponse.class);
        Root<UserResponse> rootEntry = cq.from(UserResponse.class);
        CriteriaQuery<UserResponse> all = cq.select(rootEntry);
        TypedQuery<UserResponse> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public UserResponse findById(long id) {
        EntityManager entityManager = getEntityManager();
        return entityManager.find(UserResponse.class,id);
    }

    @Override
    public void save(UserResponse userResponse) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();

        userResponse.getService().setStatus(2);
        entityManager.merge(userResponse.getService());



        // saving user response
        entityManager.persist(userResponse);

        //saving user response notification
        String message = "L'utilisatuer "+userResponse.getUser().getUserName()+" a répondu à votre " ;
        if (userResponse.getService().isOffer())
        {
            message += "offre de service";
        }
        else
        {
            message += "demande de service";
        }
        UserResponseNotification userResponseNotification = new UserResponseNotification(userResponse.getService().getUser(),
                LocalDateTime.now(),message,userResponse);
        entityManager.persist(userResponseNotification);

        //saving user details notification
        message = "Vous avez répondu à ";
        if (userResponse.getService().isOffer())
        {
            message += "l'offre de service";
        }
        else
        {
            message += "la demande de service";
        }
        message+= " de l'utilisateur "+userResponse.getUser().getUserName();

        UserDetailsNotification userDetailsNotification = new UserDetailsNotification(userResponse.getUser(),
                LocalDateTime.now(),message,userResponse.getService().getUser());
        entityManager.persist(userDetailsNotification);



        entityManager.getTransaction().commit();

    }

    @Override
    public void deleteById(long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        UserResponse userResponse = em.find(UserResponse.class,id);
        em.remove(userResponse);
        em.getTransaction().commit();
    }
}
