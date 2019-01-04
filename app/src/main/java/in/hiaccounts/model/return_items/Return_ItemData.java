
package in.hiaccounts.model.return_items;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Return_ItemData implements Serializable {

    @SerializedName("siid")
    @Expose
    private int siid;
    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("selectedItemsList")
    @Expose
    private List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("totalCheckOutamt")
    @Expose
    private float totalCheckOutamt;
    @SerializedName("paymentType")
    @Expose
    private String paymentType;
    @SerializedName("totalTaxAmt")
    @Expose
    private double totalTaxAmt;
    @SerializedName("customerId")
    @Expose
    private Long customerId;

    @SerializedName("supplierId")
    @Expose
    private Long supplierId;

    @SerializedName("formNo")
    @Expose
    private String formNo;
    @SerializedName("totalCashPymtAmtReturned")
    @Expose
    private double totalCashPymtAmtReturned;



    /**
     * No args constructor for use in serialization
     * 
     */
    public Return_ItemData() {
    }

    /**
     * 
     * @param operation
     * @param customerId
     * @param selectedItemsList
     * @param paymentType
     * @param totalTaxAmt
     * @param siid
     * @param totalCheckOutamt
     */
    public Return_ItemData(int siid, String operation, List<SelectedItemsList> selectedItemsList, float totalCheckOutamt, String paymentType, double totalTaxAmt, Long customerId) {
        super();
        this.siid = siid;
        this.operation = operation;
        this.selectedItemsList = selectedItemsList;
        this.totalCheckOutamt = totalCheckOutamt;
        this.paymentType = paymentType;
        this.totalTaxAmt = totalTaxAmt;
        this.customerId = customerId;
    }

    public Return_ItemData(String operation, String paymentType, Long customerId, String formNo, double totalCashPymtAmtReturned) {
        this.operation = operation;
        this.paymentType = paymentType;
        this.customerId = customerId;
        this.formNo = formNo;
        this.totalCashPymtAmtReturned = totalCashPymtAmtReturned;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public double getTotalCashPymtAmtReturned() {
        return totalCashPymtAmtReturned;
    }

    public void setTotalCashPymtAmtReturned(double totalCashPymtAmtReturned) {
        this.totalCashPymtAmtReturned = totalCashPymtAmtReturned;
    }

    public int getSiid() {
        return siid;
    }

    public void setSiid(int siid) {
        this.siid = siid;
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

    public float getTotalCheckOutamt() {
        return totalCheckOutamt;
    }

    public void setTotalCheckOutamt(float totalCheckOutamt) {
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
