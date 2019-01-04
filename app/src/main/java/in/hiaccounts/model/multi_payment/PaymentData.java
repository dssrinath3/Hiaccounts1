
package in.hiaccounts.model.multi_payment;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentData implements Serializable {


    @SerializedName("from_reg")
    private String from_reg;
    @SerializedName("to_reg")
    private String to_reg;


    @SerializedName("type_doc")
    private String type_doc;


    @SerializedName("dateOfInvoice")
    @Expose
    private String dateOfInvoice;


    @SerializedName("cessTotalTaxAmt")
    @Expose
    private String cessTotalTaxAmt;




    @SerializedName("selectedItemsList")
    @Expose
    private List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("cashPayment")
    @Expose
    private CashPayment cashPayment;
    @SerializedName("creditPayment")
    @Expose
    private CreditPayment creditPayment;
    @SerializedName("voucherPayment")
    @Expose
    private VoucherPayment voucherPayment;
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
    @SerializedName("amountReturned")
    @Expose
    private double amountReturned;
    @SerializedName("discountAmount")
    @Expose
    private double discountAmount;
    @SerializedName("totalTenderedAmount")
    @Expose
    private double totalTenderedAmount;

    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("advancepayment")
    @Expose
    private boolean advancepayment;

    @SerializedName("supplierId")
    @Expose
    private Long supplierId;

    @SerializedName("supplierName")
    @Expose
    private String supplierName;

    @SerializedName("supplierEmail")
    @Expose
    private String supplierEmail;


    @SerializedName("supplierInvNo")
    @Expose
    private String supplierInvNo;

    @SerializedName("selfBuildInvoice")
    @Expose
    private boolean selfBuildInvoice;




    private final static long serialVersionUID = -1850864457513415081L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PaymentData() {
    }

    /**
     * 
     * @param creditPayment
     * @param voucherPayment
     * @param cashPayment
     * @param customerId
     * @param selectedItemsList
     * @param amountReturned
     * @param paymentType
     * @param taxType
     * @param totalTaxAmt
     * @param totalCheckOutamt
     * @param customerEmail
     * @param discountAmount
     * @param cutomerName
     */
    public PaymentData(List<SelectedItemsList> selectedItemsList, CashPayment cashPayment, CreditPayment creditPayment, VoucherPayment voucherPayment, double totalCheckOutamt, String paymentType, double totalTaxAmt, String taxType, long customerId, String customerEmail, String cutomerName, double amountReturned, double discountAmount,double totalTenderedAmount) {
        super();
        this.selectedItemsList = selectedItemsList;
        this.cashPayment = cashPayment;
        this.creditPayment = creditPayment;
        this.voucherPayment = voucherPayment;
        this.totalCheckOutamt = totalCheckOutamt;
        this.paymentType = paymentType;
        this.totalTaxAmt = totalTaxAmt;
        this.taxType = taxType;
        this.customerId = customerId;
        this.customerEmail = customerEmail;
        this.cutomerName = cutomerName;
        this.amountReturned = amountReturned;
        this.discountAmount = discountAmount;
        this.totalTenderedAmount=totalTenderedAmount;
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

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public boolean isAdvancepayment() {
        return advancepayment;
    }

    public void setAdvancepayment(boolean advancepayment) {
        this.advancepayment = advancepayment;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public double getTotalTenderedAmount() {
        return totalTenderedAmount;
    }

    public void setTotalTenderedAmount(double totalTenderedAmount) {
        this.totalTenderedAmount = totalTenderedAmount;
    }

    public List<SelectedItemsList> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<SelectedItemsList> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }

    public CashPayment getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(CashPayment cashPayment) {
        this.cashPayment = cashPayment;
    }

    public CreditPayment getCreditPayment() {
        return creditPayment;
    }

    public void setCreditPayment(CreditPayment creditPayment) {
        this.creditPayment = creditPayment;
    }

    public VoucherPayment getVoucherPayment() {
        return voucherPayment;
    }

    public void setVoucherPayment(VoucherPayment voucherPayment) {
        this.voucherPayment = voucherPayment;
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

    public double getAmountReturned() {
        return amountReturned;
    }

    public void setAmountReturned(double amountReturned) {
        this.amountReturned = amountReturned;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getSupplierInvNo() {
        return supplierInvNo;
    }

    public void setSupplierInvNo(String supplierInvNo) {
        this.supplierInvNo = supplierInvNo;
    }

    public String getCessTotalTaxAmt() {
        return cessTotalTaxAmt;
    }

    public void setCessTotalTaxAmt(String cessTotalTaxAmt) {
        this.cessTotalTaxAmt = cessTotalTaxAmt;
    }

    public boolean isSelfBuildInvoice() {
        return selfBuildInvoice;
    }

    public void setSelfBuildInvoice(boolean selfBuildInvoice) {
        this.selfBuildInvoice = selfBuildInvoice;
    }
}
