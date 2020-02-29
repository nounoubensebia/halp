package ejb;

import data.UserDetailsNotification;
import data.UserResponse;
import data.UserResponseNotification;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
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
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserResponseBean extends Repository<UserResponse> {

    @PersistenceContext
    EntityManager em;

    @Resource
    private SessionContext sessionContext;

    @Override
    public List<UserResponse> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserResponse> cq = cb.createQuery(UserResponse.class);
        Root<UserResponse> rootEntry = cq.from(UserResponse.class);
        CriteriaQuery<UserResponse> all = cq.select(rootEntry);
        TypedQuery<UserResponse> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public UserResponse findById(long id) {
        EntityManager entityManager = em;
        return entityManager.find(UserResponse.class,id);
    }

    @Override
    public void save(UserResponse userResponse) throws TransactionException {

        try {

            EntityManager entityManager = em;

            userResponse.getService().setStatus(2);
            entityManager.merge(userResponse.getService());


            // saving user response
            entityManager.persist(userResponse);

            //saving user response notification
            String message = "L'utilisateur ayant les informations suivantes<br>" + userResponse.getUser().getNotificationInfo()
                    + "<br>a répondu à votre ";
            if (userResponse.getService().isOffer()) {
                message += "offre de service ayant pour référence "+userResponse.getService().getReference();
            } else {
                message += "demande de service ayant pour référence "+userResponse.getService().getReference();
            }

                message+="<br>cet utilisateur vous a adréssé le message suivant : "+userResponse.getMessage();

            UserResponseNotification userResponseNotification = new UserResponseNotification(userResponse.getService().getUser(),
                    LocalDateTime.now(), message, userResponse);
            entityManager.persist(userResponseNotification);

            //saving user details notification
            message = "Vous avez répondu à ";
            if (userResponse.getService().isOffer()) {
                message += "l'offre de service";
            } else {
                message += "la demande de service";
            }
            message += " ayant pour référence "+ userResponse.getService().getReference()+
                    " de l'utilisateur ayant les informations suivantes : <br>" + userResponse.getUser().getNotificationInfo();

            UserDetailsNotification userDetailsNotification = new UserDetailsNotification(userResponse.getUser(),
                    LocalDateTime.now(), message, userResponse.getService().getUser());
            entityManager.persist(userDetailsNotification);


        }
        catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
            e.printStackTrace();
            throw transactionException;
        }

    }

    @Override
    public void deleteById(long id) throws TransactionException {
        try {
            UserResponse userResponse = em.find(UserResponse.class, id);
            em.remove(userResponse);
        } catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
            throw transactionException;
        }
    }
}
