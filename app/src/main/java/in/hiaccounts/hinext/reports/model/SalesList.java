package in.hiaccounts.hinext.reports.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesList {
    @SerializedName("sqno")
    @Expose
    public String sqno;
    @SerializedName("sqid")
    @Expose
    public Long sqid;
    @SerializedName("sqdate")
    @Expose
    public String sqdate;

    public String getSqno() {
        return sqno;
    }

    public void setSqno(String sqno) {
        this.sqno = sqno;
    }

    public Long getSqid() {
        return sqid;
    }

    public void setSqid(Long sqid) {
        this.sqid = sqid;
    }

    public String getSqdate() {
        return sqdate;
    }

    public void setSqdate(String sqdate) {
        this.sqdate = sqdate;
    }

    @Override
    public String toString() {
        return sqno;
    }
}
