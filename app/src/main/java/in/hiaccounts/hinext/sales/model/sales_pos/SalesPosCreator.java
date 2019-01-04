package in.hiaccounts.hinext.sales.model.sales_pos;


import java.util.ArrayList;
import java.util.List;

import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;
import in.hiaccounts.hinext.sales.model.sales_pos.exception.ItemNotFoundException;
import in.hiaccounts.hinext.sales.model.sales_pos.exception.QuantityOutOfRangeException;
import in.hiaccounts.utility.Constant;

/**
 * Created by Prateek on 2/16/2017.
 */

public class SalesPosCreator {

    String TAG = SalesPosCreator.class.getSimpleName();
    private ArrayList<SalesPosCartItem> salesposCartItemList = new ArrayList<>();
    private int totalItems = 0;
    private double totalPrice = 0d;


    /**
     * Add a quantity of a certain {@link SalesPosCartItem} item to this pos cart
     *
     * @param salesposCartItem the item will be added to this pos cart
     */
    public void addItem(SalesPosCartItem salesposCartItem) {

        if (salesposCartItem.getItem().getItemTypeName() != null) {
            if (salesposCartItem.getItem().getItemTypeName().equals(Constant.ITEMTYPE_PRODUCT)) {
                boolean isUpdate = false;
                if (salesposCartItemList.size() > 0) {
                    for (int i = 0; i < salesposCartItemList.size(); i++) {
                        if (salesposCartItem.getItem().getItemId() == salesposCartItemList.get(i).getItem().getItemId()) {
                            salesposCartItemList.set(i, salesposCartItem);
                            isUpdate = true;
                        }
                    }
                }

                if (!isUpdate) {
                    salesposCartItemList.add(salesposCartItem);
                }
            } else if (salesposCartItem.getItem().getItemTypeName().equals(Constant.ITEMTYPE_SERVICE)) {
                salesposCartItemList.add(salesposCartItem);
            }
        }else {
            salesposCartItemList.add(salesposCartItem);
        }


        totalItems += 1;
        salesposCartItem.setTotalItems(totalItems);

    }

    /**
     * Get a map of products to their quantities in the shopping cart
     *
     * @return A map from product to its quantity in this shopping cart
     */
    public List<SalesPosCartItem> getItems() {
        List<SalesPosCartItem> salesposCartItems = new ArrayList<SalesPosCartItem>();
      /*  if (salesposCartItemMap != null && salesposCartItemMap.size() > 0) {
            for (Map.Entry<Long, RestraPosCartItem> entry : salesposCartItemMap.entrySet()) {
                //PosCartItem postCartItem = new PosCartItem();
                RestraPosCartItem posCartItem = entry.getValue();
                salesposCartItems.add(posCartItem);
            }
        }*/
        if (salesposCartItemList != null && salesposCartItemList.size() > 0) {
            for (int i = 0; i < salesposCartItemList.size(); i++) {
                //PosCartItem postCartItem = new PosCartItem();
                SalesPosCartItem posCartItem = salesposCartItemList.get(i);
                salesposCartItems.add(posCartItem);
            }
        }
        return salesposCartItems;
    }


    /**
     * Set new quantity for a {@link SelectedItemsList} item in this pos cart
     *
     * @param //itemDatum the item which quantity will be updated
     * @param //quantity  the new quantity will be assigned for the item
     * @throws ItemNotFoundException       if the item is not found in this pos cart.
     * @throws QuantityOutOfRangeException if the quantity is negative
     */
    public void update(SalesPosCartItem selectedPosCartItem, int position) {

        salesposCartItemList.set(position, selectedPosCartItem);

    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void clear() {
        salesposCartItemList.clear();
        totalPrice = 0d;
    }

    public void delete(List<SelectedItemsList> selectItemForDelete) {

        for (int i = 0; i < selectItemForDelete.size(); i++) {
            for (int j = 0; j < salesposCartItemList.size(); j++) {
                if (salesposCartItemList.get(j).getItem().getItemId() == selectItemForDelete.get(i).getItemId()) {
                    salesposCartItemList.remove(j);
                    // totalItems -= 1;
                    //salesposCartItemList.get(j).setTotalItems(totalItems);
                }
            }

            /*for (Iterator<Map.Entry<Long, RestraPosCartItem>> it = salesposCartItemMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Long, RestraPosCartItem> entry = it.next();
                if (entry.getKey() == selectItemForDelete.get(i).getItemId()) {
                    it.remove();
                    totalItems -= 1;
                    entry.getValue().setTotalItems(totalItems);
                }
            }*/
        }
    }
}
