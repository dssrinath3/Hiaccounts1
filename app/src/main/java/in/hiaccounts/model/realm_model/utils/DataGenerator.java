package in.hiaccounts.model.realm_model.utils;


import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import in.hiaccounts.hinext.application.realm_manager.RealmManager;

import in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.SubRow;
import in.hiaccounts.model.realm_model.data.RealmTemp_SelectItemList;
import in.hiaccounts.model.realm_model.data.Realm_Checkout_SplitTable;
import in.hiaccounts.model.realm_model.data.Realm_RestraPosTableOrderCartItem;
import in.hiaccounts.model.realm_model.data.Realm_SelectItemList;
import in.hiaccounts.model.realm_model.data.Realm_SplitTable;
import in.hiaccounts.model.realm_model.data.Realm_TableData;
import in.hiaccounts.model.realm_model.data.Realm_TempOrders;
import io.realm.RealmList;

/**
 * Created by administrator on 13/3/18.
 */

public class DataGenerator {



    public static Realm_TableData generateTableData(List<in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList> posCartItems, SubRow subRow) {
        Realm_TableData realmTableData = new Realm_TableData();

        if (subRow!=null){
            realmTableData.setTableName(subRow.getTableName());
            realmTableData.setTableVal(subRow.getTableId());
            String ItemCode="";
            RealmList<Realm_RestraPosTableOrderCartItem> restraPosTableList = new RealmList<>();
            List<in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList> selectedItemsLists = new ArrayList<>();
            Realm_RestraPosTableOrderCartItem restraCartItem = new Realm_RestraPosTableOrderCartItem();
            selectedItemsLists.clear();

            for (SelectedItemsList posTableCartItem : posCartItems) {
                in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList realmItemData = new in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList();

                realmItemData.setItemId(posTableCartItem.getItemId());
                realmItemData.setItemCode(posTableCartItem.getItemCode());
                realmItemData.setItemName(posTableCartItem.getItemName());
                realmItemData.setItemQuantity(posTableCartItem.getItemQuantity());
                realmItemData.setItemDesc(posTableCartItem.getItemDesc());
                Log.e("ItemTotalAmountInTax ", String.valueOf(posTableCartItem.getItemTotalAmountInTax()));
                Log.e("ItemTotalAmountExTax( ", String.valueOf(posTableCartItem.getItemTotalAmountExTax()));
                realmItemData.setItemTotalAmountInTax(posTableCartItem.getItemTotalAmountInTax());
                realmItemData.setItemTotalAmountExTax(posTableCartItem.getItemTotalAmountExTax());
                realmItemData.setGstTaxAmt(posTableCartItem.getGstTaxAmt());
                realmItemData.setGstTaxPercantage(posTableCartItem.getGstTaxPercantage());
                if (posTableCartItem.getUnitPrice()!=0.00){
                    realmItemData.setUnitPrice(posTableCartItem.getUnitPrice());
                }else{
                    realmItemData.setUnitPrice(posTableCartItem.getSalesPrice());
                }

                realmItemData.setItemCategoryId(posTableCartItem.getItemCategoryId());
                realmItemData.setItemCategoryName(posTableCartItem.getItemCategoryName());
                realmItemData.setInclusiveJSON(posTableCartItem.getInclusiveJSON());
                realmItemData.setTaxId(posTableCartItem.getTaxid());
                realmItemData.setInputTaxId(posTableCartItem.getInputTaxId());
                realmItemData.setOutputTaxId(posTableCartItem.getOutputTaxId());
                realmItemData.setItemTypeId(posTableCartItem.getItemTypeId());
                realmItemData.setItemTypeName(posTableCartItem.getItemTypeName());
                realmItemData.setItemTotalAmount(posTableCartItem.getItemTotalAmount());
                realmItemData.setTableId(posTableCartItem.getTableId());
                realmItemData.setTableName(posTableCartItem.getTableName());
                realmItemData.setSelect(posTableCartItem.isSelect());
                realmItemData.setFloorId(posTableCartItem.getFloorId());
                realmItemData.setWaiterName(posTableCartItem.getWaiterName());
                realmItemData.setCustomerId(posTableCartItem.getCustomerId());
                realmItemData.setQty(posTableCartItem.getQty());
                selectedItemsLists.add(realmItemData);


                boolean item = selectedItemsLists.contains(posTableCartItem.getItemCode());

                if (item){
                   Log.e("boolitem exist",posTableCartItem.getItemCode());
                }else{
                    Log.e("boolitem not exist",posTableCartItem.getItemCode());
                }

                ItemCode = posTableCartItem.getItemCode();



                restraCartItem.setTotalItems(posCartItems.size());
                restraCartItem.setTotalPrice(0.00);
                restraPosTableList.add(restraCartItem);
            }

            Log.e("selectItemLists ", String.valueOf(selectedItemsLists.size()));
            if (selectedItemsLists!=null){
                    RealmManager.createRestaurentDao().saveRestaurentItemDetails(DataGenerator.generateRestaSelectItem(selectedItemsLists,subRow,ItemCode),ItemCode,subRow);
            }

            realmTableData.setRestraPosTableList(restraPosTableList);

        }


        return realmTableData;
    }

