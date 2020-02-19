package data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private LocalDateTime localDateTime;

    private String message;

    private int status;

    public Notification() {
    }

    public Notification(User user, LocalDateTime localDateTime, String message) {
        this.user = user;
        this.localDateTime = localDateTime;
        this.message = message;
        this.status = 0;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
