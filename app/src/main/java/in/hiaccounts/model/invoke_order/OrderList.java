
package in.hiaccounts.model.invoke_order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderList implements Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("invokeOrderNo")
    @Expose
    private String invokeOrderNo;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OrderList() {
    }

    /**
     * 
     * @param id
     * @param invokeOrderNo
     */
    public OrderList(Integer id, String invokeOrderNo) {
        super();
        this.id = id;
        this.invokeOrderNo = invokeOrderNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
