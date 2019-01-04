package in.hiaccounts.hinext.inventory.model.inventory_pos;

/**
 * Created by administrator on 27/1/18.
 */

public class    InventoryPosCartItem {
    public InventorySelectItemData itemDatum;
    public int totalItems;

    public InventoryPosCartItem(InventorySelectItemData itemDatum, int totalItems) {
        this.itemDatum = itemDatum;
        this.totalItems = totalItems;
    }

    public InventorySelectItemData getItemDatum() {
        return itemDatum;
    }

    public void setItemDatum(InventorySelectItemData itemDatum) {
        this.itemDatum = itemDatum;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
