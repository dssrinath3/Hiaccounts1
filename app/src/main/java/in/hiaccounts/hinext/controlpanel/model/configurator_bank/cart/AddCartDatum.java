package in.hiaccounts.hinext.controlpanel.model.configurator_bank.cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by administrator on 30/1/18.
 */

public class AddCartDatum implements Serializable {

    @SerializedName("cartId")
    @Expose
    public Long cartId;
    @SerializedName("cartName")
    @Expose
    public String cartName;
    @SerializedName("userName")
    @Expose
    public String userName;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("apiName")
    @Expose
    public String apiName;
    @SerializedName("registeredDate")
    @Expose
    public String registeredDate;
    @SerializedName("expiryDate")
    @Expose
    public String expiryDate;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("currentPassword")
    @Expose
    public String currentPassword;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
}
