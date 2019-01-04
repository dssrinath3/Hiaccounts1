
package in.hiaccounts.model.hiconnect;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyData {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("list")
    @Expose
    private java.util.List<List> list = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CompanyData() {
    }

    /**
     * 
     * @param status
     * @param list
     */
    public CompanyData(String status, java.util.List<List> list) {
        super();
        this.status = status;
        this.list = list;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

}