    private static Realm_SelectItemList generateRestaSelectItem(List<SelectedItemsList> itemsListData, SubRow subRow, String ItemCode) {


        Realm_SelectItemList realmSelectItemList = new Realm_SelectItemList();

        for (in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList itemsList :itemsListData){
            if (itemsList!=null){
                try {
                    realmSelectItemList.setTableId(subRow.getTableId());
                    realmSelectItemList.setTableName(subRow.getTableName());

                    if (subRow.getSelected()){
                        realmSelectItemList.setSelect(subRow.getSelected());
                    }else{
                        realmSelectItemList.setSelect(true);
                    }
                    realmSelectItemList.setWaiterName(itemsList.getWaiterName());
                    realmSelectItemList.setFloorId(subRow.getFloorId());
                    realmSelectItemList.setKotSelect(false);
                    realmSelectItemList.setCustomerId(itemsList.getCustomerId());
                    Log.e("CustomerIdData", String.valueOf(itemsList.getCustomerId()));
                    realmSelectItemList.setItemId(itemsList.getItemId());
                    realmSelectItemList.setItemCode(itemsList.getItemCode());
                    realmSelectItemList.setItemName(itemsList.getItemName());
                    realmSelectItemList.setUnitPrice(itemsList.getUnitPrice());
                    realmSelectItemList.setItemCategoryId(itemsList.getItemCategoryId());
                    if (itemsList.getQty() !=null){
                        Log.e("getQty1", String.valueOf(itemsList.getQty()));
                        if (itemsList.getItemQuantity()!=0){
                            realmSelectItemList.setQty(itemsList.getItemQuantity());
                        }else{
                            realmSelectItemList.setQty(itemsList.getQty());
                        }

                    }else {
                        realmSelectItemList.setQty(1l);

                    }
                    realmSelectItemList.setType("Order");
                    if (itemsList.getItemDesc()!=null){
                        realmSelectItemList.setItemDesc(itemsList.getItemDesc());
                    }else{
                        realmSelectItemList.setItemDesc("");
                    }

                    realmSelectItemList.setItemDescription(itemsList.getItemDesc());
                    realmSelectItemList.setTaxId(itemsList.getTaxId());
                    realmSelectItemList.setItemCategoryName(itemsList.getItemCategoryName());
                    realmSelectItemList.setInclusiveJSON(itemsList.getInclusiveJSON());
                    realmSelectItemList.setInputTaxId(itemsList.getInputTaxId());
                    realmSelectItemList.setOuputTaxId(itemsList.getOutputTaxId());
                    realmSelectItemList.setGstTaxAmt(itemsList.getGstTaxAmt());
                    realmSelectItemList.setItemTypeId(itemsList.getItemTypeId());
                    realmSelectItemList.setItemTypeName(itemsList.getItemTypeName());
                    realmSelectItemList.setItemTotalAmountInTax(itemsList.getItemTotalAmountInTax());
                    realmSelectItemList.setItemTotalAmountExTax(itemsList.getItemTotalAmountExTax());
                    realmSelectItemList.setGstTaxPercantage(itemsList.getGstTaxPercantage());
                    realmSelectItemList.setItemTotalAmount(itemsList.getItemTotalAmount());

                }catch (Exception e){
                    Log.e("Exception",e.toString());
                }




            }


        }

        return realmSelectItemList;
    }
    public static Realm_SplitTable generateRestaSplitItem(Realm_SelectItemList itemsList, SubRow subRow, String ItemCode) {
        Realm_SplitTable realmSelectItemList = new Realm_SplitTable();
        if (itemsList!=null){
            try {
                realmSelectItemList.setTableId(itemsList.getTableId());
                realmSelectItemList.setTableName(itemsList.getTableName());
                realmSelectItemList.setSelect(itemsList.isSelect());
                realmSelectItemList.setWaiterName(itemsList.getWaiterName());
                realmSelectItemList.setFloorId(itemsList.getFloorId());
                realmSelectItemList.setKotSelect(itemsList.isKotSelect());
                realmSelectItemList.setCustomerId(itemsList.getCustomerId());
                realmSelectItemList.setItemId(itemsList.getItemId());
                realmSelectItemList.setItemCode(itemsList.getItemCode());
                realmSelectItemList.setItemName(itemsList.getItemName());
                realmSelectItemList.setUnitPrice(itemsList.getUnitPrice());
                realmSelectItemList.setItemCategoryId(itemsList.getItemCategoryId());
                realmSelectItemList.setQty(itemsList.getQty());
                realmSelectItemList.setType(itemsList.getType());
                realmSelectItemList.setTaxId(itemsList.getTaxId());
                realmSelectItemList.setItemCategoryName(itemsList.getItemCategoryName());
                realmSelectItemList.setInclusiveJSON(itemsList.getInclusiveJSON());
                realmSelectItemList.setInputTaxId(itemsList.getInputTaxId());
                realmSelectItemList.setOuputTaxId(itemsList.getOuputTaxId());
                realmSelectItemList.setGstTaxAmt(itemsList.getGstTaxAmt());
                realmSelectItemList.setItemTypeId(itemsList.getItemTypeId());
                realmSelectItemList.setItemTypeName(itemsList.getItemTypeName());
                realmSelectItemList.setItemTotalAmountInTax(itemsList.getItemTotalAmountInTax());
                realmSelectItemList.setItemTotalAmountExTax(itemsList.getItemTotalAmountExTax());
                realmSelectItemList.setGstTaxPercantage(itemsList.getGstTaxPercantage());
                realmSelectItemList.setItemTotalAmount(itemsList.getItemTotalAmount());
            }catch (Exception e){
                Log.e("Exception",e.toString());
            }
        }

        return realmSelectItemList;
    }
    public static Realm_SplitTable generateRestaSplitOrder(SelectedItemsList itemsList, SubRow subRow, String ItemCode) {
        Realm_SplitTable realmSelectItemList = new Realm_SplitTable();
        if (itemsList!=null){
                try {
                    realmSelectItemList.setTableId(itemsList.getTableId());
                    realmSelectItemList.setTableName(itemsList.getTableName());
                    realmSelectItemList.setSelect(itemsList.isSelect());
                    realmSelectItemList.setWaiterName(itemsList.getWaiterName());
                    realmSelectItemList.setFloorId(itemsList.getFloorId());
                    realmSelectItemList.setKotSelect(true);
                    realmSelectItemList.setCustomerId(itemsList.getCustomerId());
                    realmSelectItemList.setItemId(itemsList.getItemId());
                    realmSelectItemList.setItemCode(itemsList.getItemCode());
                    realmSelectItemList.setItemName(itemsList.getItemName());
                    realmSelectItemList.setUnitPrice(itemsList.getUnitPrice());
                    realmSelectItemList.setItemCategoryId(itemsList.getItemCategoryId());
                    realmSelectItemList.setQty(itemsList.getQty());
                    realmSelectItemList.setType(itemsList.getType());
                    realmSelectItemList.setTaxId(itemsList.getTaxId());
                    realmSelectItemList.setItemCategoryName(itemsList.getItemCategoryName());
                    realmSelectItemList.setInclusiveJSON(itemsList.getInclusiveJSON());
                    realmSelectItemList.setInputTaxId(itemsList.getInputTaxId());
                    realmSelectItemList.setOuputTaxId(itemsList.getOutputTaxId());
                    realmSelectItemList.setGstTaxAmt(itemsList.getGstTaxAmt());
                    realmSelectItemList.setItemTypeId(itemsList.getItemTypeId());
                    realmSelectItemList.setItemTypeName(itemsList.getItemTypeName());
                    realmSelectItemList.setItemTotalAmountInTax(itemsList.getItemTotalAmountInTax());
                    realmSelectItemList.setItemTotalAmountExTax(itemsList.getItemTotalAmountExTax());
                    realmSelectItemList.setGstTaxPercantage(itemsList.getGstTaxPercantage());
                    realmSelectItemList.setItemTotalAmount(itemsList.getItemTotalAmount());
                }catch (Exception e){
                    Log.e("order Exception",e.toString());
                }
            }

        return realmSelectItemList;
    }
    public static Realm_Checkout_SplitTable generateRestaSplitCheckoutItem(Realm_SplitTable itemsList, SubRow subRow, String ItemCode) {
        Realm_Checkout_SplitTable realmSelectItemList = new Realm_Checkout_SplitTable();

        if (itemsList!=null){
            try {
                realmSelectItemList.setTableId(itemsList.getTableId());
                realmSelectItemList.setTableName(itemsList.getTableName());
                realmSelectItemList.setSelect(itemsList.isSelect());
                realmSelectItemList.setWaiterName(itemsList.getWaiterName());
                realmSelectItemList.setFloorId(subRow.getFloorId());
                realmSelectItemList.setKotSelect(itemsList.isKotSelect());
                realmSelectItemList.setCustomerId(itemsList.getCustomerId());
                realmSelectItemList.setItemId(itemsList.getItemId());
                realmSelectItemList.setItemCode(itemsList.getItemCode());
                realmSelectItemList.setItemName(itemsList.getItemName());
                realmSelectItemList.setUnitPrice(itemsList.getUnitPrice());
                realmSelectItemList.setItemCategoryId(itemsList.getItemCategoryId());

                if (itemsList.getQty()!=0){
                    realmSelectItemList.setQty(itemsList.getQty());
                }else{
                    realmSelectItemList.setQty(itemsList.getItemQuantity());
                }

                realmSelectItemList.setType("Order");
                realmSelectItemList.setTaxId(itemsList.getTaxId());
                realmSelectItemList.setItemCategoryName(itemsList.getItemCategoryName());
                realmSelectItemList.setInclusiveJSON(itemsList.getInclusiveJSON());
                realmSelectItemList.setInputTaxId(itemsList.getInputTaxId());
                realmSelectItemList.setOuputTaxId(itemsList.getOuputTaxId());
                realmSelectItemList.setGstTaxAmt(itemsList.getGstTaxAmt());
                realmSelectItemList.setItemTypeId(itemsList.getItemTypeId());
                realmSelectItemList.setItemTypeName(itemsList.getItemTypeName());
                realmSelectItemList.setItemTotalAmountInTax(itemsList.getItemTotalAmountInTax());
                realmSelectItemList.setItemTotalAmountExTax(itemsList.getItemTotalAmountExTax());
                realmSelectItemList.setGstTaxPercantage(itemsList.getGstTaxPercantage());
                realmSelectItemList.setItemTotalAmount(itemsList.getItemTotalAmount());

            }catch (Exception e){
                Log.e("Exception",e.toString());
            }
        }
        return realmSelectItemList;
    }

