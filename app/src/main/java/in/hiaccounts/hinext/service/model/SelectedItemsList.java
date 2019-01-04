package in.hiaccounts.hinext.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import in.hiaccounts.hinext.sales.model.checkout.UomConvertorList;

/**
 * Created by administrator on 23/2/18.
 */

public class SelectedItemsList implements Serializable {
    @SerializedName("itemName")
    @Expose
    public String itemName;
    @SerializedName("purchaseInvoiceDetailID")
    @Expose
    public Long purchaseInvoiceDetailID;
    @SerializedName("itemId")
    @Expose
    public Long itemId;
    @SerializedName("unitPrice")
    @Expose
    public Double unitPrice;
    @SerializedName("qty")
    @Expose
    public Double qty;
    @SerializedName("returnQty")
    @Expose
    public Double returnQty;
    @SerializedName("remainingQty")
    @Expose
    public Double remainingQty;
    @SerializedName("amtexclusivetax")
    @Expose
    public Double amtexclusivetax;
    @SerializedName("taxid")
    @Expose
    public Long taxid;
    @SerializedName("taxpercent")
    @Expose
    public Double taxpercent;
    @SerializedName("taxamt")
    @Expose
    public Double taxamt;
    @SerializedName("amtinclusivetax")
    @Expose
    public Double amtinclusivetax;
    @SerializedName("discountAmt")
    @Expose
    public Double discountAmt;
    @SerializedName("itemDescription")
    @Expose
    public String itemDescription;
    @SerializedName("itemDesc")
    @Expose
    public String itemDesc;

    @SerializedName("taxName")
    @Expose
    public String taxName;
    @SerializedName("serializableItemId")
    @Expose
    public Object serializableItemId;
    @SerializedName("serializableNumbers")
    @Expose
    public Object serializableNumbers;
    @SerializedName("itemCode")
    @Expose
    public String itemCode;
    @SerializedName("makingCharge")
    @Expose
    public Double makingCharge;
    @SerializedName("actualWeight")
    @Expose
    public Double actualWeight;
    @SerializedName("cess")
    @Expose
    public Double cess;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("cgstsgstsplitvalues")
    @Expose
    public Double cgstsgstsplitvalues;
    @SerializedName("taxPercentageSplit")
    @Expose
    public Double taxPercentageSplit;
    @SerializedName("cessTaxAmt")
    @Expose
    public Double cessTaxAmt;
    @SerializedName("hsnCode")
    @Expose
    public String hsnCode;
    @SerializedName("salesOrderId")
    @Expose
    public Long salesOrderId;
    @SerializedName("salesOrderDetailsId")
    @Expose
    public Long salesOrderDetailsId;
    @SerializedName("posamtexclusivetax")
    @Expose
    public Double posamtexclusivetax;
    @SerializedName("posamtinclusivetax")
    @Expose
    public Double posamtinclusivetax;
    @SerializedName("receiveItemFlag")
    @Expose
    public Boolean receiveItemFlag;
    @SerializedName("receiveItemId")
    @Expose
    public Long receiveItemId;
    @SerializedName("receiveItemFormNo")
    @Expose
    public String receiveItemFormNo;
    @SerializedName("taxCode")
    @Expose
    public String taxCode;
    @SerializedName("salesQuotationId")
    @Expose
    public Long salesQuotationId;
    @SerializedName("salesQuotationDetailsId")
    @Expose
    public Long salesQuotationDetailsId;
    @SerializedName("inclusiveJSON")
    @Expose
    public String inclusiveJSON;
    @SerializedName("purchaseQuotationId")
    @Expose
    public Long purchaseQuotationId;
    @SerializedName("purchaseOrderId")
    @Expose
    public Long purchaseOrderId;
    @SerializedName("salesOrder")
    @Expose
    public Long salesOrder;
    @SerializedName("batchNo")
    @Expose
    public String batchNo;
    @SerializedName("batchExpDate")
    @Expose
    public String batchExpDate;
    @SerializedName("serializableIMEINo")
    @Expose
    public String serializableIMEINo;
    @SerializedName("batchNoList")
    @Expose
    public String batchNoList;
    @SerializedName("uomConvertorList")
    @Expose
    public List<UomConvertorList> uomConvertorList = null;
    @SerializedName("uomConvertorDTOList")
    @Expose
    public List<UomConvertorDTOList> uomConvertorDTOList = null;

