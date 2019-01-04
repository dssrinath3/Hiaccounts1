
package in.hiaccounts.hinext.purchase.model.purchase_pos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import in.hiaccounts.hinext.sales.model.checkout.UomConvertorList;

public class SelectedItemsList implements Serializable{
    @SerializedName("itemTypeName")
    @Expose
    public String itemTypeName;

    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("itemId")
    @Expose
    private long itemId;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("serializableStatus")
    @Expose
    private String serializableStatus;
    @SerializedName("unitPrice")
    @Expose
    private Double unitPrice;
    @SerializedName("unitPriceIn")
    @Expose
    private Double unitPriceIn;

    @SerializedName("discountAmt")
    @Expose
    private double discountAmt;
    @SerializedName("cess")
    @Expose
    private Double cess;
    @SerializedName("isDiscountInPercent")
    @Expose
    private boolean isDiscountInPercent;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("returnQty")
    @Expose
    private long returnQty;
    @SerializedName("remainingQty")
    @Expose
    private String remainingQty;
    @SerializedName("amtexclusivetax")
    @Expose
    private double amtexclusivetax;
    @SerializedName("taxid")
    @Expose
    private long taxid;
    @SerializedName("taxpercent")
    @Expose
    private String taxpercent;
    @SerializedName("taxName")
    @Expose
    private String taxName;
    @SerializedName("taxamt")
    @Expose
    private double taxamt;
    @SerializedName("igTax")
    @Expose
    private double igTax;
    @SerializedName("amtinclusivetax")
    @Expose
    private double amtinclusivetax;
    @SerializedName("hsnCode")
    @Expose
    private String hsnCode;
    @SerializedName("uomName")
    @Expose
    private String uomName;
    @SerializedName("itemDescription")
    @Expose
    private String itemDescription;
    @SerializedName("cessTaxAmt")
    @Expose
    private double cessTaxAmt;
    @SerializedName("taxAmountSplit")
    @Expose
    private String taxAmountSplit;
    @SerializedName("uomConvertorList")
    @Expose
    public List<UomConvertorList> uomConvertorList = null;

    @SerializedName("salesOrderId")
    @Expose
    public Long salesOrderId;
    @SerializedName("salesOrderDetailsId")
    @Expose
    public Long salesOrderDetailsId;
    @SerializedName("posamtexclusivetax")
    @Expose
    public Long posamtexclusivetax;
    @SerializedName("posamtinclusivetax")
    @Expose
    public Long posamtinclusivetax;
    @SerializedName("receiveItemFlag")
    @Expose
    public Boolean receiveItemFlag;
    @SerializedName("receiveItemId")
    @Expose
    public Long receiveItemId;
    @SerializedName("receiveItemFormNo")
    @Expose
    public Long receiveItemFormNo;
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
    public String salesOrder;
    @SerializedName("batchNo")
    @Expose
    public String batchNo;
    @SerializedName("batchExpDate")
    @Expose
    public String batchExpDate;
    @SerializedName("serializableIMEINo")
    @Expose
    public Object serializableIMEINo;
    @SerializedName("batchNoList")
    @Expose
    public Object batchNoList;

    @SerializedName("serializeNoList")
    @Expose
    public Object serializeNoList;
    @SerializedName("serializableItemsDTOs")
    @Expose
    public Object serializableItemsDTOs;
    @SerializedName("serializableItemsDTOList")
    @Expose
    public Object serializableItemsDTOList;
    @SerializedName("uom")
    @Expose
    public Long uom;

