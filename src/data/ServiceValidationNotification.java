package data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ServiceValidationNotification extends Notification {

    @OneToOne
    private Service service;

    @OneToOne
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
