
package in.hiaccounts.hinext.company_setup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BalanceAccountDatum implements Serializable{

    @SerializedName("icId")
    @Expose
    public IcId icId;
    @SerializedName("btId")
    @Expose
    public BtId btId;
    @SerializedName("apcode")
    @Expose
    public String apcode;
    @SerializedName("caaccountid")
    @Expose
    public Long caaccountid;
    @SerializedName("coaCode")
    @Expose
    public String coaCode;
    @SerializedName("caflag")
    @Expose
    public boolean caflag;
    @SerializedName("caag_id")
    @Expose
    public CaagId caagId;
    @SerializedName("castringAccountCode")
    @Expose
    public String castringAccountCode;
    @SerializedName("caaccount_name")
    @Expose
    public String caaccountName;
    @SerializedName("caaccount_code")
    @Expose
    public String caaccountCode;
    @SerializedName("caam_accountid")
    @Expose
    public Object caamAccountid;

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

    public Long getCaaccountid() {
        return caaccountid;
    }

    public void setCaaccountid(Long caaccountid) {
        this.caaccountid = caaccountid;
    }

    public String getCoaCode() {
        return coaCode;
    }

    public void setCoaCode(String coaCode) {
        this.coaCode = coaCode;
    }

    public boolean isCaflag() {
        return caflag;
    }

    public void setCaflag(boolean caflag) {
        this.caflag = caflag;
    }

    public CaagId getCaagId() {
        return caagId;
    }

    public void setCaagId(CaagId caagId) {
        this.caagId = caagId;
    }

    public String getCastringAccountCode() {
        return castringAccountCode;
    }

    public void setCastringAccountCode(String castringAccountCode) {
        this.castringAccountCode = castringAccountCode;
    }

    public String getCaaccountName() {
        return caaccountName;
    }

    public void setCaaccountName(String caaccountName) {
        this.caaccountName = caaccountName;
    }

    public String getCaaccountCode() {
        return caaccountCode;
    }

    public void setCaaccountCode(String caaccountCode) {
        this.caaccountCode = caaccountCode;
    }

    public Object getCaamAccountid() {
        return caamAccountid;
    }

    public void setCaamAccountid(Object caamAccountid) {
        this.caamAccountid = caamAccountid;
    }
}
