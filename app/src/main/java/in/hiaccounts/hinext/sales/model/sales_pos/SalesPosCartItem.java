package in.hiaccounts.hinext.sales.model.sales_pos;

import java.math.BigDecimal;

import in.hiaccounts.hinext.checkout.model.PurchaseItem;
import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;


/**
 * Created by Prateek on 2/16/2017.
 */

public class SalesPosCartItem {

    public SelectedItemsList itemDatum;
    public int totalItems;
    public BigDecimal totalPrice;


    public SalesPosCartItem(PurchaseItem saveItem, int totalItems) {

    }

    public SalesPosCartItem(SelectedItemsList itemDatum, int totalItems) {
        this.itemDatum = itemDatum;
        this.totalItems = totalItems;
    }

    public SelectedItemsList getItem() {
        return itemDatum;
    }

    public void setItem(SelectedItemsList itemDatum) {
        this.itemDatum = itemDatum;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
