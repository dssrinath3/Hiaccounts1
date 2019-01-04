package in.hiaccounts.hinext.controlpanel.model.configurator_bank.email_server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 8/21/2017.
 */

public class EmailServerDatum implements Serializable {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("smtpHost")
    @Expose
    private String smtpHost;
    @SerializedName("port")
    @Expose
    private String port;
    @SerializedName("forMail")
    @Expose
    private String forMail;
    @SerializedName("logoUrl")
    @Expose
    private Object logoUrl;
    @SerializedName("status")
    @Expose
    private Object status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getForMail() {
        return forMail;
    }

    public void setForMail(String forMail) {
        this.forMail = forMail;
    }

    public Object getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(Object logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }
}
