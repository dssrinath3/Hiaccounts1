package in.hiaccounts.hinext.inventory.model.inventorylocationtransfer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by administrator on 2/2/18.
 */

public class AddLocationTransferData implements Serializable {
    @SerializedName("locFromId")
    @Expose
    private LocFromId locFromId;
    @SerializedName("LTdetails")
    @Expose
    private List<LTdetail> lTdetails = null;
    @SerializedName("locToId")
    @Expose
    private LocToId locToId;
    @SerializedName("ilocTransDate")
    @Expose
    private String ilocTransDate;

    public LocFromId getLocFromId() {
        return locFromId;
    }

    public void setLocFromId(LocFromId locFromId) {
        this.locFromId = locFromId;
    }

    public List<LTdetail> getlTdetails() {
        return lTdetails;
    }

    public void setlTdetails(List<LTdetail> lTdetails) {
        this.lTdetails = lTdetails;
    }

    public LocToId getLocToId() {
        return locToId;
    }

    public void setLocToId(LocToId locToId) {
        this.locToId = locToId;
    }

    public String getIlocTransDate() {
        return ilocTransDate;
    }

    public void setIlocTransDate(String ilocTransDate) {
        this.ilocTransDate = ilocTransDate;
    }
}