    @SerializedName("serializeNoList")
    @Expose
    public String serializeNoList;
    @SerializedName("serializableItemsDTOs")
    @Expose
    public String serializableItemsDTOs;
    @SerializedName("serializableItemsDTOList")
    @Expose
    public String serializableItemsDTOList;
    @SerializedName("uom")
    @Expose
    public String uom;
    @SerializedName("uomName")
    @Expose
    public String uomName;
    @SerializedName("uomConvertorId")
    @Expose
    public String uomConvertorId;
    @SerializedName("uomValue")
    @Expose
    public String uomValue;
    @SerializedName("convertedReturnQty")
    @Expose
    public Double convertedReturnQty;
    @SerializedName("uomId")
    @Expose
    public Long uomId;
    @SerializedName("sIItemId")
    @Expose
    public Long sIItemId;
    @SerializedName("convertedName")
    @Expose
    public String convertedName;
    @SerializedName("assertId")
    @Expose
    public Long assertId;
    @SerializedName("uomConvertor")
    @Expose
    public String uomConvertor;
    @SerializedName("customerName")
    @Expose
    public String customerName;
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("deliverdQuantity")
    @Expose
    public Double deliverdQuantity;
    @SerializedName("siid")
    @Expose
    public String siid;
    @SerializedName("batchDate")
    @Expose
    public String batchDate;
    @SerializedName("convertedQuantity")
    @Expose
    public Double convertedQuantity;
    @SerializedName("itemType")
    @Expose
    public String itemType;
    @SerializedName("fifoAmount")
    @Expose
    public Double fifoAmount;
    @SerializedName("salesDeliverOrderId")
    @Expose
    public Long salesDeliverOrderId;
    @SerializedName("salesDeliverOrderDetailsId")
    @Expose
    public Long salesDeliverOrderDetailsId;
    @SerializedName("received")
    @Expose
    public Double received;
    @SerializedName("qtytotalSent")
    @Expose
    public Double qtytotalSent;
    @SerializedName("discPercent")
    @Expose
    public Double discPercent;
    @SerializedName("itemAmount")
    @Expose
    public Double itemAmount;
    @SerializedName("delStatus")
    @Expose
    public String delStatus;
    @SerializedName("uomConvertedName")
    @Expose
    public String uomConvertedName;
    @SerializedName("receiveItemDetailsId")
    @Expose
    public Long receiveItemDetailsId;
    @SerializedName("customerId")
    @Expose
    public Long customerId;
    @SerializedName("tablesId")
    @Expose
    public Object tablesId;
    @SerializedName("a1")
    @Expose
    public Object a1;
    @SerializedName("a2")
    @Expose
    public Object a2;
    @SerializedName("a3")
    @Expose
    public Object a3;
    @SerializedName("a4")
    @Expose
    public Object a4;
    @SerializedName("a5")
    @Expose
    public Object a5;
    @SerializedName("a6")
    @Expose
    public Object a6;
    @SerializedName("a7")
    @Expose
    public Object a7;
    @SerializedName("a8")
    @Expose
    public Object a8;
    @SerializedName("a9")
    @Expose
    public Object a9;
    @SerializedName("a10")
    @Expose
    public Object a10;
    @SerializedName("locationId")
    @Expose
    public Long locationId;
    @SerializedName("useraccount_id")
    @Expose
    public Long useraccountId;
    @SerializedName("expireDate")
    @Expose
    public String expireDate;
    @SerializedName("purchaseDate")
    @Expose
    public String purchaseDate;
    @SerializedName("manufactureDate")
    @Expose
    public String manufactureDate;
    @SerializedName("sORbNumbers")
    @Expose
    public String sORbNumbers;
    @SerializedName("serializableImeiNo")
    @Expose
    public String serializableImeiNo;
    @SerializedName("itemCommisionAmount")
    @Expose
    public Double itemCommisionAmount;
    @SerializedName("itemCommisionConfigAmount")
    @Expose
    public Double itemCommisionConfigAmount;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("discountConfigAmt")
    @Expose
    public Double discountConfigAmt;
    @SerializedName("serviceRemainingQty")
    @Expose
    public Double serviceRemainingQty;
    @SerializedName("fromDate")
    @Expose
    public String fromDate;
    @SerializedName("toDate")
    @Expose
    public String toDate;
    @SerializedName("serializableStatus")
    @Expose
    public String serializableStatus;
    @SerializedName("itemCategoryId")
    @Expose
    public Long itemCategoryId;
    @SerializedName("itemCategoryName")
    @Expose
    public String itemCategoryName;
    @SerializedName("pORemaningQty")
    @Expose
    public Double pORemaningQty;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("purchaseOrderDetailsId")
    @Expose
    public Long purchaseOrderDetailsId;
    @SerializedName("salesOrderDetailId")
    @Expose
    public Long salesOrderDetailId;
    @SerializedName("deliveredQuantity")
    @Expose
    public Double deliveredQuantity;
    @SerializedName("productMergerSubItemNames")
    @Expose
    public String productMergerSubItemNames;
    @SerializedName("otherCharges")
    @Expose
    public String otherCharges;
    @SerializedName("piNo")
    @Expose
    public Long piNo;
    @SerializedName("salesInvoiceDetailsId")
    @Expose
    public Long salesInvoiceDetailsId;
    @SerializedName("operation")
    @Expose
    public String operation;
    @SerializedName("qtySent")
    @Expose
    public Double qtySent;
    @SerializedName("proFormaSalesInvoiceId")
    @Expose
    public Long proFormaSalesInvoiceId;
    @SerializedName("proFormaSalesInvoiceDetailsId")
    @Expose
    public Long proFormaSalesInvoiceDetailsId;

