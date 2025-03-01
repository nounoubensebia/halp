package data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Service service;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private LocalDateTime date;

    private String message;

    public UserResponse() {
    }

    public UserResponse(Service service, User user, LocalDateTime date, String message) {
        this.service = service;
        this.user = user;
        this.date = date;
        this.message = message;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
