package in.hiaccounts.hinext.inventory.model.hsncode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 8/24/2017.
 */

public class HSNCodeData implements Serializable{

    @SerializedName("msiccomid")
    @Expose
    public Long msiccomid;
    @SerializedName("mscid")
    @Expose
    public Mscid mscid;
    @SerializedName("msiccode")
    @Expose
    public String msiccode;
    @SerializedName("descrip")
    @Expose
    public String descrip;
    @SerializedName("companyId")
    @Expose
    public Object companyId;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("status")
    @Expose
    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getMsiccomid() {
        return msiccomid;
    }

    public void setMsiccomid(Long msiccomid) {
        this.msiccomid = msiccomid;
    }

    public Mscid getMscid() {
        return mscid;
    }

    public void setMscid(Mscid mscid) {
        this.mscid = mscid;
    }

    public String getMsiccode() {
        return msiccode;
    }

    public void setMsiccode(String msiccode) {
        this.msiccode = msiccode;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
        this.companyId = companyId;
    }

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }
}
