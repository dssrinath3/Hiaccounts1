package in.hiaccounts.hinext.inventory.model.inventory_pos;

import java.util.ArrayList;
import java.util.List;

import in.hiaccounts.hinext.item.model.SelectItemIDData;
import in.hiaccounts.hinext.purchase.model.purchase_pos.exception.ItemNotFoundException;
import in.hiaccounts.hinext.purchase.model.purchase_pos.exception.QuantityOutOfRangeException;

/**
 * Created by administrator on 27/1/18.
 */

public class InventoryPosCreator {

    String TAG = InventoryPosCreator.class.getSimpleName();
    private List<InventoryPosCartItem> inventoryPosList = new ArrayList<>();
    private int totalItems = 0;

    public void addItem(InventoryPosCartItem inventoryposCartItem, SelectItemIDData itemId) {
        if (inventoryposCartItem.getItemDatum().getItemId().getItemCode() != null) {

                boolean isUpdate = false;
                if (inventoryPosList.size() > 0) {
                    for (int i = 0; i < inventoryPosList.size(); i++) {
                        if (inventoryposCartItem.getItemDatum().getItemId().getItemCode() == inventoryPosList.get(i).getItemDatum().getItemId().getItemCode()) {

                            inventoryPosList.set(i, inventoryposCartItem);
                            isUpdate = true;

                        }
                    }
                }

                if (!isUpdate) {
                    inventoryPosList.add(inventoryposCartItem);
                }

        }else {

            inventoryPosList.add(inventoryposCartItem);
        }

    }

    public List<InventoryPosCartItem> getItems() {

        List<InventoryPosCartItem> inventoryPosCartItems = new ArrayList<InventoryPosCartItem>();
        if (inventoryPosList != null && inventoryPosList.size() > 0) {
            for (int i = 0; i < inventoryPosList.size(); i++) {

                InventoryPosCartItem posCartItem = inventoryPosList.get(i);


                inventoryPosCartItems.add(posCartItem);
            }
        }
        return inventoryPosCartItems;
    }

    public void update(InventoryPosCartItem posCartItem, int position) throws ItemNotFoundException, QuantityOutOfRangeException {

        if (inventoryPosList != null)
            inventoryPosList.set(position, posCartItem);


    }

    public void clear() {
        inventoryPosList.clear();
    }

    public void delete(List<InventorySelectItemData> selectItemForDelete) {

        for (int i = 0; i < selectItemForDelete.size(); i++) {
            for (int j = 0; j < inventoryPosList.size(); j++) {
                if (inventoryPosList.get(j).getItemDatum().getItemId().getItemCode() == selectItemForDelete.get(i).getItemId().getItemCode()) {
                    inventoryPosList.remove(j);

                }
            }

        }
    }
}
