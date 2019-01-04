
package in.hiaccounts.model.duplicate_invoicedata;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DuplicateInvoice implements Parcelable{

    public static final Creator<DuplicateInvoice> CREATOR
            = new Creator<DuplicateInvoice>() {
        public DuplicateInvoice createFromParcel(Parcel in) {
            return new DuplicateInvoice(in);
        }

        public DuplicateInvoice[] newArray(int size) {
            return new DuplicateInvoice[size];
        }
    };
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("formNo")
    @Expose
    private String formNo;
    @SerializedName("arbalance")
    @Expose
    private Double arbalance;
    @SerializedName("apBalance")
    @Expose
    private Double apBalance;
    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("supplierName")
    @Expose
    private String supplierName;
    @SerializedName("supplierEmail")
    @Expose
    private String supplierEmail;

    /**
     * No args constructor for use in serialization
     *
     */
    public DuplicateInvoice() {
    }

    /**
     *
     * @param customerName
     * @param amount
     * @param formNo
     */
    public DuplicateInvoice(Double amount, String customerName, String formNo, Double arbalance, Double apBalance, String customerEmail, String supplierName, String supplierEmail) {
        this.amount = amount;
        this.customerName = customerName;
        this.formNo = formNo;
        this.arbalance = arbalance;
        this.apBalance = apBalance;
        this.customerEmail = customerEmail;
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
    }

    /**
     * recreate object from parcel
     */
    private DuplicateInvoice(Parcel in) {
        amount = in.readDouble();
        customerName = in.readString();
        formNo = in.readString();
        customerEmail = in.readString();
    }

    public Double getAmount() {
        return amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getFormNo() {
        return formNo;
    }

    public Double getArbalance() {
        return arbalance;
    }

    public Double getApBalance() {
        return apBalance;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(amount);
        dest.writeString(customerName);
        dest.writeString(formNo);
        dest.writeString(customerEmail);
    }
}
