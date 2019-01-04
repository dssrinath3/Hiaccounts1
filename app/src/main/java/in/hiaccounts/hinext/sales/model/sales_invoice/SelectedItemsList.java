package in.hiaccounts.hinext.sales.model.sales_invoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.hiaccounts.hinext.sales.model.checkout.UomConvertorList;

/**
 * Created by administrator on 3/2/18.
 */

public class SelectedItemsList {
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("purchaseInvoiceDetailID")
    @Expose
    private long purchaseInvoiceDetailID;
    @SerializedName("itemId")
    @Expose
    private Long itemId;
    @SerializedName("unitPrice")
    @Expose
    private double unitPrice;
    @SerializedName("qty")
    @Expose
    private long qty;
    @SerializedName("returnQty")
    @Expose
    private long returnQty;
    @SerializedName("remainingQty")
    @Expose
    private long remainingQty;
    @SerializedName("amtexclusivetax")
    @Expose
    private double amtexclusivetax;
    @SerializedName("taxid")
    @Expose
    private Long taxid;
    @SerializedName("taxpercent")
    @Expose
    private double taxpercent;
    @SerializedName("taxamt")
    @Expose
    private double taxamt;
    @SerializedName("amtinclusivetax")
    @Expose
    private double amtinclusivetax;
    @SerializedName("discountAmt")
    @Expose
    private double discountAmt;
    @SerializedName("itemDescription")
    @Expose
    private String itemDescription;
    @SerializedName("taxName")
    @Expose
    private String taxName;
    @SerializedName("serializableItemId")
    @Expose
    private Object serializableItemId;
    @SerializedName("serializableNumbers")
    @Expose
    private Object serializableNumbers;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("makingCharge")
    @Expose
    private double makingCharge;
    @SerializedName("actualWeight")
    @Expose
    private double actualWeight;
    @SerializedName("cess")
    @Expose
    private double cess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cgstsgstsplitvalues")
    @Expose
    private double cgstsgstsplitvalues;
    @SerializedName("taxPercentageSplit")
    @Expose
    private double taxPercentageSplit;
    @SerializedName("cessTaxAmt")
    @Expose
    private double cessTaxAmt;
    @SerializedName("hsnCode")
    @Expose
    private String hsnCode;
    @SerializedName("salesOrderId")
    @Expose
    private Object salesOrderId;
    @SerializedName("salesOrderDetailsId")
    @Expose
    private Object salesOrderDetailsId;
    @SerializedName("posamtexclusivetax")
    @Expose
    private double posamtexclusivetax;
    @SerializedName("posamtinclusivetax")
    @Expose
    private double posamtinclusivetax;
    @SerializedName("receiveItemFlag")
    @Expose
    private Boolean receiveItemFlag;
    @SerializedName("receiveItemId")
    @Expose
    private Object receiveItemId;
    @SerializedName("receiveItemFormNo")
    @Expose
    private Object receiveItemFormNo;
    @SerializedName("taxCode")
    @Expose
    private Object taxCode;
    @SerializedName("salesQuotationId")
    @Expose
    private Object salesQuotationId;
    @SerializedName("salesQuotationDetailsId")
    @Expose
    private Object salesQuotationDetailsId;
    @SerializedName("inclusiveJSON")
    @Expose
    private String inclusiveJSON;
    @SerializedName("purchaseQuotationId")
    @Expose
    private Object purchaseQuotationId;
    @SerializedName("purchaseOrderId")
    @Expose
    private Object purchaseOrderId;
    @SerializedName("salesOrder")
    @Expose
    private Object salesOrder;
    @SerializedName("batchNo")
    @Expose
    private Object batchNo;
    @SerializedName("batchExpDate")
    @Expose
    private Object batchExpDate;
    @SerializedName("serializableIMEINo")
    @Expose
    private Object serializableIMEINo;
    @SerializedName("batchNoList")
    @Expose
    private Object batchNoList;
    @SerializedName("uomConvertorList")
    @Expose
    private List<UomConvertorList> uomConvertorList = null;
    @SerializedName("serializeNoList")
    @Expose
    private Object serializeNoList;
    @SerializedName("serializableItemsDTOs")
    @Expose
    private Object serializableItemsDTOs;
    @SerializedName("serializableItemsDTOList")
    @Expose
    private Object serializableItemsDTOList;
    @SerializedName("uom")
    @Expose
    private String uom;
    @SerializedName("uomName")
    @Expose
    private String uomName;
    @SerializedName("uomConvertorId")
    @Expose
    private Integer uomConvertorId;
    @SerializedName("uomValue")
    @Expose
    private Object uomValue;
    @SerializedName("convertedReturnQty")
    @Expose
    private Double convertedReturnQty;
    @SerializedName("uomId")
    @Expose
    private Object uomId;
    @SerializedName("sIItemId")
    @Expose
    private Object sIItemId;
    @SerializedName("convertedName")
    @Expose
    private Object convertedName;
    @SerializedName("assertId")
    @Expose
    private Object assertId;
    @SerializedName("uomConvertor")
    @Expose
    private Object uomConvertor;
    @SerializedName("customerName")
    @Expose
    private Object customerName;
    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("deliverdQuantity")
    @Expose
    private Double deliverdQuantity;
    @SerializedName("siid")
    @Expose
    private Object siid;
    @SerializedName("batchDate")
    @Expose
    private Object batchDate;
    @SerializedName("convertedQuantity")
    @Expose
    private Double convertedQuantity;
    @SerializedName("itemType")
    @Expose
    private String itemType;
    @SerializedName("fifoAmount")
    @Expose
    private Double fifoAmount;
    @SerializedName("salesDeliverOrderId")
    @Expose
    private Object salesDeliverOrderId;
    @SerializedName("salesDeliverOrderDetailsId")
    @Expose
    private Object salesDeliverOrderDetailsId;
    @SerializedName("received")
    @Expose
    private Double received;
    @SerializedName("qtytotalSent")
    @Expose
    private Double qtytotalSent;
    @SerializedName("discPercent")
    @Expose
    private Double discPercent;
    @SerializedName("itemAmount")
    @Expose
    private Double itemAmount;
    @SerializedName("delStatus")
    @Expose
    private Object delStatus;
    @SerializedName("uomConvertedName")
    @Expose
    private String uomConvertedName;
    @SerializedName("receiveItemDetailsId")
    @Expose
    private Object receiveItemDetailsId;
    @SerializedName("customerId")
    @Expose
    private Object customerId;
    @SerializedName("tablesId")
    @Expose
    private Object tablesId;
    @SerializedName("a1")
    @Expose
    private Object a1;
    @SerializedName("a2")
    @Expose
    private Object a2;
    @SerializedName("a3")
    @Expose
    private Object a3;
    @SerializedName("a4")
    @Expose
    private Object a4;
    @SerializedName("a5")
    @Expose
    private Object a5;
    @SerializedName("a6")
    @Expose
    private Object a6;
    @SerializedName("a7")
    @Expose
    private Object a7;
    @SerializedName("a8")
    @Expose
    private Object a8;
    @SerializedName("a9")
    @Expose
    private Object a9;
    @SerializedName("a10")
    @Expose
    private Object a10;
    @SerializedName("locationId")
    @Expose
    private Object locationId;
    @SerializedName("useraccount_id")
    @Expose
    private Object useraccountId;
    @SerializedName("expireDate")
    @Expose
    private Object expireDate;
    @SerializedName("purchaseDate")
    @Expose
    private Object purchaseDate;
    @SerializedName("manufactureDate")
    @Expose
    private Object manufactureDate;
    @SerializedName("sORbNumbers")
    @Expose
    private Object sORbNumbers;
    @SerializedName("serializableImeiNo")
    @Expose
    private Object serializableImeiNo;
    @SerializedName("itemCommisionAmount")
    @Expose
    private Double itemCommisionAmount;
    @SerializedName("itemCommisionConfigAmount")
    @Expose
    private Double itemCommisionConfigAmount;
    @SerializedName("type")
    @Expose
    private Object type;
    @SerializedName("discountConfigAmt")
    @Expose
    private Double discountConfigAmt;
    @SerializedName("serviceRemainingQty")
    @Expose
    private Double serviceRemainingQty;
    @SerializedName("fromDate")
    @Expose
    private Object fromDate;
    @SerializedName("toDate")
    @Expose
    private Object toDate;
    @SerializedName("serializableStatus")
    @Expose
    private String serializableStatus;
    @SerializedName("itemCategoryId")
    @Expose
    private Object itemCategoryId;
    @SerializedName("itemCategoryName")
    @Expose
    private Object itemCategoryName;
    @SerializedName("pORemaningQty")
    @Expose
    private Double pORemaningQty;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("purchaseOrderDetailsId")
    @Expose
    private Object purchaseOrderDetailsId;
    @SerializedName("salesOrderDetailId")
    @Expose
    private Object salesOrderDetailId;
    @SerializedName("deliveredQuantity")
    @Expose
    private Double deliveredQuantity;
    @SerializedName("productMergerSubItemNames")
    @Expose
    private Object productMergerSubItemNames;
    @SerializedName("otherCharges")
    @Expose
    private Object otherCharges;
    @SerializedName("piNo")
    @Expose
    private Object piNo;
    @SerializedName("operation")
    @Expose
    private Object operation;
    @SerializedName("qtySent")
    @Expose
    private Double qtySent;
    @SerializedName("proFormaSalesInvoiceDetailsId")
    @Expose
    private Object proFormaSalesInvoiceDetailsId;
    @SerializedName("proFormaSalesInvoiceId")
    @Expose
    private Object proFormaSalesInvoiceId;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getPurchaseInvoiceDetailID() {
        return purchaseInvoiceDetailID;
    }

