package in.hiaccounts.hinext.restaurant.model.restra_pos;

import android.util.Log;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList;
import in.hiaccounts.hinext.restaurant.model.restra_pos.exception.ItemNotFoundException;
import in.hiaccounts.hinext.restaurant.model.restra_pos.exception.QuantityOutOfRangeException;
import in.hiaccounts.hinext.restaurant.model.restra_pos.exception.RestraPosTableOrderCartItem;
import in.hiaccounts.utility.Constant;

public class RestraPosTableOrderCreator {
    String TAG = RestraPosTableOrderCreator.class.getSimpleName();
    private Map<Long, RestraPosTableOrderCartItem> posCartItemMap = new HashMap<>();
    private int totalItems = 0;
    private double totalPrice = 0d;


    /**
     * Add a quantity of a certain {@link RestraPosTableOrderCartItem} item to this pos cart
     * @param posCartItem the item will be added to this pos cart
     * @param itemId
     */
    public void addItem(RestraPosTableOrderCartItem posCartItem, Long itemId) {

        if (posCartItemMap.containsKey(itemId)) {
            //  RestraPosCartItem cartItem = posCartItemMap.get(itemId);
            //  posCartItemMap.remove(cartItem);
            totalItems += 1;
            posCartItem.setTotalItems(totalItems);
            posCartItemMap.put(itemId, posCartItem);


            update(posCartItem.getItem(),posCartItem.getItem().getItemQuantity()+1);

        } else {
            if (posCartItem.getItem().getItemQuantity()==0) {
                posCartItem.getItem().setItemQuantity(1);
            }
            totalItems += 1;
            posCartItem.setTotalItems(totalItems);
            posCartItemMap.put(itemId, posCartItem);
        }


    }

    /**
     * Get a map of products to their quantities in the shopping cart
     *
     * @return A map from product to its quantity in this shopping cart
     *
     */
    public List<RestraPosTableOrderCartItem> getItems() {
        List<RestraPosTableOrderCartItem> posCartItems = new ArrayList<RestraPosTableOrderCartItem>();
        if (posCartItemMap != null && posCartItemMap.size() > 0) {
            for (Map.Entry<Long, RestraPosTableOrderCartItem> entry : posCartItemMap.entrySet()) {
                //PosCartItem postCartItem = new PosCartItem();
                RestraPosTableOrderCartItem posCartItem = entry.getValue();

                    posCartItems.add(posCartItem);


            }
        }
        return posCartItems;
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
        if (!posCartItemMap.containsKey(itemDatum.getItemId())) throw new ItemNotFoundException();
        if (quantity < 0) throw new QuantityOutOfRangeException(quantity + " is not a valid quantity. It must be non-negative.");
        itemDatum.setItemQuantity(quantity);
    }

    public void updateUnitPrice(SelectedItemsList itemDatum, BigDecimal unitPrice) throws ItemNotFoundException, QuantityOutOfRangeException {
        if (itemDatum.getSerializableStatus() == null || itemDatum.getSerializableStatus().equals(Constant.ITEM_BULK)) {
            if (!posCartItemMap.containsKey(itemDatum.getItemId())) throw new ItemNotFoundException();
            itemDatum.setUnitPrice(Double.parseDouble(String.valueOf(unitPrice)));
            //itemDatum.setSalesPrice(Double.parseDouble(String.valueOf(unitPrice)));
            // itemDatum.setPrice(unitPrice);
        }
    }
    public void updateitemPriceIncludeTax(SelectedItemsList itemDatum, double itmPrice) throws ItemNotFoundException, QuantityOutOfRangeException {
        if (!posCartItemMap.containsKey((itemDatum.getItemId())) )throw new ItemNotFoundException();
        itemDatum.setItemTotalAmount(itmPrice);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public void clear() {
        posCartItemMap.clear();
        totalPrice = 0d;
    }

    public void delete(List<SelectedItemsList> selectItemForDelete) {

        for (int i = 0; i < selectItemForDelete.size(); i++) {

            for (Iterator<Map.Entry<Long, RestraPosTableOrderCartItem>> it = posCartItemMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Long, RestraPosTableOrderCartItem> entry = it.next();
                if (entry.getKey() == selectItemForDelete.get(i).getItemId()) {
                    it.remove();
                    totalItems -= 1;
                    entry.getValue().setTotalItems(totalItems);
                }
            }
        }
    }

    public boolean delete(SelectedItemsList selectItemForDelete) {

        boolean deleteStatus=false;

        for (Iterator<Map.Entry<Long, RestraPosTableOrderCartItem>> it = posCartItemMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Long, RestraPosTableOrderCartItem> entry = it.next();
            if (entry.getKey() == selectItemForDelete.getItemId()) {
                it.remove();
                totalItems -= 1;
                entry.getValue().setTotalItems(totalItems);
                deleteStatus=true;
            }
        }
        return deleteStatus;
    }

    public long checkItemQty(SelectedItemsList item){
        if (posCartItemMap.containsKey(item.getItemId())) {
            RestraPosTableOrderCartItem cartItem = posCartItemMap.get(item.getItemId());
            return cartItem.getItem().getItemQuantity();
        } else {
            return 1l;
        }
    }


}