    @SerializedName("itemTypeName")
    @Expose
    public String itemTypeName;


    String taxTypeSelection;
    private BigDecimal price;
    private long itemQuantity;
    private double itemTotalAmount;
    boolean isSelect;
    boolean selectSerialItem;

    public List<UomConvertorDTOList> getUomConvertorDTOList() {
        return uomConvertorDTOList;
    }

    public void setUomConvertorDTOList(List<UomConvertorDTOList> uomConvertorDTOList) {
        this.uomConvertorDTOList = uomConvertorDTOList;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getPurchaseInvoiceDetailID() {
        return purchaseInvoiceDetailID;
    }

    public void setPurchaseInvoiceDetailID(Long purchaseInvoiceDetailID) {
        this.purchaseInvoiceDetailID = purchaseInvoiceDetailID;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(Double returnQty) {
        this.returnQty = returnQty;
    }

    public Double getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(Double remainingQty) {
        this.remainingQty = remainingQty;
    }

    public Double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(Double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public Long getTaxid() {
        return taxid;
    }

    public void setTaxid(Long taxid) {
        this.taxid = taxid;
    }

    public Double getTaxpercent() {
        return taxpercent;
    }

    public void setTaxpercent(Double taxpercent) {
        this.taxpercent = taxpercent;
    }

    public Double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(Double taxamt) {
        this.taxamt = taxamt;
    }

    public Double getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(Double amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public Double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(Double discountAmt) {
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

    public Double getMakingCharge() {
        return makingCharge;
    }

    public void setMakingCharge(Double makingCharge) {
        this.makingCharge = makingCharge;
    }

    public Double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(Double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public Double getCess() {
        return cess;
    }

    public void setCess(Double cess) {
        this.cess = cess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getCgstsgstsplitvalues() {
        return cgstsgstsplitvalues;
    }

    public void setCgstsgstsplitvalues(Double cgstsgstsplitvalues) {
        this.cgstsgstsplitvalues = cgstsgstsplitvalues;
    }

    public Double getTaxPercentageSplit() {
        return taxPercentageSplit;
    }

    public void setTaxPercentageSplit(Double taxPercentageSplit) {
        this.taxPercentageSplit = taxPercentageSplit;
    }

    public Double getCessTaxAmt() {
        return cessTaxAmt;
    }

    public void setCessTaxAmt(Double cessTaxAmt) {
        this.cessTaxAmt = cessTaxAmt;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Long getSalesOrderDetailsId() {
        return salesOrderDetailsId;
    }

    public void setSalesOrderDetailsId(Long salesOrderDetailsId) {
        this.salesOrderDetailsId = salesOrderDetailsId;
    }

    public Double getPosamtexclusivetax() {
        return posamtexclusivetax;
    }

    public void setPosamtexclusivetax(Double posamtexclusivetax) {
        this.posamtexclusivetax = posamtexclusivetax;
    }

    public Double getPosamtinclusivetax() {
        return posamtinclusivetax;
    }

    public void setPosamtinclusivetax(Double posamtinclusivetax) {
        this.posamtinclusivetax = posamtinclusivetax;
    }

    public Boolean getReceiveItemFlag() {
        return receiveItemFlag;
    }

    public void setReceiveItemFlag(Boolean receiveItemFlag) {
        this.receiveItemFlag = receiveItemFlag;
    }

    public Long getReceiveItemId() {
        return receiveItemId;
    }

    public void setReceiveItemId(Long receiveItemId) {
        this.receiveItemId = receiveItemId;
    }

    public String getReceiveItemFormNo() {
        return receiveItemFormNo;
    }

    public void setReceiveItemFormNo(String receiveItemFormNo) {
        this.receiveItemFormNo = receiveItemFormNo;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Long getSalesQuotationId() {
        return salesQuotationId;
    }

    public void setSalesQuotationId(Long salesQuotationId) {
        this.salesQuotationId = salesQuotationId;
    }

    public Long getSalesQuotationDetailsId() {
        return salesQuotationDetailsId;
    }

    public void setSalesQuotationDetailsId(Long salesQuotationDetailsId) {
        this.salesQuotationDetailsId = salesQuotationDetailsId;
    }

    public String getInclusiveJSON() {
        return inclusiveJSON;
    }

    public void setInclusiveJSON(String inclusiveJSON) {
        this.inclusiveJSON = inclusiveJSON;
    }

    public Long getPurchaseQuotationId() {
        return purchaseQuotationId;
    }

    public void setPurchaseQuotationId(Long purchaseQuotationId) {
        this.purchaseQuotationId = purchaseQuotationId;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(Long salesOrder) {
        this.salesOrder = salesOrder;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBatchExpDate() {
        return batchExpDate;
    }

    public void setBatchExpDate(String batchExpDate) {
        this.batchExpDate = batchExpDate;
    }

    public String getSerializableIMEINo() {
        return serializableIMEINo;
    }

    public void setSerializableIMEINo(String serializableIMEINo) {
        this.serializableIMEINo = serializableIMEINo;
    }

    public String getBatchNoList() {
        return batchNoList;
    }

    public void setBatchNoList(String batchNoList) {
        this.batchNoList = batchNoList;
    }

    public List<UomConvertorList> getUomConvertorList() {
        return uomConvertorList;
    }

    public void setUomConvertorList(List<UomConvertorList> uomConvertorList) {
        this.uomConvertorList = uomConvertorList;
    }

    public String getSerializeNoList() {
        return serializeNoList;
    }

    public void setSerializeNoList(String serializeNoList) {
        this.serializeNoList = serializeNoList;
    }

    public String getSerializableItemsDTOs() {
        return serializableItemsDTOs;
    }

    public void setSerializableItemsDTOs(String serializableItemsDTOs) {
        this.serializableItemsDTOs = serializableItemsDTOs;
    }

    public String getSerializableItemsDTOList() {
        return serializableItemsDTOList;
    }

    public void setSerializableItemsDTOList(String serializableItemsDTOList) {
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

    public String getUomConvertorId() {
        return uomConvertorId;
    }

    public void setUomConvertorId(String uomConvertorId) {
        this.uomConvertorId = uomConvertorId;
    }

    public String getUomValue() {
        return uomValue;
    }

    public void setUomValue(String uomValue) {
        this.uomValue = uomValue;
    }

    public Double getConvertedReturnQty() {
        return convertedReturnQty;
    }

    public void setConvertedReturnQty(Double convertedReturnQty) {
        this.convertedReturnQty = convertedReturnQty;
    }

    public Long getUomId() {
        return uomId;
    }

    public void setUomId(Long uomId) {
        this.uomId = uomId;
    }

    public Long getsIItemId() {
        return sIItemId;
    }

    public void setsIItemId(Long sIItemId) {
        this.sIItemId = sIItemId;
    }

    public String getConvertedName() {
        return convertedName;
    }

    public void setConvertedName(String convertedName) {
        this.convertedName = convertedName;
    }

    public Long getAssertId() {
        return assertId;
    }

    public void setAssertId(Long assertId) {
        this.assertId = assertId;
    }

    public String getUomConvertor() {
        return uomConvertor;
    }

    public void setUomConvertor(String uomConvertor) {
        this.uomConvertor = uomConvertor;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDeliverdQuantity() {
        return deliverdQuantity;
    }

    public void setDeliverdQuantity(Double deliverdQuantity) {
        this.deliverdQuantity = deliverdQuantity;
    }

    public String getSiid() {
        return siid;
    }

    public void setSiid(String siid) {
        this.siid = siid;
    }

    public String getBatchDate() {
        return batchDate;
    }

    public void setBatchDate(String batchDate) {
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

    public Long getSalesDeliverOrderId() {
        return salesDeliverOrderId;
    }

    public void setSalesDeliverOrderId(Long salesDeliverOrderId) {
        this.salesDeliverOrderId = salesDeliverOrderId;
    }

    public Long getSalesDeliverOrderDetailsId() {
        return salesDeliverOrderDetailsId;
    }

    public void setSalesDeliverOrderDetailsId(Long salesDeliverOrderDetailsId) {
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

    public String getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(String delStatus) {
        this.delStatus = delStatus;
    }

    public String getUomConvertedName() {
        return uomConvertedName;
    }

    public void setUomConvertedName(String uomConvertedName) {
        this.uomConvertedName = uomConvertedName;
    }

    public Long getReceiveItemDetailsId() {
        return receiveItemDetailsId;
    }

    public void setReceiveItemDetailsId(Long receiveItemDetailsId) {
        this.receiveItemDetailsId = receiveItemDetailsId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(Long useraccountId) {
        this.useraccountId = useraccountId;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getsORbNumbers() {
        return sORbNumbers;
    }

    public void setsORbNumbers(String sORbNumbers) {
        this.sORbNumbers = sORbNumbers;
    }

    public String getSerializableImeiNo() {
        return serializableImeiNo;
    }

    public void setSerializableImeiNo(String serializableImeiNo) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getSerializableStatus() {
        return serializableStatus;
    }

    public void setSerializableStatus(String serializableStatus) {
        this.serializableStatus = serializableStatus;
    }

    public Long getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Long itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public Double getpORemaningQty() {
        return pORemaningQty;
    }

    public void setpORemaningQty(Double pORemaningQty) {
        this.pORemaningQty = pORemaningQty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPurchaseOrderDetailsId() {
        return purchaseOrderDetailsId;
    }

    public void setPurchaseOrderDetailsId(Long purchaseOrderDetailsId) {
        this.purchaseOrderDetailsId = purchaseOrderDetailsId;
    }

    public Long getSalesOrderDetailId() {
        return salesOrderDetailId;
    }

    public void setSalesOrderDetailId(Long salesOrderDetailId) {
        this.salesOrderDetailId = salesOrderDetailId;
    }

    public Double getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(Double deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public String getProductMergerSubItemNames() {
        return productMergerSubItemNames;
    }

    public void setProductMergerSubItemNames(String productMergerSubItemNames) {
        this.productMergerSubItemNames = productMergerSubItemNames;
    }

    public String getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(String otherCharges) {
        this.otherCharges = otherCharges;
    }

    public Long getPiNo() {
        return piNo;
    }

    public void setPiNo(Long piNo) {
        this.piNo = piNo;
    }

    public Long getSalesInvoiceDetailsId() {
        return salesInvoiceDetailsId;
    }

    public void setSalesInvoiceDetailsId(Long salesInvoiceDetailsId) {
        this.salesInvoiceDetailsId = salesInvoiceDetailsId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getQtySent() {
        return qtySent;
    }

    public void setQtySent(Double qtySent) {
        this.qtySent = qtySent;
    }

    public Long getProFormaSalesInvoiceId() {
        return proFormaSalesInvoiceId;
    }

    public void setProFormaSalesInvoiceId(Long proFormaSalesInvoiceId) {
        this.proFormaSalesInvoiceId = proFormaSalesInvoiceId;
    }

    public Long getProFormaSalesInvoiceDetailsId() {
        return proFormaSalesInvoiceDetailsId;
    }

    public void setProFormaSalesInvoiceDetailsId(Long proFormaSalesInvoiceDetailsId) {
        this.proFormaSalesInvoiceDetailsId = proFormaSalesInvoiceDetailsId;
    }

    public String getTaxTypeSelection() {
        return taxTypeSelection;
    }

    public void setTaxTypeSelection(String taxTypeSelection) {
        this.taxTypeSelection = taxTypeSelection;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(long itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getItemTotalAmount() {
        return itemTotalAmount;
    }

    public void setItemTotalAmount(double itemTotalAmount) {
        this.itemTotalAmount = itemTotalAmount;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isSelectSerialItem() {
        return selectSerialItem;
    }

    public void setSelectSerialItem(boolean selectSerialItem) {
        this.selectSerialItem = selectSerialItem;
    }
}
