
package in.hiaccounts.hinext.controlpanel.model.configurator_bank.paymentmethod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FiscalYear implements Serializable {

    @SerializedName("begin")
    @Expose
    private long begin;
    @SerializedName("end")
    @Expose
    private long end;
    @SerializedName("close")
    @Expose
    private boolean close;
    @SerializedName("bufferPeriod")
    @Expose
    private long bufferPeriod;
    @SerializedName("bufferDate")
    @Expose
    private String bufferDate;
    @SerializedName("companyId")
    @Expose
    private CompanyId companyId;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;
    @SerializedName("fyId")
    @Expose
    private Long fyId;

    public long getBegin() {
        return begin;
    }

    public void setBegin(long begin) {
        this.begin = begin;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public long getBufferPeriod() {
        return bufferPeriod;
    }

    public void setBufferPeriod(long bufferPeriod) {
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
