package in.hiaccounts.hinext.generaltransaction.model;


import java.util.ArrayList;
import java.util.List;

import in.hiaccounts.hinext.generaltransaction.model.payee_account.Account;
import in.hiaccounts.hinext.sales.model.checkout.SelectedItemsList;
import in.hiaccounts.hinext.sales.model.sales_pos.SalesPosCartItem;
import in.hiaccounts.hinext.sales.model.sales_pos.exception.ItemNotFoundException;
import in.hiaccounts.hinext.sales.model.sales_pos.exception.QuantityOutOfRangeException;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 2/16/2017.
 */

public class AccountPosCreator {

    String TAG = AccountPosCreator.class.getSimpleName();
    private ArrayList<Account> accountList = new ArrayList<>();
    /**
     * Add a quantity of a certain {@link SalesPosCartItem} item to this pos cart
     *
     * @param account the item will be added to this pos cart
     */
    public void addAccount(Account account) {
        accountList.add(account);
        UtilView.showLogCat("AccountPosCreator add Account now size ",""+accountList.size());
    }

    /**
     * Get a map of products to their quantities in the shopping cart
     *
     * @return A map from product to its quantity in this shopping cart
     */
    public List<Account> getAccounts() {
        List<Account> accountList1 = new ArrayList<Account>();
      /*  if (salesposCartItemMap != null && salesposCartItemMap.size() > 0) {
            for (Map.Entry<Long, RestraPosCartItem> entry : salesposCartItemMap.entrySet()) {
                //PosCartItem postCartItem = new PosCartItem();
                RestraPosCartItem posCartItem = entry.getValue();
                salesposCartItems.add(posCartItem);
            }
        }*/
        if (accountList != null && accountList.size() > 0) {
            for (int i = 0; i < accountList.size(); i++) {
                //PosCartItem postCartItem = new PosCartItem();
                Account account = accountList.get(i);
                accountList1.add(account);
            }
        }
        return accountList1;
    }


    /**
     * Set new quantity for a {@link SelectedItemsList} item in this pos cart
     *
     * @param //itemDatum the item which quantity will be updated
     * @param //quantity  the new quantity will be assigned for the item
     * @throws ItemNotFoundException       if the item is not found in this pos cart.
     * @throws QuantityOutOfRangeException if the quantity is negative
     */
    public void update(Account account, int position) {

        accountList.set(position, account);

    }


    public void clear() {
        accountList.clear();
    }


}
