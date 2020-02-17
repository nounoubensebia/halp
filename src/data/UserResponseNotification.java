package data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class UserResponseNotification extends Notification {

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserResponse userResponse;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
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
