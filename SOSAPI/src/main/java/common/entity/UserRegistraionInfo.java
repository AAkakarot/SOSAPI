package common.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_reg_detail")
public class UserRegistraionInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Id
    private String phonenumber;

    private String name;
    private String otp;
    private String password;
    private Date createdAt;
    private Date updatedAt;
    private String af1;
    private String af2;
    private String af3;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAf3() {
        return af3;
    }

    public void setAf3(String af3) {
        this.af3 = af3;
    }


    public static UserRegistraionInfo build(String phonenumber){
        Date presentDate=new Date();
        UserRegistraionInfo userRegistraionInfo=new UserRegistraionInfo();
        userRegistraionInfo.setPhonenumber(phonenumber);
        userRegistraionInfo.setCreatedAt(presentDate);
        userRegistraionInfo.setUpdatedAt(presentDate);
        return userRegistraionInfo;
    }


}
