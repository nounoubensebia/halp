package data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class UserResponseNotification extends Notification {

    @OneToOne
    private UserResponse userResponse;

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }
}
