
package in.hiaccounts.model.hiconnect;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cmp_REG_NO")
    @Expose
    private String cmpREGNO;
    @SerializedName("registrationid")
    @Expose
    private long registrationid;
    @SerializedName("companyname")
    @Expose
    private String companyname;
    @SerializedName("email")
    @Expose
    private String email;

    /**
     * No args constructor for use in serialization
     * 
     */
    public List() {
    }

    /**
     * 
     * @param cmpREGNO
     * @param email
     * @param address
     * @param name
     * @param companyname
     * @param registrationid
     */
    public List(String address, String name, String cmpREGNO, long registrationid, String companyname, String email) {
        super();
        this.address = address;
        this.name = name;
        this.cmpREGNO = cmpREGNO;
        this.registrationid = registrationid;
        this.companyname = companyname;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCmpREGNO() {
        return cmpREGNO;
    }

    public void setCmpREGNO(String cmpREGNO) {
        this.cmpREGNO = cmpREGNO;
    }

    public long getRegistrationid() {
        return registrationid;
    }

    public void setRegistrationid(long registrationid) {
        this.registrationid = registrationid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return companyname;
    }
}
