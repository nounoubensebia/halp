package data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class UserResponseNotification extends Notification {

    @OneToOne
    private UserResponse userResponse;

    @OneToOne
    public UserResponse getUserResponse() {
        return userResponse;
    }

    public UserResponseNotification() {
    }

    public UserResponseNotification(User user, LocalDateTime localDateTime, String message, UserResponse userResponse) {
        super(user, localDateTime, message);
        this.userResponse = userResponse;
    }



    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }
}
