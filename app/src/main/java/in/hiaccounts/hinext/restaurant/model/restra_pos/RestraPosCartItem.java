package in.hiaccounts.hinext.restaurant.model.restra_pos;

import java.math.BigDecimal;

import in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList;


/**
 * Created by Prateek on 2/16/2017.
 */

public class RestraPosCartItem {

    public SelectedItemsList itemDatum;
    public int totalItems;
    public BigDecimal totalPrice;



    public RestraPosCartItem(SelectedItemsList itemDatum, int totalItems) {
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
