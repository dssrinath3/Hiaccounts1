package in.hiaccounts.model.return_items.return_pos;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.hiaccounts.model.checkout.exception.ItemNotFoundException;
import in.hiaccounts.model.checkout.exception.QuantityOutOfRangeException;
import in.hiaccounts.model.return_items.SelectedItemsList;

/**
 * Created by Admin on 2/16/2017.
 */

public class Return_PosCreator {

    String TAG=Return_PosCreator.class.getSimpleName();
    private Map<Integer, Return_PosCartItem> posCartItemMap = new HashMap<>();

    private float totalTaxAmt = 0;
    private float totalPrice = 0;

    /**
     * Add a quantity of a certain {@link Return_PosCartItem} item to this pos cart
     *
     * @param posCartItem the item will be added to this pos cart
     * @param itemId  to be added
     */
    public void addItem(Return_PosCartItem posCartItem,int itemId){
        posCartItemMap.put(itemId,posCartItem);
    }

     /**
     * Get a map of products to their quantities in the shopping cart
     *
     * @return A map from product to its quantity in this shopping cart
     */
    public List<Return_PosCartItem> getItems() {
        List<Return_PosCartItem> posCartItems = new ArrayList<Return_PosCartItem>();
        for (Map.Entry<Integer, Return_PosCartItem> entry : posCartItemMap.entrySet()) {

            //PosCartItem postCartItem = new PosCartItem();
            Return_PosCartItem posCartItem=entry.getValue();
            posCartItems.add(posCartItem);
    }

        return posCartItems;
    }

    public void update(SelectedItemsList itemDatum) throws ItemNotFoundException,QuantityOutOfRangeException {
        if (!posCartItemMap.containsKey(itemDatum.getItemId())) throw new ItemNotFoundException();

        int remaining_quantity=itemDatum.getQty()-itemDatum.getReturnQty();
        itemDatum.setRemainingQty(remaining_quantity);


    }


    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getTotalTaxtAmt() {
        return totalTaxAmt;
    }

    public void setTotalTaxtAmt(float totalTaxAmt) {
        this.totalTaxAmt = totalTaxAmt;
    }

/*


    */
/**
     * Set new quantity for a {@link GetItemDatum} item in this pos cart
     *
     * @param itemDatum the item which quantity will be updated
     * @param quantity the new quantity will be assigned for the item
     * @throws ItemNotFoundException    if the item is not found in this pos cart.
     * @throws QuantityOutOfRangeException if the quantity is negative
     *//*

    public void update(GetItemDatum itemDatum, int quantity) throws ItemNotFoundException,QuantityOutOfRangeException {
        if (!posCartItemMap.containsKey(itemDatum.getItemId())) throw new ItemNotFoundException();
        if (quantity < 0)
            throw new QuantityOutOfRangeException(quantity + " is not a valid quantity. It must be non-negative.");

        int previousQuantity = itemDatum.getItemQuantity();

        itemDatum.setItemQuantity(quantity);
        UtilView.showLogCat(TAG,"udpate quantity of item "+itemDatum.getItemName()+" ,previous quatity : "+previousQuantity+", new quantity: "+quantity);

    }
*/
/*

    public double getTotalPrice() {
        return_items totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
*//*


    */
/**
     * Set new unit price for a {@link GetItemDatum} item in this pos cart
     *
     * @param itemDatum the item which quantity will be updated
     * @param unitPrice the new price will be assigned for the item
     * @throws ItemNotFoundException    if the item is not found in this pos cart.
     * @throws QuantityOutOfRangeException if the quantity is negative
     *//*


    public void updateUnitPrice(GetItemDatum itemDatum, BigDecimal unitPrice) throws ItemNotFoundException, QuantityOutOfRangeException {
        if (!posCartItemMap.containsKey(itemDatum.getItemId())) throw new ItemNotFoundException();
        BigDecimal previousPrice=itemDatum.getPrice();

        itemDatum.setUnitPrice(Float.parseFloat(String.valueOf(unitPrice)));
        itemDatum.setPrice(unitPrice);


    }

    public void updateitemPriceIncludeTax(GetItemDatum itemDatum, double itmPrice) throws ItemNotFoundException, QuantityOutOfRangeException {
        if (!posCartItemMap.containsKey(itemDatum.getItemId())) throw new ItemNotFoundException();
        double prvsitemTotalamt=itemDatum.getItemTotalAmount();
        itemDatum.setItemTotalAmount(itmPrice);
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
*/

    public void clear() {
        posCartItemMap.clear();
        totalPrice=0;

    }


}
