package in.hiaccounts.hinext.sales.model.sales_pos;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;
import in.hiaccounts.hinext.sales.model.sales_pos.exception.ItemNotFoundException;
import in.hiaccounts.hinext.sales.model.sales_pos.exception.QuantityOutOfRangeException;

/**
 * Created by Prateek on 2/16/2017.
 */

public class SalesPosCreator1 {

    String TAG = SalesPosCreator1.class.getSimpleName();
    private Map<Long, SalesPosCartItem> salesposCartItemMap = new HashMap<>();
    private int totalItems = 0;
    private double totalPrice = 0d;


    /**
     * Add a quantity of a certain {@link SalesPosCartItem} item to this pos cart
     *
     * @param salesposCartItem the item will be added to this pos cart
     * @param itemId           to be added
     */
    public void addItem(SalesPosCartItem salesposCartItem, Long itemId) {

        if (salesposCartItemMap.containsKey(itemId)) {
            SalesPosCartItem cartItem = salesposCartItemMap.get(itemId);
            salesposCartItemMap.remove(cartItem);
            totalItems += 1;
            salesposCartItem.setTotalItems(totalItems);
            salesposCartItemMap.put(itemId, salesposCartItem);
        } else {
            totalItems += 1;
            salesposCartItem.setTotalItems(totalItems);
            salesposCartItemMap.put(itemId, salesposCartItem);
        }
    }

    /**
     * Get a map of products to their quantities in the shopping cart
     *
     * @return A map from product to its quantity in this shopping cart
     */
    public List<SalesPosCartItem> getItems() {
        List<SalesPosCartItem> salesposCartItems = new ArrayList<SalesPosCartItem>();
        if (salesposCartItemMap != null && salesposCartItemMap.size() > 0) {
            for (Map.Entry<Long, SalesPosCartItem> entry : salesposCartItemMap.entrySet()) {
                //PosCartItem postCartItem = new PosCartItem();
                SalesPosCartItem posCartItem = entry.getValue();
                salesposCartItems.add(posCartItem);
            }
        }
        return salesposCartItems;
    }


    /**
     * Set new quantity for a {@link SelectedItemsList} item in this pos cart
     *
     * @param itemDatum the item which quantity will be updated
     * @param quantity  the new quantity will be assigned for the item
     * @throws ItemNotFoundException       if the item is not found in this pos cart.
     * @throws QuantityOutOfRangeException if the quantity is negative
     */
    public void update(SelectedItemsList itemDatum, long quantity) throws ItemNotFoundException, QuantityOutOfRangeException {
        if (!salesposCartItemMap.containsKey(itemDatum.getItemId()))
            throw new ItemNotFoundException();
        if (quantity < 0)
            throw new QuantityOutOfRangeException(quantity + " is not a valid quantity. It must be non-negative.");
        itemDatum.setItemQuantity(quantity);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void clear() {
        salesposCartItemMap.clear();
        totalPrice = 0d;
    }

    public void delete(List<SelectedItemsList> selectItemForDelete) {

        for (int i = 0; i < selectItemForDelete.size(); i++) {

            for (Iterator<Map.Entry<Long, SalesPosCartItem>> it = salesposCartItemMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Long, SalesPosCartItem> entry = it.next();
                if (entry.getKey() == selectItemForDelete.get(i).getItemId()) {
                    it.remove();
                    totalItems -= 1;
                    entry.getValue().setTotalItems(totalItems);
                }
            }
        }
    }
}
