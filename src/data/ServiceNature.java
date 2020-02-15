package data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ServiceNature {
    private long id;
    private String nature;
    private String natureIfOther;

    public void setId(long id) {
        this.id = id;
    }

    @Id
    public long getId() {
        return id;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getNatureIfOther() {
        return natureIfOther;
    }

    public void setNatureIfOther(String natureIfOther) {
        this.natureIfOther = natureIfOther;
    }
}
