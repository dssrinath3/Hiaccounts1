package in.hiaccounts.model.return_items.return_pos;

import java.math.BigDecimal;

import in.hiaccounts.model.return_items.SelectedItemsList;

/**
 * Created by Admin on 2/16/2017.
 */

public class Return_PosCartItem {

    public SelectedItemsList selectedItemsList;
    public int totalItems;
    public BigDecimal totalPrice;


    public Return_PosCartItem(){

    }


    public Return_PosCartItem(SelectedItemsList selectedItemsList) {
        this.selectedItemsList=selectedItemsList;
    }

    public SelectedItemsList getSelectedItemsList() {
        return selectedItemsList;
    }

    public void setSelectedItemsList(SelectedItemsList selectedItemsList) {
        this.selectedItemsList = selectedItemsList;
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
