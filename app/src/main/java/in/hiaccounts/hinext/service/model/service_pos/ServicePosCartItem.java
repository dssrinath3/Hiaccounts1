package in.hiaccounts.hinext.service.model.service_pos;

import java.math.BigDecimal;

import in.hiaccounts.hinext.service.model.SelectedItemsList;

/**
 * Created by administrator on 23/2/18.
 */

public class ServicePosCartItem {
    public SelectedItemsList itemDatum;
    public int totalItems;
    public BigDecimal totalPrice;



    public ServicePosCartItem(SelectedItemsList itemDatum, int totalItems) {
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
