package in.hiaccounts.hinext.item.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddedCartsList implements Serializable {
    @SerializedName("cartId")
    @Expose
    public Long cartId;
    @SerializedName("cartName")
    @Expose
    public String cartName;
    @SerializedName("userName")
    @Expose
    public Object userName;
    @SerializedName("password")
    @Expose
    public Object password;
    @SerializedName("url")
    @Expose
    public Object url;
    @SerializedName("apiName")
    @Expose
    public Object apiName;
    @SerializedName("registeredDate")
    @Expose
    public Object registeredDate;
    @SerializedName("expiryDate")
    @Expose
    public Object expiryDate;
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("currentPassword")
    @Expose
    public Object currentPassword;
    @SerializedName("email")
    @Expose
    public Object email;
    @SerializedName("hiconnectRegNo")
    @Expose
    public Object hiconnectRegNo;
    @SerializedName("companyId")
    @Expose
    public Object companyId;

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

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public Object getApiName() {
        return apiName;
    }

    public void setApiName(Object apiName) {
        this.apiName = apiName;
    }

    public Object getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Object registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Object getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Object expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(Object currentPassword) {
        this.currentPassword = currentPassword;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getHiconnectRegNo() {
        return hiconnectRegNo;
    }

    public void setHiconnectRegNo(Object hiconnectRegNo) {
        this.hiconnectRegNo = hiconnectRegNo;
    }

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
        this.companyId = companyId;
    }
}