    @SerializedName("uomConvertorId")
    @Expose
    public Long uomConvertorId;
    @SerializedName("uomValue")
    @Expose
    public Long uomValue;
    @SerializedName("convertedReturnQty")
    @Expose
    public Long convertedReturnQty;
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
    public Long deliverdQuantity;
    @SerializedName("siid")
    @Expose
    public String siid;
    @SerializedName("batchDate")
    @Expose
    public String batchDate;
    @SerializedName("convertedQuantity")
    @Expose
    public Long convertedQuantity;
    @SerializedName("itemType")
    @Expose
    public String itemType;
    @SerializedName("fifoAmount")
    @Expose
    public Long fifoAmount;
    @SerializedName("salesDeliverOrderId")
    @Expose
    public Long salesDeliverOrderId;
    @SerializedName("salesDeliverOrderDetailsId")
    @Expose
    public Long salesDeliverOrderDetailsId;
    @SerializedName("received")
    @Expose
    public Long received;
    @SerializedName("qtytotalSent")
    @Expose
    public Long qtytotalSent;
    @SerializedName("discPercent")
    @Expose
    public Long discPercent;
    @SerializedName("itemAmount")
    @Expose
    public Long itemAmount;
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
    public Long itemCommisionAmount;
    @SerializedName("itemCommisionConfigAmount")
    @Expose
    public Long itemCommisionConfigAmount;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("discountConfigAmt")
    @Expose
    public Double discountConfigAmt;
    @SerializedName("serviceRemainingQty")
    @Expose
    public Long serviceRemainingQty;
    @SerializedName("fromDate")
    @Expose
    public String fromDate;
    @SerializedName("toDate")
    @Expose
    public String toDate;

    @SerializedName("itemCategoryId")
    @Expose
    public Long itemCategoryId;
    @SerializedName("itemCategoryName")
    @Expose
    public String itemCategoryName;
    @SerializedName("pORemaningQty")
    @Expose
    public Long pORemaningQty;
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
    public Long deliveredQuantity;
    @SerializedName("productMergerSubItemNames")
    @Expose
    public String productMergerSubItemNames;
    @SerializedName("otherCharges")
    @Expose
    public String otherCharges;
    @SerializedName("piNo")
    @Expose
    public Long piNo;
    @SerializedName("operation")
    @Expose
    public String operation;
    @SerializedName("qtySent")
    @Expose
    public Long qtySent;
    @SerializedName("proFormaSalesInvoiceDetailsId")
    @Expose
    public Long proFormaSalesInvoiceDetailsId;
    @SerializedName("proFormaSalesInvoiceId")
    @Expose
    public Long proFormaSalesInvoiceId;




    String taxTypeSelection;
    private BigDecimal price;
    private long itemQuantity;
    private double itemTotalAmount;
    boolean isSelect;
    boolean selectSerialItem;

    public List<UomConvertorList> getUomConvertorList() {
        return uomConvertorList;
    }

