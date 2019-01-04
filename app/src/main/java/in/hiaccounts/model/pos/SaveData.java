
package in.hiaccounts.model.pos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveData {

    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("selectedItemsList")
    @Expose
    private List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("totalCheckOutamt")
    @Expose
    private double totalCheckOutamt;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("totalTaxAmt")
    @Expose
    private double totalTaxAmt;
    @SerializedName("taxType")
    @Expose
    private String taxType;
    @SerializedName("customerId")
    @Expose
    private long customerId;
    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("cutomerName")
    @Expose
    private String cutomerName;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("advancepayment")
    @Expose
    private boolean isAdvancePayment;



    @SerializedName("dateOfInvoice")
    @Expose
    private String dateOfInvoice;


    @SerializedName("from_reg")
    @Expose
    private String from_reg;


    @SerializedName("to_reg")
    @Expose
    private String to_reg;


    @SerializedName("type_doc")
    @Expose
    private String type_doc;


    @SerializedName("cessTotalTaxAmt")
    @Expose
    private String cessTotalTaxAmt;


    @SerializedName("supplierId")
    @Expose
    private long supplierId;

    @SerializedName("supplierEmail")
    @Expose
    private String supplierEmail;

    @SerializedName("supplierInvNo")
    @Expose
    private String supplierInvNo;

    @SerializedName("selfBuildInvoice")
    @Expose
    private boolean selfBuildInvoice;


    /**
     * No args constructor for use in serialization
     * 
     */
    public SaveData() {
    }

    /**
     * 
     * @param operation
     * @param selectedItemsList
     * @param paymentType
     * @param taxType
     * @param totalTaxAmt
     * @param totalCheckOutamt

     */
    public SaveData(String operation, List<SelectedItemsList> selectedItemsList, double totalCheckOutamt, String paymentType, double totalTaxAmt, String taxType) {
        super();
        this.operation = operation;
        this.selectedItemsList = selectedItemsList;
        this.totalCheckOutamt = totalCheckOutamt;
        this.paymentType = paymentType;
        this.totalTaxAmt = totalTaxAmt;
        this.taxType = taxType;
    }
    public SaveData(String operation, List<SelectedItemsList> selectedItemsList, double totalCheckOutamt, String paymentType, double totalTaxAmt, String taxType,long customerId,String customerEmail,String cutomerName) {
        super();
        this.operation = operation;
        this.selectedItemsList = selectedItemsList;
        this.totalCheckOutamt = totalCheckOutamt;
        this.paymentType = paymentType;
        this.totalTaxAmt = totalTaxAmt;
        this.taxType = taxType;
        this.customerId=customerId;
        this.cutomerName=cutomerName;
        this.customerEmail=customerEmail;
    }

    public boolean isAdvancePayment() {
        return isAdvancePayment;
    }

    public void setAdvancePayment(boolean advancePayment) {
        isAdvancePayment = advancePayment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<SelectedItemsList> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<SelectedItemsList> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public double getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(double totalCheckOutamt) {
        this.totalCheckOutamt = totalCheckOutamt;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getTotalTaxAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxAmt(double totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCutomerName() {
        return cutomerName;
    }

    public void setCutomerName(String cutomerName) {
        this.cutomerName = cutomerName;
    }

    public String getDateOfInvoice() {
        return dateOfInvoice;
    }

    public void setDateOfInvoice(String dateOfInvoice) {
        this.dateOfInvoice = dateOfInvoice;
    }

    public String getFrom_reg() {
        return from_reg;
    }

    public void setFrom_reg(String from_reg) {
        this.from_reg = from_reg;
    }

    public String getTo_reg() {
        return to_reg;
    }

    public void setTo_reg(String to_reg) {
        this.to_reg = to_reg;
    }

    public String getType_doc() {
        return type_doc;
    }

    public void setType_doc(String type_doc) {
        this.type_doc = type_doc;
    }

    public String getCessTotalTaxAmt() {
        return cessTotalTaxAmt;
    }

    public void setCessTotalTaxAmt(String cessTotalTaxAmt) {
        this.cessTotalTaxAmt = cessTotalTaxAmt;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierInvNo() {
        return supplierInvNo;
    }

    public void setSupplierInvNo(String supplierInvNo) {
        this.supplierInvNo = supplierInvNo;
    }

    public boolean isSelfBuildInvoice() {
        return selfBuildInvoice;
    }

    public void setSelfBuildInvoice(boolean selfBuildInvoice) {
        this.selfBuildInvoice = selfBuildInvoice;
    }
}
