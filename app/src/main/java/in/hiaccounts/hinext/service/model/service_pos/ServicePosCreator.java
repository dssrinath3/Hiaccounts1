package in.hiaccounts.hinext.service.model.service_pos;

import java.util.ArrayList;
import java.util.List;

import in.hiaccounts.hinext.service.model.SelectedItemsList;
import in.hiaccounts.utility.Constant;

/**
 * Created by administrator on 23/2/18.
 */

public class ServicePosCreator {
    String TAG = ServicePosCreator.class.getSimpleName();
    private ArrayList<ServicePosCartItem> servicePosCartItemList = new ArrayList<>();
    private int totalItems = 0;
    private double totalPrice = 0d;


    /**
     * Add a quantity of a certain {@link ServicePosCartItem} item to this pos cart
     *
     * @param servicePosCartItem the item will be added to this pos cart
     */
    public void addItem(ServicePosCartItem servicePosCartItem) {

        if (servicePosCartItem.getItem().getItemTypeName() != null) {
            if (servicePosCartItem.getItem().getItemTypeName().equals(Constant.ITEMTYPE_PRODUCT)) {
                boolean isUpdate = false;
                if (servicePosCartItemList.size() > 0) {
                    for (int i = 0; i < servicePosCartItemList.size(); i++) {
                        if (servicePosCartItem.getItem().getItemId() == servicePosCartItemList.get(i).getItem().getItemId()) {
                            servicePosCartItemList.set(i, servicePosCartItem);
                            isUpdate = true;
                        }
                    }
                }

                if (!isUpdate) {
                    servicePosCartItemList.add(servicePosCartItem);
                }
            } else if (servicePosCartItem.getItem().getItemTypeName().equals(Constant.ITEMTYPE_SERVICE)) {
                servicePosCartItemList.add(servicePosCartItem);
            }
        }else {
            servicePosCartItemList.add(servicePosCartItem);
        }


        totalItems += 1;
        servicePosCartItem.setTotalItems(totalItems);

    }

    /**
     * Get a map of products to their quantities in the shopping cart
     *
     * @return A map from product to its quantity in this shopping cart
     */
    public List<ServicePosCartItem> getItems() {
        List<ServicePosCartItem> servicePosCartItems = new ArrayList<ServicePosCartItem>();

        if (servicePosCartItemList != null && servicePosCartItemList.size() > 0) {
            for (int i = 0; i < servicePosCartItemList.size(); i++) {
                //PosCartItem postCartItem = new PosCartItem();
                ServicePosCartItem posCartItem = servicePosCartItemList.get(i);
                servicePosCartItems.add(posCartItem);
            }
        }
        return servicePosCartItems;
    }


    /**
     * Set new quantity for a {@link ServicePosCartItem} item in this pos cart
     *
     * @param //itemDatum the item which quantity will be updated
     * @param //quantity  the new quantity will be assigned for the item
     * @throws       // if the item is not found in this pos cart.
     * @throws // if the quantity is negative
     */
    public void update(ServicePosCartItem selectedPosCartItem, int position) {

        servicePosCartItemList.set(position, selectedPosCartItem);

    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void clear() {
        servicePosCartItemList.clear();
        totalPrice = 0d;
    }

    public void delete(List<SelectedItemsList> selectItemForDelete) {

        for (int i = 0; i < selectItemForDelete.size(); i++) {
            for (int j = 0; j < servicePosCartItemList.size(); j++) {
                if (servicePosCartItemList.get(j).getItem().getItemId() == selectItemForDelete.get(i).getItemId()) {
                    servicePosCartItemList.remove(j);
                    // totalItems -= 1;
                    //servicePosCartItemList.get(j).setTotalItems(totalItems);
                }
            }


        }
    }
}
