package in.hiaccounts.hinext.purchase.model.purchase_pos;


import java.util.ArrayList;
import java.util.List;

import in.hiaccounts.hinext.purchase.model.purchase_pos.exception.ItemNotFoundException;
import in.hiaccounts.hinext.purchase.model.purchase_pos.exception.QuantityOutOfRangeException;
import in.hiaccounts.utility.Constant;


/**
 * Created by Prateek on 2/16/2017.
 */

public class PurchasePosCreator {

    String TAG = PurchasePosCreator.class.getSimpleName();
    private ArrayList<PurchasePosCartItem> purchasePosList = new ArrayList<>();
    private int totalItems = 0;
    private double totalPrice = 0d;


    /**
     * Add a quantity of a certain {@link PurchasePosCartItem} item to this pos cart
     *
     * @param salesposCartItem the item will be added to this pos cart
     * @param itemId           to be added
     */
    public void addItem(PurchasePosCartItem salesposCartItem, Long itemId) {
        if (salesposCartItem.getItem().getItemTypeName() != null) {
            if (salesposCartItem.getItem().getItemTypeName().equals(Constant.ITEMTYPE_PRODUCT)) {
                boolean isUpdate = false;
                if (purchasePosList.size() > 0) {
                    for (int i = 0; i < purchasePosList.size(); i++) {
                        if (salesposCartItem.getItem().getItemId() == purchasePosList.get(i).getItem().getItemId()) {
                            purchasePosList.set(i, salesposCartItem);
                            isUpdate = true;
                        }
                    }
                }

                if (!isUpdate) {
                    purchasePosList.add(salesposCartItem);
                }
            } else if (salesposCartItem.getItem().getItemTypeName().equals(Constant.ITEMTYPE_SERVICE)) {
                purchasePosList.add(salesposCartItem);
            }
        }else {
            purchasePosList.add(salesposCartItem);
        }

        /*if (salesposCartItemMap.containsKey(itemId)) {
            PurchasePosCartItem cartItem = salesposCartItemMap.get(itemId);
            salesposCartItemMap.remove(cartItem);
            totalItems += 1;
            salesposCartItem.setTotalItems(totalItems);
            salesposCartItemMap.put(itemId, salesposCartItem);
        } else {
            totalItems += 1;
            salesposCartItem.setTotalItems(totalItems);
            salesposCartItemMap.put(itemId, salesposCartItem);
        }*/
    }

    /**
     * Get a map of products to their quantities in the shopping cart
     *
     * @return A map from product to its quantity in this shopping cart
     */
    public List<PurchasePosCartItem> getItems() {
       /* List<PurchasePosCartItem> salesposCartItems = new ArrayList<PurchasePosCartItem>();
        if (salesposCartItemMap != null && salesposCartItemMap.size() > 0) {
            for (Map.Entry<Long, PurchasePosCartItem> entry : salesposCartItemMap.entrySet()) {
                //PosCartItem postCartItem = new PosCartItem();
                PurchasePosCartItem posCartItem = entry.getValue();
                salesposCartItems.add(posCartItem);
            }
        }*/
        List<PurchasePosCartItem> salesposCartItems = new ArrayList<PurchasePosCartItem>();
        if (purchasePosList != null && purchasePosList.size() > 0) {
            for (int i = 0; i < purchasePosList.size(); i++) {
                //PosCartItem postCartItem = new PosCartItem();
                PurchasePosCartItem posCartItem = purchasePosList.get(i);
                salesposCartItems.add(posCartItem);
            }
        }
        return salesposCartItems;
    }


    /**
     * Set new quantity for a {@link SelectedItemsList} item in this pos cart
     *
     * //@param itemDatum the item which quantity will be updated
     * //@param quantity  the new quantity will be assigned for the item
     * @throws ItemNotFoundException       if the item is not found in this pos cart.
     * @throws QuantityOutOfRangeException if the quantity is negative
     */
    public void update(PurchasePosCartItem posCartItem, int position) throws ItemNotFoundException, QuantityOutOfRangeException {
        /*if (!salesposCartItemMap.containsKey(itemDatum.getItemId()))
            throw new ItemNotFoundException();
        if (quantity < 0)
            throw new QuantityOutOfRangeException(quantity + " is not a valid quantity. It must be non-negative.");
        itemDatum.setItemQuantity(quantity);*/

        if (purchasePosList!=null)
            purchasePosList.set(position, posCartItem);


    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void clear() {
        purchasePosList.clear();
        totalPrice = 0d;
    }

    public void delete(List<SelectedItemsList> selectItemForDelete) {

        for (int i = 0; i < selectItemForDelete.size(); i++) {
            for (int j = 0; j < purchasePosList.size(); j++) {
                if (purchasePosList.get(j).getItem().getItemId() == selectItemForDelete.get(i).getItemId()) {
                    purchasePosList.remove(j);
                    // totalItems -= 1;
                    //salesposCartItemList.get(j).setTotalItems(totalItems);
                }
            }
      /*  for (int i = 0; i < selectItemForDelete.size(); i++) {

            for (Iterator<Map.Entry<Long, PurchasePosCartItem>> it = salesposCartItemMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Long, PurchasePosCartItem> entry = it.next();
                if (entry.getKey() == selectItemForDelete.get(i).getItemId()) {
                    it.remove();
                    totalItems -= 1;
                    entry.getValue().setTotalItems(totalItems);
                }
            }*/
        }
    }
}
