package in.hiaccounts.model.restrapos;

/**
 * Created by Admin on 2/13/2017.
 */

public class RestraPos_PageData {
    private String message;

    private TableConfigDetails[] tableConfigDetails;

    private String status;

    private String[] taxList;

    private ItemCategorys[] itemCategorys;

    private Customers[] customers;

    private UserRights userRights;

    private String hiPosServiceCharge;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TableConfigDetails[] getTableConfigDetails() {
        return tableConfigDetails;
    }

    public void setTableConfigDetails(TableConfigDetails[] tableConfigDetails) {
        this.tableConfigDetails = tableConfigDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getTaxList() {
        return taxList;
    }

    public void setTaxList(String[] taxList) {
        this.taxList = taxList;
    }

    public ItemCategorys[] getItemCategorys() {
        return itemCategorys;
    }

    public void setItemCategorys(ItemCategorys[] itemCategorys) {
        this.itemCategorys = itemCategorys;
    }

    public Customers[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customers[] customers) {
        this.customers = customers;
    }

    public UserRights getUserRights() {
        return userRights;
    }

    public void setUserRights(UserRights userRights) {
        this.userRights = userRights;
    }

    public String getHiPosServiceCharge() {
        return hiPosServiceCharge;
    }

    public void setHiPosServiceCharge(String hiPosServiceCharge) {
        this.hiPosServiceCharge = hiPosServiceCharge;
    }
}


