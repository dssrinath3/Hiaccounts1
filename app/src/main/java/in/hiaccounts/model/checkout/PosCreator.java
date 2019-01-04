package in.hiaccounts.model.checkout;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import in.hiaccounts.model.checkout.exception.ItemNotFoundException;
import in.hiaccounts.model.checkout.exception.QuantityOutOfRangeException;
import in.hiaccounts.model.pos.SelectedItemsList;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/16/2017.
 */

public class PosCreator {

    String TAG=PosCreator.class.getSimpleName();
    private Map<Long, PosCartItem> posCartItemMap = new HashMap<>();
    //private Map<String, PosCartItem> posSerializableCartItemMap = new HashMap<>();

    ArrayList<PosCartItem>serializeItemList=new ArrayList<>();

    private int totalItems = 0;
    private double totalPrice = 0d;


    /**
     * Add a quantity of a certain {@link PosCartItem} item to this pos cart
     *
     * @param posCartItem the item will be added to this pos cart
     * @param itemId  to be added
     */
    public void addItem(PosCartItem posCartItem,Long itemId){
        if (posCartItem.getItem().getSerializableStatus()==null){
            if (posCartItemMap.containsKey(itemId)){
                PosCartItem cartItem=posCartItemMap.get(itemId);
                posCartItemMap.remove(cartItem);
                totalItems += 1;
                posCartItem.setTotalItems(totalItems);
                posCartItemMap.put(itemId,posCartItem);
               // update(cartItem.getItem(),cartItem.getItem().getItemQuantity());

            }else {
                /*if(posCartItem.getItem().getQty()!=0){
                    posCartItem.getItem().setItemQuantity(posCartItem.getItem().getQty());
                }else {
                    posCartItem.getItem().setItemQuantity(1);
                }*/
                totalItems += 1;
                posCartItem.setTotalItems(totalItems);
                posCartItemMap.put(itemId,posCartItem);
            }


        }

        if (posCartItem.getItem().getSerializableStatus()!=null && posCartItem.getItem().getSerializableStatus().equals(Constant.ITEM_SERIALIZABLE)){

            totalItems += 1;
            posCartItem.setTotalItems(totalItems);
            serializeItemList.add(posCartItem);

        }
        if (posCartItem.getItem().getSerializableStatus()!=null && posCartItem.getItem().getSerializableStatus().equals(Constant.ITEM_BULK)){

            if (posCartItemMap.containsKey(itemId)){
                PosCartItem cartItem=posCartItemMap.get(itemId);
                posCartItemMap.remove(cartItem);
                totalItems += 1;
                posCartItem.setTotalItems(totalItems);
                posCartItemMap.put(itemId,posCartItem);


            }else {

                totalItems += 1;
                posCartItem.setTotalItems(totalItems);
                posCartItemMap.put(itemId,posCartItem);
            }



        }





    }

     /**
     * Get a map of products to their quantities in the shopping cart
     *
     * @return A map from product to its quantity in this shopping cart
     */
    public List<PosCartItem> getItems() {
        List<PosCartItem> posCartItems = new ArrayList<PosCartItem>();
        if (posCartItemMap!=null && posCartItemMap.size()>0){
            for (Map.Entry<Long, PosCartItem> entry : posCartItemMap.entrySet()) {
                //PosCartItem postCartItem = new PosCartItem();
                PosCartItem posCartItem=entry.getValue();
                posCartItems.add(posCartItem);
            }
        }
        if (serializeItemList!=null && serializeItemList.size()>0){
            for (int i=0;i<serializeItemList.size();i++){
                posCartItems.add(serializeItemList.get(i));

            }
        }



        return posCartItems;
    }


    /**
     * Set new quantity for a {@link SelectedItemsList} item in this pos cart
     *
     * @param itemDatum the item which quantity will be updated
     * @param quantity the new quantity will be assigned for the item
     * @throws ItemNotFoundException    if the item is not found in this pos cart.
     * @throws QuantityOutOfRangeException if the quantity is negative
     */
    public void update(SelectedItemsList itemDatum, long quantity) throws ItemNotFoundException,QuantityOutOfRangeException {
        if (!posCartItemMap.containsKey(itemDatum.getItemId())) throw new ItemNotFoundException();
        if (quantity < 0)
            throw new QuantityOutOfRangeException(quantity + " is not a valid quantity. It must be non-negative.");
        itemDatum.setItemQuantity(quantity);


    }


/*

    public double getTotalPrice() {
        return_items totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
*/

    /**
     * Set new unit price for a {@link SelectedItemsList} item in this pos cart
     *
     * @param itemDatum the item which quantity will be updated
     * @param unitPrice the new price will be assigned for the item
     * @throws ItemNotFoundException    if the item is not found in this pos cart.
     * @throws QuantityOutOfRangeException if the quantity is negative
     */

    public void updateUnitPrice(SelectedItemsList itemDatum, BigDecimal unitPrice) throws ItemNotFoundException, QuantityOutOfRangeException {
        if (itemDatum.getSerializableStatus()==null || itemDatum.getSerializableStatus().equals(Constant.ITEM_BULK)){
            if (!posCartItemMap.containsKey(itemDatum.getItemId())) throw new ItemNotFoundException();
            itemDatum.setUnitPrice(Double.parseDouble(String.valueOf(unitPrice)));
            itemDatum.setSalesPrice(Double.parseDouble(String.valueOf(unitPrice)));
            itemDatum.setPurchasePrice(Double.parseDouble(String.valueOf(unitPrice)));
            itemDatum.setPrice(unitPrice);
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

    public void clear() {
        posCartItemMap.clear();
        serializeItemList.clear();
        totalPrice=0d;

    }

    public void delete(List<SelectedItemsList> selectItemForDelete) {


        for (int i=0;i<selectItemForDelete.size();i++) {

            if (serializeItemList!=null) {
                for (int j = 0; j < serializeItemList.size(); j++) {

                    if (serializeItemList.get(j).getItem().getItemId()==selectItemForDelete.get(i).getItemId()){
                        serializeItemList.remove(j);
                    }
                }
            }
            for (Iterator<Map.Entry<Long, PosCartItem>> it = posCartItemMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Long, PosCartItem> entry = it.next();
                if (entry.getKey()==selectItemForDelete.get(i).getItemId()) {
                    it.remove();
                    totalItems-=1;
                    entry.getValue().setTotalItems(totalItems);
                }
            }
        }


    }
    /**
     * Set remove status for a {@link SelectedItemsList} item in this pos cart
     *
     * @param itemDatum the item which want to be removed
     * @param select the status select true or false aasigned
     * @throws ItemNotFoundException    if the item is not found in this pos cart.
     */
    public void updateDeleteStatus(SelectedItemsList itemDatum, boolean select) throws ItemNotFoundException {
        if (!posCartItemMap.containsKey(itemDatum.getItemId())) throw new ItemNotFoundException();
        itemDatum.setSelect(select);
    }



    /**
     * Get a map of products to their quantities in the shopping cart
     *
     * @return A map from product to its quantity in this shopping cart
     */
    public List<SelectedItemsList> getSerilizableItems() {

        List<SelectedItemsList>serialzableItemList=new ArrayList<SelectedItemsList>();
        if (serializeItemList!=null && serializeItemList.size()>0){
            for (int i=0;i<serializeItemList.size();i++){
                serialzableItemList.add(serializeItemList.get(i).getItem());
                UtilView.showLogCat(TAG,"getSerializable item "+serialzableItemList.get(i).getItemName()+" : "+serialzableItemList.get(i).getSerializableNumbers());
            }
        }
        return serialzableItemList;
    }



}
