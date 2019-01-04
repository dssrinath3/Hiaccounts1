package in.hiaccounts.model.login;

/**
 * Created by Prateek on 5/6/2017.
 */

public class CompanyDetail {

    String companyEmial;
    String userName;
    String password;

    public CompanyDetail(String companyEmial, String userName, String password) {
        this.companyEmial = companyEmial;
        this.userName = userName;
        this.password = password;
    }

    public String getCompanyEmial() {
        return companyEmial;
    }

    public void setCompanyEmial(String companyEmial) {
        this.companyEmial = companyEmial;
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
}
