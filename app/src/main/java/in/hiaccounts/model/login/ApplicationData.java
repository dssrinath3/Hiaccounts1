package in.hiaccounts.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 5/20/2017.
 */

public class ApplicationData implements Serializable{

    @SerializedName("companyName")
    @Expose
    String companyName;

    @SerializedName("logoPath")
    @Expose
    String logoPath;

    @SerializedName("fullUserName")
    @Expose
    String fullUserName;

    @SerializedName("customerDTO")
    @Expose
    Customer customerDTO;

    @SerializedName("supplierDTO")
    @Expose
    Supplier supplierDTO;


    public ApplicationData(String companyName, String logoPath, String fullUserName, Customer customerDTO, Supplier supplierDTO) {
        this.companyName = companyName;
        this.logoPath = logoPath;
        this.fullUserName = fullUserName;
        this.customerDTO = customerDTO;
        this.supplierDTO = supplierDTO;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getFullUserName() {
        return fullUserName;
    }

    public void setFullUserName(String fullUserName) {
        this.fullUserName = fullUserName;
    }

    public Customer getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(Customer customerDTO) {
        this.customerDTO = customerDTO;
    }

    public Supplier getSupplierDTO() {
        return supplierDTO;
    }

    public void setSupplierDTO(Supplier supplierDTO) {
        this.supplierDTO = supplierDTO;
    }
}
