package in.hiaccounts.model.realm_model.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Realm_RestraPosTableOrderCartItem extends RealmObject {
    @PrimaryKey
    private int id;
    public Realm_SelectItemList itemDatum;
    public int totalItems;
    public double totalPrice;

/*    public Realm_RestraPosTableOrderCartItem(Realm_SelectItemList itemDatum, int totalItems) {
        this.itemDatum = itemDatum;
        this.totalItems = totalItems;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Realm_SelectItemList getItemDatum() {
        return itemDatum;
    }

    public void setItemDatum(Realm_SelectItemList itemDatum) {
        this.itemDatum = itemDatum;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
