
package in.hiaccounts.hinext.sales.model.invoke_draft;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InvokeDraftList implements Serializable{

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("invokeOrderNo")
    @Expose
    private String invokeOrderNo;

    /**
     * No args constructor for use in serialization
     *
     */
    public InvokeDraftList() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInvokeOrderNo() {
        return invokeOrderNo;
    }

    public void setInvokeOrderNo(String invokeOrderNo) {
        this.invokeOrderNo = invokeOrderNo;
    }

    @Override
    public String toString() {
        return invokeOrderNo;
    }
}
