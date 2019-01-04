package in.hiaccounts.model.login;

import java.io.Serializable;

/**
 * Created by Prateek on 2/25/2017.
 */

public class LoginData implements Serializable{

    String base_url;
    String port_number;
    String application_name;
    String password;
    String userName;
    String companyEmail;


    public LoginData() {
    }


    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public String getPort_number() {
        return port_number;
    }

    public void setPort_number(String port_number) {
        this.port_number = port_number;
    }

    public String getApplication_name() {
        return application_name;
    }

    public void setApplication_name(String application_name) {
        this.application_name = application_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }
}