    public void setUomConvertorList(List<UomConvertorList> uomConvertorList) {
        this.uomConvertorList = uomConvertorList;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSerializableStatus() {
        return serializableStatus;
    }

    public void setSerializableStatus(String serializableStatus) {
        this.serializableStatus = serializableStatus;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getUnitPriceIn() {
        return unitPriceIn;
    }

    public void setUnitPriceIn(Double unitPriceIn) {
        this.unitPriceIn = unitPriceIn;
    }

    public double getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(double discountAmt) {
        this.discountAmt = discountAmt;
    }

    public Double getCess() {
        return cess;
    }

    public void setCess(Double cess) {
        this.cess = cess;
    }

    public boolean isDiscountInPercent() {
        return isDiscountInPercent;
    }

    public void setDiscountInPercent(boolean discountInPercent) {
        isDiscountInPercent = discountInPercent;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public long getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(long returnQty) {
        this.returnQty = returnQty;
    }

    public String getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(String remainingQty) {
        this.remainingQty = remainingQty;
    }

    public double getAmtexclusivetax() {
        return amtexclusivetax;
    }

    public void setAmtexclusivetax(double amtexclusivetax) {
        this.amtexclusivetax = amtexclusivetax;
    }

    public long getTaxid() {
        return taxid;
    }

    public void setTaxid(long taxid) {
        this.taxid = taxid;
    }

    public String getTaxpercent() {
        return taxpercent;
    }

    public void setTaxpercent(String taxpercent) {
        this.taxpercent = taxpercent;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public double getTaxamt() {
        return taxamt;
    }

    public void setTaxamt(double taxamt) {
        this.taxamt = taxamt;
    }

    public double getIgTax() {
        return igTax;
    }

    public void setIgTax(double igTax) {
        this.igTax = igTax;
    }

    public double getAmtinclusivetax() {
        return amtinclusivetax;
    }

    public void setAmtinclusivetax(double amtinclusivetax) {
        this.amtinclusivetax = amtinclusivetax;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getCessTaxAmt() {
        return cessTaxAmt;
    }

    public void setCessTaxAmt(double cessTaxAmt) {
        this.cessTaxAmt = cessTaxAmt;
    }

    public String getTaxAmountSplit() {
        return taxAmountSplit;
    }

    public void setTaxAmountSplit(String taxAmountSplit) {
        this.taxAmountSplit = taxAmountSplit;
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

    public Long getPosamtexclusivetax() {
        return posamtexclusivetax;
    }

    public void setPosamtexclusivetax(Long posamtexclusivetax) {
        this.posamtexclusivetax = posamtexclusivetax;
    }

    public Long getPosamtinclusivetax() {
        return posamtinclusivetax;
    }

    public void setPosamtinclusivetax(Long posamtinclusivetax) {
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

    public Long getReceiveItemFormNo() {
        return receiveItemFormNo;
    }

    public void setReceiveItemFormNo(Long receiveItemFormNo) {
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

    public String getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(String salesOrder) {
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

    public Long getUom() {
        return uom;
    }

    public void setUom(Long uom) {
        this.uom = uom;
    }

    public Long getUomConvertorId() {
        return uomConvertorId;
    }

    public void setUomConvertorId(Long uomConvertorId) {
        this.uomConvertorId = uomConvertorId;
    }

    public Long getUomValue() {
        return uomValue;
    }

    public void setUomValue(Long uomValue) {
        this.uomValue = uomValue;
    }

    public Long getConvertedReturnQty() {
        return convertedReturnQty;
    }

    public void setConvertedReturnQty(Long convertedReturnQty) {
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

    public Long getDeliverdQuantity() {
        return deliverdQuantity;
    }

    public void setDeliverdQuantity(Long deliverdQuantity) {
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

    public Long getConvertedQuantity() {
        return convertedQuantity;
    }

    public void setConvertedQuantity(Long convertedQuantity) {
        this.convertedQuantity = convertedQuantity;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Long getFifoAmount() {
        return fifoAmount;
    }

    public void setFifoAmount(Long fifoAmount) {
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

    public Long getReceived() {
        return received;
    }

    public void setReceived(Long received) {
        this.received = received;
    }

    public Long getQtytotalSent() {
        return qtytotalSent;
    }

    public void setQtytotalSent(Long qtytotalSent) {
        this.qtytotalSent = qtytotalSent;
    }

    public Long getDiscPercent() {
        return discPercent;
    }

    public void setDiscPercent(Long discPercent) {
        this.discPercent = discPercent;
    }

    public Long getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Long itemAmount) {
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

    public Long getItemCommisionAmount() {
        return itemCommisionAmount;
    }

    public void setItemCommisionAmount(Long itemCommisionAmount) {
        this.itemCommisionAmount = itemCommisionAmount;
    }

    public Long getItemCommisionConfigAmount() {
        return itemCommisionConfigAmount;
    }

    public void setItemCommisionConfigAmount(Long itemCommisionConfigAmount) {
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

    public Long getServiceRemainingQty() {
        return serviceRemainingQty;
    }

    public void setServiceRemainingQty(Long serviceRemainingQty) {
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

    public Long getpORemaningQty() {
        return pORemaningQty;
    }

    public void setpORemaningQty(Long pORemaningQty) {
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

    public Long getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(Long deliveredQuantity) {
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Long getQtySent() {
        return qtySent;
    }

    public void setQtySent(Long qtySent) {
        this.qtySent = qtySent;
    }

    public Long getProFormaSalesInvoiceDetailsId() {
        return proFormaSalesInvoiceDetailsId;
    }

    public void setProFormaSalesInvoiceDetailsId(Long proFormaSalesInvoiceDetailsId) {
        this.proFormaSalesInvoiceDetailsId = proFormaSalesInvoiceDetailsId;
    }

    public Long getProFormaSalesInvoiceId() {
        return proFormaSalesInvoiceId;
    }

    public void setProFormaSalesInvoiceId(Long proFormaSalesInvoiceId) {
        this.proFormaSalesInvoiceId = proFormaSalesInvoiceId;
    }
}
