package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServiceNature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nature;
    private boolean other;


    public ServiceNature(String nature, boolean other) {
        this.nature = nature;
        this.other = other;
    }

    public ServiceNature() {
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public boolean isOther() {
        return other;
    }

    public void setOther(boolean other) {
        this.other = other;
    }
}