    public void setPurchaseInvoiceDetailID(long purchaseInvoiceDetailID) {
        this.purchaseInvoiceDetailID = purchaseInvoiceDetailID;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public long getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(long returnQty) {
        this.returnQty = returnQty;
    }

    public long getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(long remainingQty) {
        this.remainingQty = remainingQty;
    }

    public double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public Long getTaxid() {
        return taxid;
    }

    public void setTaxid(Long taxid) {
        this.taxid = taxid;
    }

    public double getTaxpercent() {
        return taxpercent;
    }

    public void setTaxpercent(double taxpercent) {
        this.taxpercent = taxpercent;
    }

    public double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(double taxamt) {
        this.taxamt = taxamt;
    }

    public double getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(double amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public Object getSerializableItemId() {
        return serializableItemId;
    }

    public void setSerializableItemId(Object serializableItemId) {
        this.serializableItemId = serializableItemId;
    }

    public Object getSerializableNumbers() {
        return serializableNumbers;
    }

    public void setSerializableNumbers(Object serializableNumbers) {
        this.serializableNumbers = serializableNumbers;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getMakingCharge() {
        return makingCharge;
    }

    public void setMakingCharge(double makingCharge) {
        this.makingCharge = makingCharge;
    }

    public double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public double getCess() {
        return cess;
    }

    public void setCess(double cess) {
        this.cess = cess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getCgstsgstsplitvalues() {
        return cgstsgstsplitvalues;
    }

    public void setCgstsgstsplitvalues(double cgstsgstsplitvalues) {
        this.cgstsgstsplitvalues = cgstsgstsplitvalues;
    }

    public double getTaxPercentageSplit() {
        return taxPercentageSplit;
    }

    public void setTaxPercentageSplit(double taxPercentageSplit) {
        this.taxPercentageSplit = taxPercentageSplit;
    }

    public double getCessTaxAmt() {
        return cessTaxAmt;
    }

    public void setCessTaxAmt(double cessTaxAmt) {
        this.cessTaxAmt = cessTaxAmt;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public Object getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Object salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Object getSalesOrderDetailsId() {
        return salesOrderDetailsId;
    }

    public void setSalesOrderDetailsId(Object salesOrderDetailsId) {
        this.salesOrderDetailsId = salesOrderDetailsId;
    }

    public double getPosamtexclusivetax() {
        return posamtexclusivetax;
    }

    public void setPosamtexclusivetax(double posamtexclusivetax) {
        this.posamtexclusivetax = posamtexclusivetax;
    }

    public double getPosamtinclusivetax() {
        return posamtinclusivetax;
    }

    public void setPosamtinclusivetax(double posamtinclusivetax) {
        this.posamtinclusivetax = posamtinclusivetax;
    }

    public Boolean getReceiveItemFlag() {
        return receiveItemFlag;
    }

    public void setReceiveItemFlag(Boolean receiveItemFlag) {
        this.receiveItemFlag = receiveItemFlag;
    }

    public Object getReceiveItemId() {
        return receiveItemId;
    }

    public void setReceiveItemId(Object receiveItemId) {
        this.receiveItemId = receiveItemId;
    }

    public Object getReceiveItemFormNo() {
        return receiveItemFormNo;
    }

    public void setReceiveItemFormNo(Object receiveItemFormNo) {
        this.receiveItemFormNo = receiveItemFormNo;
    }

    public Object getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(Object taxCode) {
        this.taxCode = taxCode;
    }

    public Object getSalesQuotationId() {
        return salesQuotationId;
    }

    public void setSalesQuotationId(Object salesQuotationId) {
        this.salesQuotationId = salesQuotationId;
    }

    public Object getSalesQuotationDetailsId() {
        return salesQuotationDetailsId;
    }

    public void setSalesQuotationDetailsId(Object salesQuotationDetailsId) {
        this.salesQuotationDetailsId = salesQuotationDetailsId;
    }

    public String getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(String inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }

    public Object getPurchaseQuotationId() {
        return purchaseQuotationId;
    }

    public void setPurchaseQuotationId(Object purchaseQuotationId) {
        this.purchaseQuotationId = purchaseQuotationId;
    }

    public Object getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Object purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Object getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(Object salesOrder) {
        this.salesOrder = salesOrder;
    }

    public Object getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Object batchNo) {
        this.batchNo = batchNo;
    }

    public Object getBatchExpDate() {
        return batchExpDate;
    }

    public void setBatchExpDate(Object batchExpDate) {
        this.batchExpDate = batchExpDate;
    }

    public Object getSerializableIMEINo() {
        return serializableIMEINo;
    }

    public void setSerializableIMEINo(Object serializableIMEINo) {
        this.serializableIMEINo = serializableIMEINo;
    }

    public Object getBatchNoList() {
        return batchNoList;
    }

    public void setBatchNoList(Object batchNoList) {
        this.batchNoList = batchNoList;
    }

    public List<UomConvertorList> getUomConvertorList() {
        return uomConvertorList;
    }

    public void setUomConvertorList(List<UomConvertorList> uomConvertorList) {
        this.uomConvertorList = uomConvertorList;
    }

    public Object getSerializeNoList() {
        return serializeNoList;
    }

    public void setSerializeNoList(Object serializeNoList) {
        this.serializeNoList = serializeNoList;
    }

    public Object getSerializableItemsDTOs() {
        return serializableItemsDTOs;
    }

    public void setSerializableItemsDTOs(Object serializableItemsDTOs) {
        this.serializableItemsDTOs = serializableItemsDTOs;
    }

    public Object getSerializableItemsDTOList() {
        return serializableItemsDTOList;
    }

    public void setSerializableItemsDTOList(Object serializableItemsDTOList) {
        this.serializableItemsDTOList = serializableItemsDTOList;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public Integer getUomConvertorId() {
        return uomConvertorId;
    }

    public void setUomConvertorId(Integer uomConvertorId) {
        this.uomConvertorId = uomConvertorId;
    }

    public Object getUomValue() {
        return uomValue;
    }

    public void setUomValue(Object uomValue) {
        this.uomValue = uomValue;
    }

    public Double getConvertedReturnQty() {
        return convertedReturnQty;
    }

    public void setConvertedReturnQty(Double convertedReturnQty) {
        this.convertedReturnQty = convertedReturnQty;
    }

    public Object getUomId() {
        return uomId;
    }

    public void setUomId(Object uomId) {
        this.uomId = uomId;
    }

    public Object getsIItemId() {
        return sIItemId;
    }

    public void setsIItemId(Object sIItemId) {
        this.sIItemId = sIItemId;
    }

    public Object getConvertedName() {
        return convertedName;
    }

    public void setConvertedName(Object convertedName) {
        this.convertedName = convertedName;
    }

    public Object getAssertId() {
        return assertId;
    }

    public void setAssertId(Object assertId) {
        this.assertId = assertId;
    }

    public Object getUomConvertor() {
        return uomConvertor;
    }

    public void setUomConvertor(Object uomConvertor) {
        this.uomConvertor = uomConvertor;
    }

    public Object getCustomerName() {
        return customerName;
    }

    public void setCustomerName(Object customerName) {
        this.customerName = customerName;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Double getDeliverdQuantity() {
        return deliverdQuantity;
    }

    public void setDeliverdQuantity(Double deliverdQuantity) {
        this.deliverdQuantity = deliverdQuantity;
    }

    public Object getSiid() {
        return siid;
    }

    public void setSiid(Object siid) {
        this.siid = siid;
    }

    public Object getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(Object batchDate) {
        this.batchDate = batchDate;
    }

    public Double getConvertedQuantity() {
        return convertedQuantity;
    }

    public void setConvertedQuantity(Double convertedQuantity) {
        this.convertedQuantity = convertedQuantity;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Double getFifoAmount() {
        return fifoAmount;
    }

    public void setFifoAmount(Double fifoAmount) {
        this.fifoAmount = fifoAmount;
    }

    public Object getSalesDeliverOrderId() {
        return salesDeliverOrderId;
    }

    public void setSalesDeliverOrderId(Object salesDeliverOrderId) {
        this.salesDeliverOrderId = salesDeliverOrderId;
    }

    public Object getSalesDeliverOrderDetailsId() {
        return salesDeliverOrderDetailsId;
    }

    public void setSalesDeliverOrderDetailsId(Object salesDeliverOrderDetailsId) {
        this.salesDeliverOrderDetailsId = salesDeliverOrderDetailsId;
    }

    public Double getReceived() {
        return received;
    }

    public void setReceived(Double received) {
        this.received = received;
    }

    public Double getQtytotalSent() {
        return qtytotalSent;
    }

    public void setQtytotalSent(Double qtytotalSent) {
        this.qtytotalSent = qtytotalSent;
    }

    public Double getDiscPercent() {
        return discPercent;
    }

    public void setDiscPercent(Double discPercent) {
        this.discPercent = discPercent;
    }

    public Double getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Double itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Object getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Object delStatus) {
        this.delStatus = delStatus;
    }

    public String getUomConvertedName() {
        return uomConvertedName;
    }

    public void setUomConvertedName(String uomConvertedName) {
        this.uomConvertedName = uomConvertedName;
    }

    public Object getReceiveItemDetailsId() {
        return receiveItemDetailsId;
    }

    public void setReceiveItemDetailsId(Object receiveItemDetailsId) {
        this.receiveItemDetailsId = receiveItemDetailsId;
    }

    public Object getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Object customerId) {
        this.customerId = customerId;
    }

    public Object getTablesId() {
        return tablesId;
    }

    public void setTablesId(Object tablesId) {
        this.tablesId = tablesId;
    }

    public Object getA1() {
        return a1;
    }

    public void setA1(Object a1) {
        this.a1 = a1;
    }

    public Object getA2() {
        return a2;
    }

    public void setA2(Object a2) {
        this.a2 = a2;
    }

    public Object getA3() {
        return a3;
    }

    public void setA3(Object a3) {
        this.a3 = a3;
    }

    public Object getA4() {
        return a4;
    }

    public void setA4(Object a4) {
        this.a4 = a4;
    }

    public Object getA5() {
        return a5;
    }

    public void setA5(Object a5) {
        this.a5 = a5;
    }

    public Object getA6() {
        return a6;
    }

    public void setA6(Object a6) {
        this.a6 = a6;
    }

    public Object getA7() {
        return a7;
    }

    public void setA7(Object a7) {
        this.a7 = a7;
    }

    public Object getA8() {
        return a8;
    }

    public void setA8(Object a8) {
        this.a8 = a8;
    }

    public Object getA9() {
        return a9;
    }

    public void setA9(Object a9) {
        this.a9 = a9;
    }

    public Object getA10() {
        return a10;
    }

    public void setA10(Object a10) {
        this.a10 = a10;
    }

    public Object getLocationId() {
        return locationId;
    }

    public void setLocationId(Object locationId) {
        this.locationId = locationId;
    }

    public Object getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Object useraccountId) {
        this.useraccountId = useraccountId;
    }

    public Object getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Object expireDate) {
        this.expireDate = expireDate;
    }

    public Object getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Object purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Object getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Object manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Object getsORbNumbers() {
        return sORbNumbers;
    }

    public void setsORbNumbers(Object sORbNumbers) {
        this.sORbNumbers = sORbNumbers;
    }

    public Object getSerializableImeiNo() {
        return serializableImeiNo;
    }

    public void setSerializableImeiNo(Object serializableImeiNo) {
        this.serializableImeiNo = serializableImeiNo;
    }

    public Double getItemCommisionAmount() {
        return itemCommisionAmount;
    }

    public void setItemCommisionAmount(Double itemCommisionAmount) {
        this.itemCommisionAmount = itemCommisionAmount;
    }

    public Double getItemCommisionConfigAmount() {
        return itemCommisionConfigAmount;
    }

    public void setItemCommisionConfigAmount(Double itemCommisionConfigAmount) {
        this.itemCommisionConfigAmount = itemCommisionConfigAmount;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Double getDiscountConfigAmt() {
        return discountConfigAmt;
    }

    public void setDiscountConfigAmt(Double discountConfigAmt) {
        this.discountConfigAmt = discountConfigAmt;
    }

    public Double getServiceRemainingQty() {
        return serviceRemainingQty;
    }

    public void setServiceRemainingQty(Double serviceRemainingQty) {
        this.serviceRemainingQty = serviceRemainingQty;
    }

    public Object getFromDate() {
        return fromDate;
    }

    public void setFromDate(Object fromDate) {
        this.fromDate = fromDate;
    }

    public Object getToDate() {
        return toDate;
    }

    public void setToDate(Object toDate) {
        this.toDate = toDate;
    }

    public String getSerializableStatus() {
        return serializableStatus;
    }

    public void setSerializableStatus(String serializableStatus) {
        this.serializableStatus = serializableStatus;
    }

    public Object getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Object itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public Object getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(Object itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public Double getpORemaningQty() {
        return pORemaningQty;
    }

    public void setpORemaningQty(Double pORemaningQty) {
        this.pORemaningQty = pORemaningQty;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getPurchaseOrderDetailsId() {
        return purchaseOrderDetailsId;
    }

    public void setPurchaseOrderDetailsId(Object purchaseOrderDetailsId) {
        this.purchaseOrderDetailsId = purchaseOrderDetailsId;
    }

    public Object getSalesOrderDetailId() {
        return salesOrderDetailId;
    }

    public void setSalesOrderDetailId(Object salesOrderDetailId) {
        this.salesOrderDetailId = salesOrderDetailId;
    }

    public Double getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(Double deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public Object getProductMergerSubItemNames() {
        return productMergerSubItemNames;
    }

    public void setProductMergerSubItemNames(Object productMergerSubItemNames) {
        this.productMergerSubItemNames = productMergerSubItemNames;
    }

    public Object getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(Object otherCharges) {
        this.otherCharges = otherCharges;
    }

    public Object getPiNo() {
        return piNo;
    }

    public void setPiNo(Object piNo) {
        this.piNo = piNo;
    }

    public Object getOperation() {
        return operation;
    }

    public void setOperation(Object operation) {
        this.operation = operation;
    }

    public Double getQtySent() {
        return qtySent;
    }

    public void setQtySent(Double qtySent) {
        this.qtySent = qtySent;
    }

    public Object getProFormaSalesInvoiceDetailsId() {
        return proFormaSalesInvoiceDetailsId;
    }

    public void setProFormaSalesInvoiceDetailsId(Object proFormaSalesInvoiceDetailsId) {
        this.proFormaSalesInvoiceDetailsId = proFormaSalesInvoiceDetailsId;
    }

    public Object getProFormaSalesInvoiceId() {
        return proFormaSalesInvoiceId;
    }

    public void setProFormaSalesInvoiceId(Object proFormaSalesInvoiceId) {
        this.proFormaSalesInvoiceId = proFormaSalesInvoiceId;
    }
}