    public static Realm_Checkout_SplitTable generateRestaSplitEditCheckoutItem(Realm_SelectItemList itemsList, SubRow subRow, String ItemCode, long changeQty) {
        Realm_Checkout_SplitTable realmSelectItemList = new Realm_Checkout_SplitTable();

        if (itemsList!=null){
            try {
                realmSelectItemList.setTableId(itemsList.getTableId());
                realmSelectItemList.setTableName(itemsList.getTableName());
                realmSelectItemList.setSelect(itemsList.isSelect());
                realmSelectItemList.setWaiterName(itemsList.getWaiterName());
                realmSelectItemList.setFloorId(subRow.getFloorId());
                realmSelectItemList.setKotSelect(true);
                realmSelectItemList.setCustomerId(itemsList.getCustomerId());
                realmSelectItemList.setItemId(itemsList.getItemId());
                realmSelectItemList.setItemCode(itemsList.getItemCode());
                realmSelectItemList.setItemName(itemsList.getItemName());
                realmSelectItemList.setUnitPrice(itemsList.getUnitPrice());
                realmSelectItemList.setItemCategoryId(itemsList.getItemCategoryId());
                realmSelectItemList.setQty(changeQty);
                realmSelectItemList.setType("Order");
                realmSelectItemList.setTaxId(itemsList.getTaxId());
                realmSelectItemList.setItemCategoryName(itemsList.getItemCategoryName());
                realmSelectItemList.setInclusiveJSON(itemsList.getInclusiveJSON());
                realmSelectItemList.setInputTaxId(itemsList.getInputTaxId());
                realmSelectItemList.setOuputTaxId(itemsList.getOuputTaxId());
                realmSelectItemList.setItemTypeId(itemsList.getItemTypeId());
                realmSelectItemList.setItemTypeName(itemsList.getItemTypeName());

                double gstTaxPercantage =itemsList.getGstTaxPercantage();
                double gstTaxAmt;
                gstTaxAmt = ((realmSelectItemList.getQty() * itemsList.getUnitPrice() * gstTaxPercantage) / 100);
                realmSelectItemList.setGstTaxPercantage(gstTaxPercantage);
                DecimalFormat df2 = new DecimalFormat(".##");


                realmSelectItemList.setGstTaxAmt(Double.parseDouble(df2.format(gstTaxAmt)));
                double amountExTax = realmSelectItemList.getQty() * realmSelectItemList.getUnitPrice();
                double amountInTax = Double.valueOf(realmSelectItemList.getQty() * realmSelectItemList.getUnitPrice()+gstTaxAmt);

                realmSelectItemList.setItemTotalAmountExTax(amountExTax);
                realmSelectItemList.setItemTotalAmountInTax(amountInTax);

                Log.e("ItemTotalAmountExTax", String.valueOf(realmSelectItemList.getItemTotalAmountExTax()));
                Log.e("ItemTotalAmountInTax", String.valueOf(realmSelectItemList.getItemTotalAmountInTax()));

                realmSelectItemList.setItemTotalAmount(itemsList.getItemTotalAmount());

            }catch (Exception e){
                Log.e("Exception",e.toString());
            }
        }
        return realmSelectItemList;
    }
    public static Realm_TempOrders generateRestaKotCancelOrder(SelectedItemsList itemsList) {


        Realm_TempOrders realmSelectItemList = new Realm_TempOrders();

         if (itemsList!=null){
                try {
                    realmSelectItemList.setTableId(itemsList.getTableId());
                    realmSelectItemList.setTableName(itemsList.getTableName());
                    realmSelectItemList.setSelect(true);
                    realmSelectItemList.setWaiterName(itemsList.getWaiterName());
                    realmSelectItemList.setFloorId(itemsList.getFloorId());
                    realmSelectItemList.setKotSelect(true);
                    realmSelectItemList.setCustomerId(itemsList.getCustomerId());
                    realmSelectItemList.setItemId(itemsList.getItemId());
                    realmSelectItemList.setItemCode(itemsList.getItemCode());
                    realmSelectItemList.setItemName(itemsList.getItemName());
                    realmSelectItemList.setUnitPrice(itemsList.getUnitPrice());
                    realmSelectItemList.setItemCategoryId(itemsList.getItemCategoryId());
                    realmSelectItemList.setQty(itemsList.getItemQuantity());
                    realmSelectItemList.setType("Cancel");
                    realmSelectItemList.setTaxId(itemsList.getTaxId());
                    realmSelectItemList.setItemCategoryName(itemsList.getItemCategoryName());
                    realmSelectItemList.setInclusiveJSON(itemsList.getInclusiveJSON());
                    realmSelectItemList.setInputTaxId(itemsList.getInputTaxId());
                    realmSelectItemList.setOuputTaxId(itemsList.getOutputTaxId());
                    realmSelectItemList.setGstTaxAmt(itemsList.getGstTaxAmt());
                    realmSelectItemList.setItemTypeId(itemsList.getItemTypeId());
                    realmSelectItemList.setItemTypeName(itemsList.getItemTypeName());
                    realmSelectItemList.setItemTotalAmountInTax(itemsList.getItemTotalAmountInTax());
                    realmSelectItemList.setItemTotalAmountExTax(itemsList.getItemTotalAmountExTax());
                    realmSelectItemList.setGstTaxPercantage(itemsList.getGstTaxPercantage());
                    realmSelectItemList.setItemTotalAmount(itemsList.getItemTotalAmount());
                    realmSelectItemList.setItemDesc(itemsList.getItemDesc());
                }catch (Exception e){
                    Log.e("Exception",e.toString());
                }




            }


        return realmSelectItemList;
    }
    public static Realm_SelectItemList generateNotificationRestaSelectItem(List<SelectedItemsList> itemsListData, SubRow subRow, String ItemCode) {


        Realm_SelectItemList realmSelectItemList = new Realm_SelectItemList();

        for (in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList itemsList :itemsListData){
            if (itemsList!=null){
                try {
                    Log.e("getQty2", String.valueOf(itemsList.getQty()));
                    realmSelectItemList.setTableId(subRow.getTableId());
                    realmSelectItemList.setTableName(subRow.getTableName());

                    if (subRow.getSelected()){
                        realmSelectItemList.setSelect(subRow.getSelected());
                    }else{
                        realmSelectItemList.setSelect(true);
                    }

                    realmSelectItemList.setFloorId(subRow.getFloorId());
                    realmSelectItemList.setKotSelect(false);
                    realmSelectItemList.setWaiterName(itemsList.getWaiterName());
                    realmSelectItemList.setCustomerId(itemsList.getCustomerId());
                    realmSelectItemList.setItemId(itemsList.getItemId());
                    realmSelectItemList.setItemCode(itemsList.getItemCode());
                    realmSelectItemList.setItemName(itemsList.getItemName());
                    realmSelectItemList.setUnitPrice(itemsList.getUnitPrice());
                    realmSelectItemList.setItemCategoryId(itemsList.getItemCategoryId());
                    realmSelectItemList.setQty(itemsList.getQty());
                    realmSelectItemList.setType(itemsList.getType());
                    realmSelectItemList.setTaxId(itemsList.getTaxid());
                    realmSelectItemList.setItemCategoryName(itemsList.getItemCategoryName());
                    //realmSelectItemList.setInclusiveJSON(itemsList.getInclusiveJSON());
                    realmSelectItemList.setInputTaxId(itemsList.getInputTaxId());
                    realmSelectItemList.setOuputTaxId(itemsList.getOutputTaxId());
                    realmSelectItemList.setItemTypeId(itemsList.getItemTypeId());
                    realmSelectItemList.setItemTypeName(itemsList.getItemTypeName());
                    realmSelectItemList.setHsnCode(itemsList.getHsnCode());
                    realmSelectItemList.setItemTotalAmountInTax(itemsList.getItemTotalAmountInTax());
                    realmSelectItemList.setItemTotalAmountExTax(itemsList.getItemTotalAmountExTax());
                    realmSelectItemList.setGstTaxAmt(itemsList.getGstTaxAmt());
                    realmSelectItemList.setGstTaxPercantage(itemsList.getGstTaxPercantage());
                    realmSelectItemList.setItemTotalAmount(itemsList.getItemTotalAmount());
                    realmSelectItemList.setTaxamt(itemsList.getTaxamt());

                }catch (Exception e){
                    Log.e("Exception",e.toString());
                }




            }


        }

        return realmSelectItemList;
    }


