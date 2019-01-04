package in.hiaccounts.hinext.customer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 9/4/2017.
 */

public class RequestParamData implements Serializable {

    @SerializedName("params")
    @Expose
    public RequestParam params;

    public RequestParam getParams() {
        return params;
    }

    public void setParams(RequestParam params) {
        this.params = params;
    }
}
