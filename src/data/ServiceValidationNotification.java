package data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class ServiceValidationNotification extends Notification {

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Service service;

    public ServiceValidationNotification() {
    }

    public ServiceValidationNotification(User user, LocalDateTime localDateTime, String message, Service service) {
        super(user, localDateTime, message);
        this.service = service;
    }

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
