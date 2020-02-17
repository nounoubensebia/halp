package data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import utils.StringUtils;

import javax.persistence.*;
import java.nio.charset.Charset;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private String reference;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime creationDate;
    private String shortDescription;
    private String longDescription;
    private boolean isOffer;
    private int status;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Location location;

    private ServiceType serviceType;

    @OneToOne
    private ServiceNature serviceNature;

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @OneToOne(cascade = CascadeType.REMOVE)
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @OneToOne
    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    @OneToOne
    public ServiceNature getServiceNature() {
        return serviceNature;
    }

    public void setServiceNature(ServiceNature serviceNature) {
        this.serviceNature = serviceNature;
    }

    public boolean isOffer() {
        return isOffer;
    }

    public void setOffer(boolean offer) {
        isOffer = offer;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Service(User user, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime creationDate, String shortDescription, String longDescription, boolean isOffer, int status, Location location, ServiceType serviceType, ServiceNature serviceNature) {
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.creationDate = creationDate;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.isOffer = isOffer;
        this.status = status;
        this.location = location;
        this.serviceType = serviceType;
        this.serviceNature = serviceNature;
        this.reference = StringUtils.randomString(6);
    }

    public Service() {
    }
}
