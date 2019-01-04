package in.hiaccounts.hinext.restaurant.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 12/16/2017.
 */

public class RestaurantTokenRecord implements Serializable {

    @SerializedName("siNo")
    @Expose
    public String siNo;
    @SerializedName("token")
    @Expose
    public String token;

    public String getSiNo() {
        return siNo;
    }

    public void setSiNo(String siNo) {
        this.siNo = siNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