    public static Realm_SelectItemList generateRestaServcerSelectItem(SelectedItemsList itemsList, SubRow subRow, String itemCode) {


        Realm_SelectItemList realmSelectItemList = new Realm_SelectItemList();
                try {
                    realmSelectItemList.setTableId(subRow.getTableId());
                    realmSelectItemList.setTableName(subRow.getTableName());
                    realmSelectItemList.setSelect(subRow.getSelected());
                    realmSelectItemList.setFloorId(subRow.getFloorId());
                    //realmSelectItemList.setFloorId("211");
                    realmSelectItemList.setWaiterName(itemsList.getWaiterName());
                    realmSelectItemList.setCustomerId(itemsList.getCustomerId());
                    realmSelectItemList.setKotSelect(true);
                    realmSelectItemList.setItemId(itemsList.getItemId());
                    realmSelectItemList.setItemCode(itemsList.getItemCode());
                    realmSelectItemList.setItemName(itemsList.getItemName());
                    realmSelectItemList.setItemDesc(itemsList.getItemDescription());
                    realmSelectItemList.setItemDescription(itemsList.getItemDescription());
                    realmSelectItemList.setItemCategoryId(itemsList.getItemCategoryId());
                    realmSelectItemList.setTaxId(itemsList.getTaxId());
                    realmSelectItemList.setType("Order");
                    realmSelectItemList.setQty(itemsList.getQty());
                    realmSelectItemList.setItemTotalAmountInTax(itemsList.getAmtinclusivetax());
                    realmSelectItemList.setItemTotalAmountExTax(itemsList.getAmtexclusivetax());
                    realmSelectItemList.setGstTaxAmt(itemsList.getGstTaxAmt());
                    realmSelectItemList.setGstTaxPercantage(itemsList.getGstTaxPercantage());
                    realmSelectItemList.setUnitPrice(itemsList.getUnitPrice());
                    realmSelectItemList.setItemCategoryName(itemsList.getItemCategoryName());
                    realmSelectItemList.setInclusiveJSON(itemsList.getInclusiveJSON());
                    realmSelectItemList.setInputTaxId(itemsList.getInputTaxId());
                    realmSelectItemList.setOuputTaxId(itemsList.getOutputTaxId());
                    realmSelectItemList.setItemTypeId(itemsList.getItemTypeId());
                    realmSelectItemList.setItemTypeName(itemsList.getItemTypeName());
                    realmSelectItemList.setItemTotalAmount(itemsList.getItemTotalAmount());

                }catch (Exception e){
                    Log.e("Exception",e.toString());
                }
        return realmSelectItemList;
    }
    public static RealmTemp_SelectItemList generateRestaTempServcerSelectItem(SelectedItemsList itemsList, SubRow subRow) {


        RealmTemp_SelectItemList realmSelectItemList = new RealmTemp_SelectItemList();
            if (itemsList!=null){

                try {
                    realmSelectItemList.setTableId(subRow.getTableId());
                    realmSelectItemList.setTableName(subRow.getTableName());
                    realmSelectItemList.setSelect(subRow.getSelected());
                    realmSelectItemList.setFloorId(subRow.getFloorId());
                    realmSelectItemList.setWaiterName(itemsList.getWaiterName());
                    realmSelectItemList.setCustomerId(itemsList.getCustomerId());
                    realmSelectItemList.setKotSelect(true);
                    realmSelectItemList.setItemId(itemsList.getItemId());
                    realmSelectItemList.setItemCode(itemsList.getItemCode());
                    realmSelectItemList.setItemName(itemsList.getItemName());
                    realmSelectItemList.setItemDesc(itemsList.getItemDescription());
                    realmSelectItemList.setItemDescription(itemsList.getItemDescription());
                    realmSelectItemList.setItemCategoryId(itemsList.getItemCategoryId());
                    realmSelectItemList.setTaxId(itemsList.getTaxId());
                    realmSelectItemList.setType("Order");
                    realmSelectItemList.setQty(itemsList.getQty());
                    realmSelectItemList.setItemTotalAmountInTax(itemsList.getAmtinclusivetax());
                    realmSelectItemList.setItemTotalAmountExTax(itemsList.getAmtexclusivetax());
                    realmSelectItemList.setGstTaxAmt(itemsList.getGstTaxAmt());
                    realmSelectItemList.setGstTaxPercantage(itemsList.getGstTaxPercantage());
                    realmSelectItemList.setUnitPrice(itemsList.getUnitPrice());
                    realmSelectItemList.setItemCategoryName(itemsList.getItemCategoryName());
                    realmSelectItemList.setInclusiveJSON(itemsList.getInclusiveJSON());
                    realmSelectItemList.setInputTaxId(itemsList.getInputTaxId());
                    realmSelectItemList.setOuputTaxId(itemsList.getOutputTaxId());
                    realmSelectItemList.setItemTypeId(itemsList.getItemTypeId());
                    realmSelectItemList.setItemTypeName(itemsList.getItemTypeName());
                    realmSelectItemList.setItemTotalAmount(itemsList.getItemTotalAmount());


                }catch (Exception e){
                    Log.e("Exception12",e.toString());
                }
            }
        return realmSelectItemList;
    }
    public static RealmTemp_SelectItemList generateRestaTempSelectItem(SelectedItemsList itemsList, SubRow subRow) {


        RealmTemp_SelectItemList realmSelectItemList = new RealmTemp_SelectItemList();
        if (itemsList!=null){
            Log.e("ItemName1 ", String.valueOf(itemsList.getItemQuantity()));

            try {
                realmSelectItemList.setTableId(subRow.getTableId());
                realmSelectItemList.setTableName(subRow.getTableName());
                realmSelectItemList.setSelect(subRow.getSelected());
                realmSelectItemList.setFloorId(subRow.getFloorId());
                realmSelectItemList.setKotSelect(true);
                realmSelectItemList.setTaxId(itemsList.getTaxId());
                realmSelectItemList.setType(itemsList.getType());
                realmSelectItemList.setWaiterName(itemsList.getWaiterName());
                realmSelectItemList.setItemId(itemsList.getItemId());
                realmSelectItemList.setItemCode(itemsList.getItemCode());
                realmSelectItemList.setItemName(itemsList.getItemName());
                realmSelectItemList.setItemDesc(itemsList.getItemDesc());
                realmSelectItemList.setQty(itemsList.getItemQuantity());
                realmSelectItemList.setItemTotalAmountInTax(itemsList.getItemTotalAmountInTax());
                realmSelectItemList.setItemTotalAmountExTax(itemsList.getItemTotalAmountExTax());
                realmSelectItemList.setGstTaxAmt(itemsList.getGstTaxAmt());
                realmSelectItemList.setGstTaxPercantage(itemsList.getGstTaxPercantage());
                realmSelectItemList.setUnitPrice(itemsList.getUnitPrice());
                realmSelectItemList.setItemCategoryName(itemsList.getItemCategoryName());
                realmSelectItemList.setInclusiveJSON(itemsList.getInclusiveJSON());
                realmSelectItemList.setInputTaxId(itemsList.getInputTaxId());
                realmSelectItemList.setOuputTaxId(itemsList.getOutputTaxId());
                realmSelectItemList.setItemTypeId(itemsList.getItemTypeId());
                realmSelectItemList.setItemTypeName(itemsList.getItemTypeName());
                realmSelectItemList.setItemTotalAmount(itemsList.getItemTotalAmount());
                realmSelectItemList.setItemCategoryId(itemsList.getItemCategoryId());

            }catch (Exception e){
                Log.e("Exception12",e.toString());
            }
        }
        return realmSelectItemList;
    }
    public static Realm_SelectItemList updateRestaSelectItem(SelectedItemsList itemsList) {


                Realm_SelectItemList realmSelectItemList = new Realm_SelectItemList();
                realmSelectItemList.setItemId(itemsList.getItemId());
                realmSelectItemList.setItemCode(itemsList.getItemCode());
                realmSelectItemList.setItemName(itemsList.getItemName());
                realmSelectItemList.setItemDesc(itemsList.getItemDesc());
                realmSelectItemList.setQty(itemsList.getItemQuantity());
                realmSelectItemList.setItemTotalAmountInTax(itemsList.getItemTotalAmountInTax());
                realmSelectItemList.setItemTotalAmountExTax(itemsList.getItemTotalAmountExTax());
                realmSelectItemList.setGstTaxAmt(itemsList.getGstTaxAmt());
                realmSelectItemList.setGstTaxPercantage(itemsList.getGstTaxPercantage());
                realmSelectItemList.setUnitPrice(itemsList.getUnitPrice());
                realmSelectItemList.setItemCategoryId(itemsList.getItemCategoryId());
                realmSelectItemList.setItemCategoryName(itemsList.getItemCategoryName());
                realmSelectItemList.setInclusiveJSON(itemsList.getInclusiveJSON());
                realmSelectItemList.setTaxId(itemsList.getTaxId()!=null?itemsList.getTaxId():0);
                realmSelectItemList.setInputTaxId(itemsList.getInputTaxId());
                realmSelectItemList.setOuputTaxId(itemsList.getOutputTaxId());
                realmSelectItemList.setItemTypeId(itemsList.getItemTypeId());
                realmSelectItemList.setItemTypeName(itemsList.getItemTypeName());
                realmSelectItemList.setItemTotalAmount(itemsList.getItemTotalAmount());
                realmSelectItemList.setTableId(itemsList.getTableId());
                realmSelectItemList.setTableName(itemsList.getTableName());
                realmSelectItemList.setSelect(true);
                realmSelectItemList.setKotSelect(itemsList.getKotSelect());
                realmSelectItemList.setFloorId(itemsList.getFloorId());
                realmSelectItemList.setType(itemsList.getType());
                realmSelectItemList.setWaiterName(itemsList.getWaiterName());
                realmSelectItemList.setCustomerId(itemsList.getCustomerId());
        return realmSelectItemList;
    }

