package in.hiaccounts.hinext.sales.model.sales_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by administrator on 3/2/18.
 */

public class ResponseDataFromServer {
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("selectedItemsList")
    @Expose
    private List<SelectedItemsList> selectedItemsList = null;
    @SerializedName("taxSummaryList")
    @Expose
    private List<Object> taxSummaryList = null;
    @SerializedName("multiPartialPaymentList")
    @Expose
    private List<Object> multiPartialPaymentList = null;
    @SerializedName("operation")
    @Expose
    private Object operation;
    @SerializedName("cashPayment")
    @Expose
    private Object cashPayment;
    @SerializedName("creditPayment")
    @Expose
    private Object creditPayment;
    @SerializedName("voucherPayment")
    @Expose
    private Object voucherPayment;
    @SerializedName("multiPayment")
    @Expose
    private Object multiPayment;
    @SerializedName("bankPayment")
    @Expose
    private Object bankPayment;
    @SerializedName("redeemPayment")
    @Expose
    private Object redeemPayment;
    @SerializedName("hiPosServiceCharge")
    @Expose
    private Double hiPosServiceCharge;
    @SerializedName("paymentType")
    @Expose
    private Object paymentType;
    @SerializedName("totalCheckOutamt")
    @Expose
    private Double totalCheckOutamt;
    @SerializedName("totalTaxAmt")
    @Expose
    private Double totalTaxAmt;
    @SerializedName("cessTotalTaxAmt")
    @Expose
    private Double cessTotalTaxAmt;
    @SerializedName("itemCount")
    @Expose
    private Integer itemCount;
    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("taxType")
    @Expose
    private Object taxType;
    @SerializedName("siid")
    @Expose
    private Integer siid;
    @SerializedName("customerEmail")
    @Expose
    private Object customerEmail;
    @SerializedName("totalServiceCharge")
    @Expose
    private Double totalServiceCharge;
    @SerializedName("siNo")
    @Expose
    private Object siNo;
    @SerializedName("srlnNo")
    @Expose
    private Object srlnNo;
    @SerializedName("cutomerName")
    @Expose
    private Object cutomerName;
    @SerializedName("companydupData")
    @Expose
    private Object companydupData;
    @SerializedName("companyData")
    @Expose
    private Object companyData;
    @SerializedName("balanceAmount")
    @Expose
    private Double balanceAmount;
    @SerializedName("customer")
    @Expose
    private Object customer;
    @SerializedName("discountAmount")
    @Expose
    private Double discountAmount;
    @SerializedName("totalTenderedAmount")
    @Expose
    private Double totalTenderedAmount;
    @SerializedName("userName")
    @Expose
    private Object userName;
    @SerializedName("printType")
    @Expose
    private Object printType;
    @SerializedName("billToAddress")
    @Expose
    private Object billToAddress;
    @SerializedName("shipToAddress")
    @Expose
    private Object shipToAddress;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("phoneNumber")
    @Expose
    private Object phoneNumber;
    @SerializedName("inventoryAddress")
    @Expose
    private Object inventoryAddress;
    @SerializedName("inventoryContactNo")
    @Expose
    private Object inventoryContactNo;
    @SerializedName("inventoryEmail")
    @Expose
    private Object inventoryEmail;
    @SerializedName("inventoryFax")
    @Expose
    private Object inventoryFax;
    @SerializedName("advancepayment")
    @Expose
    private Object advancepayment;
    @SerializedName("formNo")
    @Expose
    private Object formNo;
    @SerializedName("totalCashPymtAmtReturned")
    @Expose
    private Object totalCashPymtAmtReturned;
    @SerializedName("totalChequePymtAmtReturned")
    @Expose
    private Object totalChequePymtAmtReturned;
    @SerializedName("totalVoucherPymtAmtReturned")
    @Expose
    private Object totalVoucherPymtAmtReturned;
    @SerializedName("chequeNumber")
    @Expose
    private Object chequeNumber;
    @SerializedName("voucherNumber")
    @Expose
    private Object voucherNumber;
    @SerializedName("salesOrderNo")
    @Expose
    private Object salesOrderNo;
    @SerializedName("salesDeliveryOrderNO")
    @Expose
    private Object salesDeliveryOrderNO;
    @SerializedName("totalActualWeight")
    @Expose
    private Double totalActualWeight;
    @SerializedName("totalSellableWeight")
    @Expose
    private Double totalSellableWeight;
    @SerializedName("from_reg")
    @Expose
    private Object fromReg;
    @SerializedName("to_reg")
    @Expose
    private Object toReg;
    @SerializedName("user_id")
    @Expose
    private Object userId;
    @SerializedName("type_doc")
    @Expose
    private Object typeDoc;
    @SerializedName("type_flag")
    @Expose
    private Object typeFlag;
    @SerializedName("recieptFooter")
    @Expose
    private Object recieptFooter;
    @SerializedName("companyLogoPath")
    @Expose
    private Object companyLogoPath;
    @SerializedName("cmpyLocation")
    @Expose
    private Object cmpyLocation;
    @SerializedName("custLocation")
    @Expose
    private Object custLocation;
    @SerializedName("termsId")
    @Expose
    private Integer termsId;
    @SerializedName("dateOfInvoice")
    @Expose
    private Object dateOfInvoice;
    @SerializedName("agentIdOfInvoice")
    @Expose
    private Object agentIdOfInvoice;
    @SerializedName("projectIdOfInvoice")
    @Expose
    private Object projectIdOfInvoice;
    @SerializedName("termCondIdOfInvoice")
    @Expose
    private Object termCondIdOfInvoice;
    @SerializedName("currencyIdOfInvoice")
    @Expose
    private Object currencyIdOfInvoice;
    @SerializedName("exchangeRateIdOfInvoice")
    @Expose
    private Object exchangeRateIdOfInvoice;
    @SerializedName("totalCashPayment")
    @Expose
    private Double totalCashPayment;
    @SerializedName("totalVoucherPayment")
    @Expose
    private Double totalVoucherPayment;
    @SerializedName("totalCardPayment")
    @Expose
    private Double totalCardPayment;
    @SerializedName("customerAddress")
    @Expose
    private Object customerAddress;
    @SerializedName("customerState")
    @Expose
    private Object customerState;
    @SerializedName("customerGst")
    @Expose
    private Object customerGst;
    @SerializedName("locationAddress")
    @Expose
    private Object locationAddress;
    @SerializedName("locationState")
    @Expose
    private Object locationState;
    @SerializedName("locationGst")
    @Expose
    private Object locationGst;
    @SerializedName("salesOrderId")
    @Expose
    private Object salesOrderId;
    @SerializedName("salesOrderDetailsId")
    @Expose
    private Object salesOrderDetailsId;
    @SerializedName("memo")
    @Expose
    private Object memo;
    @SerializedName("returnReason")
    @Expose
    private Object returnReason;
    @SerializedName("totalRemaininBalance")
    @Expose
    private Object totalRemaininBalance;
    @SerializedName("shippingDate")
    @Expose
    private Object shippingDate;
    @SerializedName("shippingDateString")
    @Expose
    private Object shippingDateString;
    @SerializedName("shippingReferenceNo")
    @Expose
    private Object shippingReferenceNo;
    @SerializedName("referenceNo")
    @Expose
    private Object referenceNo;
    @SerializedName("shippingmethodId")
    @Expose
    private Object shippingmethodId;
    @SerializedName("salesQuotationId")
    @Expose
    private Object salesQuotationId;
    @SerializedName("proFormaId")
    @Expose
    private Object proFormaId;
    @SerializedName("proFormaDetailsId")
    @Expose
    private Object proFormaDetailsId;
    @SerializedName("totalVoucherAmt")
    @Expose
    private Double totalVoucherAmt;
    @SerializedName("totalCreditCardAmt")
    @Expose
    private Double totalCreditCardAmt;
    @SerializedName("totalCashAmt")
    @Expose
    private Double totalCashAmt;
    @SerializedName("patientId")
    @Expose
    private Object patientId;
    @SerializedName("serviceCharge")
    @Expose
    private Object serviceCharge;
    @SerializedName("agentName")
    @Expose
    private Object agentName;
    @SerializedName("exchangerateValue")
    @Expose
    private Object exchangerateValue;
    @SerializedName("tc_value")
    @Expose
    private Object tcValue;
    @SerializedName("salesQuotationDetailsId")
    @Expose
    private Object salesQuotationDetailsId;
    @SerializedName("otherContactId")
    @Expose
    private Object otherContactId;
    @SerializedName("invoiceDate")
    @Expose
    private Object invoiceDate;
    @SerializedName("invoiceAmt")
    @Expose
    private Double invoiceAmt;
    @SerializedName("incrementAmt")
    @Expose
    private Double incrementAmt;
    @SerializedName("employeeId")
    @Expose
    private Object employeeId;
    @SerializedName("tokenNo")
    @Expose
    private Double tokenNo;
    @SerializedName("table")
    @Expose
    private Object table;
    @SerializedName("invoiceNo")
    @Expose
    private Object invoiceNo;
    @SerializedName("formDate")
    @Expose
    private Object formDate;
    @SerializedName("projectName")
    @Expose
    private Object projectName;
    @SerializedName("shippingMethodName")
    @Expose
    private Object shippingMethodName;
    @SerializedName("invokeOrderId")
    @Expose
    private Object invokeOrderId;
    @SerializedName("invokeOrderName")
    @Expose
    private Object invokeOrderName;
    @SerializedName("generatedVoucherNo")
    @Expose
    private Object generatedVoucherNo;
    @SerializedName("customerPo")
    @Expose
    private Object customerPo;
    @SerializedName("serviceDeliveryId")
    @Expose
    private Object serviceDeliveryId;
    @SerializedName("exportInvoice")
    @Expose
    private Object exportInvoice;
    @SerializedName("invokeRemaningQty")
    @Expose
    private Double invokeRemaningQty;
    @SerializedName("amtToBePaid")
    @Expose
    private Double amtToBePaid;
    @SerializedName("siStatus")
    @Expose
    private Object siStatus;
    @SerializedName("checkForHoldStock")
    @Expose
    private Boolean checkForHoldStock;
    @SerializedName("currencyObj")
    @Expose
    private Object currencyObj;
    @SerializedName("otherDetailsDto")
    @Expose
    private Object otherDetailsDto;
    @SerializedName("customerObj")
    @Expose
    private Object customerObj;
    @SerializedName("roundingAdj")
    @Expose
    private Double roundingAdj;
    @SerializedName("totalAmt")
    @Expose
    private Double totalAmt;
    @SerializedName("tcsAmount")
    @Expose
    private Object tcsAmount;
    @SerializedName("tdsAmount")
    @Expose
    private Object tdsAmount;
    @SerializedName("deliveryOrderId")
    @Expose
    private Object deliveryOrderId;
    @SerializedName("deliveryOrderIdDetails")
    @Expose
    private Object deliveryOrderIdDetails;
    @SerializedName("amountReturned")
    @Expose
    private Double amountReturned;
    @SerializedName("arbalance")
    @Expose
    private Object arbalance;

    public List<SelectedItemsList> getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(List<SelectedItemsList> selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
    }
}
