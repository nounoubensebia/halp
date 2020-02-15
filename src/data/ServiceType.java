package data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ServiceType {

    private long id;
    private String name;

    public void setId(long id) {
        this.id = id;
    }

    @Id
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
