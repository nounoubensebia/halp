package data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String userName;
    private String phone;
    private String password;
    private String description;
    private boolean isAdmin;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @OneToOne(cascade = {CascadeType.REMOVE} )
    private Address address;

    public User() {
    }

    public User(String email, String firstName, String lastName, String userName, String phone, String password, String description, boolean isAdmin, Address address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.phone = phone;
        this.password = password;
        this.description = description;
        this.isAdmin = isAdmin;
        this.address = address;
    }



    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @OneToOne(cascade = {CascadeType.REMOVE})
    public Address getAddress() {
        return address;
    }


    public void setAddress(Address address) {
        this.address = address;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotificationInfo()
    {
        String s = "Nom : "+lastName;
        s+="<br>Prénom : "+firstName;
        s+="<br>Pseudonyme : "+userName;
        s+="<br>Email : "+email;
        s+="<br>Téléphone : "+phone;
        s+="<br>Adresse : "+address.getStreet()+", "+address.getCity()+", "+address.getSupplement();
        s+="<br>Description personnel : "+description;
        return s;
    }
}