    public static Realm_SelectItemList updateRestaKotSelectItem(Realm_SelectItemList itemsList, Boolean tableKotOrderDone) {


        Realm_SelectItemList realmSelectItemList = new Realm_SelectItemList();

        realmSelectItemList.setItemId(itemsList.getItemId());
        realmSelectItemList.setItemCode(itemsList.getItemCode());
        realmSelectItemList.setItemName(itemsList.getItemName());
        realmSelectItemList.setItemDesc(itemsList.getItemDesc());
        realmSelectItemList.setQty(itemsList.getItemQuantity());
        realmSelectItemList.setItemTotalAmountInTax(itemsList.getItemTotalAmountInTax());
        realmSelectItemList.setItemTotalAmountExTax(itemsList.getItemTotalAmountExTax());
        realmSelectItemList.setGstTaxAmt(itemsList.getGstTaxAmt());
        realmSelectItemList.setGstTaxPercantage(itemsList.getGstTaxPercantage());
        realmSelectItemList.setUnitPrice(itemsList.getUnitPrice());
        realmSelectItemList.setItemCategoryId(itemsList.getItemCategoryId());
        realmSelectItemList.setItemCategoryName(itemsList.getItemCategoryName());
        realmSelectItemList.setInclusiveJSON(itemsList.getInclusiveJSON());
        realmSelectItemList.setTaxId(itemsList.getTaxId());
        realmSelectItemList.setInputTaxId(itemsList.getInputTaxId());
        realmSelectItemList.setOuputTaxId(itemsList.getOuputTaxId());
        realmSelectItemList.setItemTypeId(itemsList.getItemTypeId());
        realmSelectItemList.setItemTypeName(itemsList.getItemTypeName());
        realmSelectItemList.setItemTotalAmount(itemsList.getItemTotalAmount());
        realmSelectItemList.setTableId(itemsList.getTableId());
        realmSelectItemList.setTableName(itemsList.getTableName());
        realmSelectItemList.setSelect(true);
        realmSelectItemList.setKotSelect(tableKotOrderDone);
      //  realmSelectItemList.setWaiterName(itemsList.getWaiterName());

        return realmSelectItemList;
    }
}
