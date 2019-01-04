package in.hiaccounts.hinext.restaurant.model.restra_pos.exception;

import java.math.BigDecimal;

import in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList;

public class RestraPosTableOrderCartItem {
    public SelectedItemsList itemDatum;
    public int totalItems;
    public BigDecimal totalPrice;

    public RestraPosTableOrderCartItem(SelectedItemsList itemDatum, int totalItems) {
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
