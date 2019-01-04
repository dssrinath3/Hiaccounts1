package in.hiaccounts.model.company;

/**
 * Created by Prateek on 5/17/2017.
 */

public class ContactUs {



    String companyName;
    String companyCountry;
    String companyAddress;
    String companContactNumber;

    public ContactUs(String companyName, String companyCountry, String companyAddress, String companContactNumber) {
        this.companyName = companyName;
        this.companyCountry = companyCountry;
        this.companyAddress = companyAddress;
        this.companContactNumber = companContactNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanContactNumber() {
        return companContactNumber;
    }

    public void setCompanContactNumber(String companContactNumber) {
        this.companContactNumber = companContactNumber;
    }
}
