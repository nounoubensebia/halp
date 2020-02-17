package data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class UserDetailsNotification extends Notification {

    @OneToOne
    private User userDetails;

    public UserDetailsNotification() {
    }

    public UserDetailsNotification(User user, LocalDateTime localDateTime, String message, User user1) {
        super(user, localDateTime, message);
        this.userDetails = user1;
    }

    @OneToOne
    public User getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(User userDetails) {
        this.userDetails = userDetails;
    }
}
