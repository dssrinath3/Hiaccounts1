
package in.hiaccounts.hinext.controlpanel.model.company_setup.chartsofaccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BalanceSheetAccnt implements Serializable {

    @SerializedName("icId")
    @Expose
    public IcId icId;
    @SerializedName("btId")
    @Expose
    public BtId btId;
    @SerializedName("apcode")
    @Expose
    public String apcode;
    @SerializedName("caam_accountid")
    @Expose
    public Object caamAccountid;
    @SerializedName("caaccountid")
    @Expose
    public Long caaccountid;
    @SerializedName("caag_id")
    @Expose
    public CaagId caagId;
    @SerializedName("coaCode")
    @Expose
    public String coaCode;
    @SerializedName("caaccount_code")
    @Expose
    public String caaccountCode;
    @SerializedName("caaccount_name")
    @Expose
    public String caaccountName;
    @SerializedName("castringAccountCode")
    @Expose
    public String castringAccountCode;
    @SerializedName("caflag")
    @Expose
    public Boolean caflag;


    public IcId getIcId() {
        return icId;
    }

    public void setIcId(IcId icId) {
        this.icId = icId;
    }

    public BtId getBtId() {
        return btId;
    }

    public void setBtId(BtId btId) {
        this.btId = btId;
    }

    public String getApcode() {
        return apcode;
    }

    public void setApcode(String apcode) {
        this.apcode = apcode;
    }

    public Object getCaamAccountid() {
        return caamAccountid;
    }

    public void setCaamAccountid(Object caamAccountid) {
        this.caamAccountid = caamAccountid;
    }

    public Long getCaaccountid() {
        return caaccountid;
    }

    public void setCaaccountid(Long caaccountid) {
        this.caaccountid = caaccountid;
    }

    public CaagId getCaagId() {
        return caagId;
    }

    public void setCaagId(CaagId caagId) {
        this.caagId = caagId;
    }

    public String getCoaCode() {
        return coaCode;
    }

    public void setCoaCode(String coaCode) {
        this.coaCode = coaCode;
    }

    public String getCaaccountCode() {
        return caaccountCode;
    }

    public void setCaaccountCode(String caaccountCode) {
        this.caaccountCode = caaccountCode;
    }

    public String getCaaccountName() {
        return caaccountName;
    }

    public void setCaaccountName(String caaccountName) {
        this.caaccountName = caaccountName;
    }

    public String getCastringAccountCode() {
        return castringAccountCode;
    }

    public void setCastringAccountCode(String castringAccountCode) {
        this.castringAccountCode = castringAccountCode;
    }

    public Boolean getCaflag() {
        return caflag;
    }

    public void setCaflag(Boolean caflag) {
        this.caflag = caflag;
    }
}
