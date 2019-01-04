package in.hiaccounts.model.checkout;

import java.math.BigDecimal;

import in.hiaccounts.model.pos.SelectedItemsList;

/**
 * Created by Admin on 2/16/2017.
 */

public class PosCartItem {

    public SelectedItemsList itemDatum;
    public int totalItems;
    public BigDecimal totalPrice;


    public PosCartItem(){

    }
    public PosCartItem(SelectedItemsList itemDatum, int totalItems) {
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
