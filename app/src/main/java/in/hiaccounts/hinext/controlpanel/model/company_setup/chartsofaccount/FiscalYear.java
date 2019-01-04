
package in.hiaccounts.hinext.controlpanel.model.company_setup.chartsofaccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FiscalYear  implements Serializable{

    @SerializedName("begin")
    @Expose
    public Long begin;
    @SerializedName("end")
    @Expose
    public Long end;
    @SerializedName("close")
    @Expose
    public Boolean close;
    @SerializedName("bufferPeriod")
    @Expose
    public Long bufferPeriod;
    @SerializedName("bufferDate")
    @Expose
    public String bufferDate;
    @SerializedName("companyId")
    @Expose
    public CompanyId companyId;
    @SerializedName("locationId")
    @Expose
    public Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Object useraccountId;
    @SerializedName("fyId")
    @Expose
    public Long fyId;

    public Long getBegin() {
        return begin;
    }

    public void setBegin(Long begin) {
        this.begin = begin;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Boolean getClose() {
        return close;
    }

    public void setClose(Boolean close) {
        this.close = close;
    }

    public Long getBufferPeriod() {
        return bufferPeriod;
    }

    public void setBufferPeriod(Long bufferPeriod) {
        this.bufferPeriod = bufferPeriod;
    }

    public String getBufferDate() {
        return bufferDate;
    }

    public void setBufferDate(String bufferDate) {
        this.bufferDate = bufferDate;
    }

    public CompanyId getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyId companyId) {
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

    public Long getFyId() {
        return fyId;
    }

    public void setFyId(Long fyId) {
        this.fyId = fyId;
    }
}
