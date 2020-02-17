package ejb;

import data.Notification;
import data.Service;
import data.ServiceValidationNotification;
import data.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public void save(Service service) throws TransactionException
    {
        try {
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
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

            em.getTransaction().commit();
        } catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
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
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.merge(service.getLocation());
            if (service.getServiceNature().isOther())
            {
                em.merge(service.getServiceNature());
            }
            em.merge(service);
            em.getTransaction().commit();
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
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
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
            message+="validée";
            Notification notification = new ServiceValidationNotification(service.getUser(), LocalDateTime.now(),
                    message,service);
            em.persist(notification);
            em.getTransaction().commit();
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
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            String message = "Votre ";
            Service service = em.find(Service.class,id);
            if (service.isOffer())
            {
                message += "offre de service";
            }
            else
            {
                message += "demande de service";
            }
            message+="ayant pour référence "+service.getReference()+" a été supprimée par l'administrateur";
            Notification notification = new Notification(service.getUser(),LocalDateTime.now(),message);
            em.persist(notification);
            em.remove(service);
            em.getTransaction().commit();
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
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            Service service = em.find(Service.class,id);
            em.remove(service);
            em.getTransaction().commit();
        }
        catch (Exception e)
        {
            TransactionException transactionException = new TransactionException("Transaction exception");
            transactionException.setStackTrace(e.getStackTrace());
            throw transactionException;
        }
    }

}
