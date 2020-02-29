package data;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class ServiceDeletionNotification extends Notification {

    private String adminMessage;

    public ServiceDeletionNotification() {
    }

    public ServiceDeletionNotification(User user, LocalDateTime localDateTime, String message, String adminMessage) {
        super(user, localDateTime, message);
        this.adminMessage = adminMessage;
    }

    public String getAdminMessage() {
        return adminMessage;
    }

    public void setAdminMessage(String adminMessage) {
        this.adminMessage = adminMessage;
    }
}
