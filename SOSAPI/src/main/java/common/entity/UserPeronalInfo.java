package common.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="user_personal_info")
public class UserPeronalInfo implements Serializable {
    @Id
    private String phonenumber;          //primary key
    private String allvehcile;           // yet to set     //0
    private String allphonenumber;       //  yet to set     //0
    private String keyForFirebase;


    private String permission;
    private String allbarcode;            //yet to set
    private int reffered;                  // 0
    private int premiumstatus;             // 0
    private Date createdAt;
    private Date updatedAt;
    private String af1;
    private String af2;
    private String af3;

    public String getKeyForFirebase() {
        return keyForFirebase;
    }

    public void setKeyForFirebase(String keyForFirebase) {
        this.keyForFirebase = keyForFirebase;
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


    public int getReffered() {
        return reffered;
    }

    public void setReffered(int reffered) {
        this.reffered = reffered;
    }

    public int getPremiumstatus() {
        return premiumstatus;
    }

    public void setPremiumstatus(int premiumstatus) {
        this.premiumstatus = premiumstatus;
    }


    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAllvehcile() {
        return allvehcile;
    }

    public void setAllvehcile(String allvehcile) {
        this.allvehcile = allvehcile;
    }

    public String getAllphonenumber() {
        return allphonenumber;
    }

    public void setAllphonenumber(String allphonenumber) {
        this.allphonenumber = allphonenumber;
    }

    public String getAllbarcode() {
        return allbarcode;
    }

    public void setAllbarcode(String allbarcode) {
        this.allbarcode = allbarcode;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

public static UserPeronalInfo build(String phonenumber){
        Date d=new Date();
        UserPeronalInfo userPeronalInfo=new UserPeronalInfo();
        userPeronalInfo.setPhonenumber(phonenumber);
        userPeronalInfo.setCreatedAt(d);
        userPeronalInfo.setUpdatedAt(d);
        userPeronalInfo.setAllvehcile("0");
        userPeronalInfo.setAllphonenumber("0");
        userPeronalInfo.setPermission("");
        return userPeronalInfo;
}


}
