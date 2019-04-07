package common.entity;

import org.hibernate.annotations.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="allvehcilecrash")
public class AllvehcileCrash  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String vehcileno ;
    String data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehcileno() {
        return vehcileno;
    }

    public void setVehcileno(String vehcileno) {
        this.vehcileno = vehcileno;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
