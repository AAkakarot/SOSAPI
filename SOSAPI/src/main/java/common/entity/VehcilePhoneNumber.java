package common.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "vehcile_phone_info")
public class VehcilePhoneNumber implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String phonenumber;

    private String vehcilenumber;
    private String vtype;

    private Date createdAt;
    private Date updatedAt;

    private String af1;

    private String af2;
    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }



    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getVehcilenumber() {
        return vehcilenumber;
    }

    public void setVehcilenumber(String vehcilenumber) {
        this.vehcilenumber = vehcilenumber;
    }


    public String getAf1() {
        return af1;
    }

    public void setAf1(String af1) {
        this.af1 = af1;
    }

    public String getAf2() {
        return af2;
    }

    public void setAf2(String af2) {
        this.af2 = af2;
    }





    public static VehcilePhoneNumber build(String phonenumber,String vehcileno,String vehciletype){
        Date presentDate=new Date();
        VehcilePhoneNumber vehcilePhoneNumber=new VehcilePhoneNumber();
        vehcilePhoneNumber.setPhonenumber(phonenumber);
        vehcilePhoneNumber.setVehcilenumber(vehcileno);
        vehcilePhoneNumber.setVtype(vehciletype);
        vehcilePhoneNumber.setCreatedAt(presentDate);
        vehcilePhoneNumber.setUpdatedAt(presentDate);
        return vehcilePhoneNumber;
    }


}
