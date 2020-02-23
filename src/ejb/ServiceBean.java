package ejb;

import data.Notification;
import data.Service;
import data.ServiceValidationNotification;
import data.User;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ServiceBean extends Repository<Service> {


    @Resource
    private SessionContext sessionContext;

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Service> getAll()
    {
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
        EntityManager entityManager = em;
        return entityManager.find(Service.class,id);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(Service service) throws TransactionException
    {
        try {
            //EntityManager em = getEntityManager();
            //em.getTransaction().begin();
            em.persist(service.getLocation());
            if (service.getServiceNature().isOther())
            {
                em.persist(service.getServiceNature());
            }
            em.persist(service);
            String message = "L'utilisateur "+service.getUser().getUserName()+" a créé une nouvelle";
            if (service.isOffer())
            {
                message+= " offre de service";
            }
            else
            {
                message+= " demande de service";
            }
            message+=" ayant pour référence "+service.getReference();
            for (User admin:getAdmins(em))
            {

                ServiceValidationNotification serviceValidationNotification = new ServiceValidationNotification(
                        admin,LocalDateTime.now(),message,service);
                em.persist(serviceValidationNotification);
            }

            //em.getTransaction().commit();
        } catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
            e.printStackTrace();
            throw transactionException;
        }
    }

    public List<User> getAdmins(EntityManager em)
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);
        TypedQuery<User> allQuery = em.createQuery(all);

        ArrayList<User> admins = new ArrayList<>();
        for (User user:allQuery.getResultList())
        {
            if (user.isAdmin())
            {
                admins.add(user);
            }
        }
        return admins;
    }

    public void update (Service service) throws TransactionException
    {
        try {
            em.merge(service.getLocation());
            if (service.getServiceNature().isOther())
            {
                em.merge(service.getServiceNature());
            }
            em.merge(service);
        }
        catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
            throw transactionException;
        }
    }

    public void validate(Service service) throws TransactionException
    {
        try {

            
            service.setStatus(1);
            em.merge(service);
            String message = "Votre ";
            if (service.isOffer())
            {
                message+= "offre de service ";
            }
            else
            {
                message+= "demande de service ";
            }
            message+=" ayant pour référence "+ service.getReference()+" a été validée";
            Notification notification = new ServiceValidationNotification(service.getUser(), LocalDateTime.now(),
                    message,service);
            em.persist(notification);
        }
        catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
            throw transactionException;
        }
    }

    public void adminDelete(long id) throws TransactionException
    {
        try {
            String message = "Votre ";
            Service service = em.find(Service.class,id);
            if (service.isOffer())
            {
                message += "offre de service ";
            }
            else
            {
                message += "demande de service ";
            }
            message+="ayant pour référence "+service.getReference()+" a été supprimée par l'administrateur";
            Notification notification = new Notification(service.getUser(),LocalDateTime.now(),message);
            em.persist(notification);
            em.remove(service);
        }
        catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
            throw transactionException;
        }
    }

    @Override
    public void deleteById(long id) throws TransactionException
    {
        //TODO check cascade
        try {
            em.getTransaction().begin();
            Service service = em.find(Service.class,id);
            em.remove(service);
        }
        catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
            throw transactionException;
        }
    }

    @Schedule(second = "30", minute = "40", hour="02", dayOfWeek = "*", dayOfMonth = "*", year = "*", info = "MySchedular")
    public void verifyObsolete()
    {
        List<Service> services = getAll();
        for (Service service:services)
        {
            if (service.getStatus()!=3&&service.getEndDate().isBefore(LocalDateTime.now()))
            {
                service.setStatus(3);
                em.persist(service);
            }
        }
    }

    public List<Service> getUserServices (User user)
    {
        List<Service> all = getAll();
        List<Service> userServices = new ArrayList<>();
        for (Service service: all)
        {
            if (service.getUser().getId()==user.getId())
            {
                userServices.add(service);
            }
        }

        return userServices;

    }

}
