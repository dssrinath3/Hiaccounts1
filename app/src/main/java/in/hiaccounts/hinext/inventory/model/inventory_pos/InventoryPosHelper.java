package in.hiaccounts.hinext.inventory.model.inventory_pos;

/**
 * Created by administrator on 29/1/18.
 */

public class InventoryPosHelper {
    private static InventoryPosCreator inventoryPosCreator = new InventoryPosCreator();

    /**
     * Retrieve the Pos cart. Call this before perform any manipulation on the Pos cart.
     *
     * @return the pos cart
     */
    public static InventoryPosCreator getPosCreator() {
        if (inventoryPosCreator == null) {
            inventoryPosCreator = new InventoryPosCreator();
        }

        return inventoryPosCreator;
    }
}
