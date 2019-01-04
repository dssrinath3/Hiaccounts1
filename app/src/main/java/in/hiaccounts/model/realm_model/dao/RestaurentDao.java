package in.hiaccounts.model.realm_model.dao;


import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import in.hiaccounts.hinext.restaurant.model.category_item.RestuarentTableData;
import in.hiaccounts.hinext.restaurant.model.category_item.SelectedItemsList;
import in.hiaccounts.hinext.restaurant.model.checkout.RestraCheckoutItem;
import in.hiaccounts.hinext.restaurant.model.restaurant_pagedata.SubRow;
import in.hiaccounts.model.realm_model.data.RealmTemp_SelectItemList;
import in.hiaccounts.model.realm_model.data.Realm_Checkout_SplitTable;
import in.hiaccounts.model.realm_model.data.Realm_SelectItemList;
import in.hiaccounts.model.realm_model.data.Realm_SplitTable;
import in.hiaccounts.model.realm_model.data.Realm_TableData;
import in.hiaccounts.model.realm_model.data.Realm_TempOrders;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class RestaurentDao {
    private Realm realm;

    public RestaurentDao(Realm realm) {
        this.realm = realm;
    }

    public void saveRestaurentTableData(final Realm_TableData tableData, final String tableVal) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                Number currentIdNum = realm.where(Realm_TableData.class).max("tableId");
                int nextId;
                if (currentIdNum == null) {
                    nextId = 1;
                } else {
                    nextId = currentIdNum.intValue() + 1;
                }


                if (!checkIfExists(tableVal)){
                    tableData.setTableId(nextId);
                    realm.copyToRealmOrUpdate(tableData);
                }


            }
        });

    }
    public boolean checkIfExists(String tableVal){

        RealmQuery<Realm_TableData> query = realm.where(Realm_TableData.class)
                .equalTo("tableVal", tableVal);

        return query.count() != 0;
    }
    public boolean checkSplitIfExists(String itemcode, String tableid, String tablename, String floorid){
        RealmQuery<Realm_SplitTable> query =
                realm.where(Realm_SplitTable.class).equalTo("itemCode", itemcode).and().equalTo("tableId",tableid).and().equalTo("tableName",tablename).and().equalTo("floorId",floorid);

        return query.count() != 0;
    }

    public void updateRestaurentTableData(final Realm_SelectItemList tableData, final SelectedItemsList data) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Realm_SelectItemList selctData =  getItemCodeDetails(data.getItemCode(),data.getTableId(), data.getFloorId());
                if (selctData.getItemCode().contains(data.getItemCode())){
                    tableData.setId(selctData.getId());
                    realm.copyToRealmOrUpdate(tableData);
                    Log.e("qty 0", String.valueOf(selctData.getQty()));
                }


            }
        });

    }

    public void updateRestSplitTableData(final Realm_SelectItemList data, Long quantity) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_SplitTable obj = realm.where(Realm_SplitTable.class).equalTo("itemCode", data.getItemCode()).findFirst();
                if(obj == null) {
                    obj = realm.createObject(Realm_SplitTable.class, obj.getId());
                }
                data.setItemQuantity(quantity);
                double gstTaxPercantage =data.getGstTaxPercantage();
                double gstTaxAmt;
                gstTaxAmt = ((data.getItemQuantity() * data.getUnitPrice() * gstTaxPercantage) / 100);
                data.setGstTaxPercantage(gstTaxPercantage);
                DecimalFormat df2 = new DecimalFormat(".##");
                data.setGstTaxAmt(Double.parseDouble(df2.format(gstTaxAmt)));
                double amountExTax = data.getItemQuantity() * data.getUnitPrice();
                double amountInTax = Double.valueOf(data.getItemQuantity() * data.getUnitPrice()+gstTaxAmt);

                data.setItemTotalAmountExTax(amountExTax);
                data.setItemTotalAmountInTax(amountInTax);

                obj.setKotSelect(true);
                obj.setQty(data.getItemQuantity());
                obj.setItemTotalAmountInTax(data.getItemTotalAmountInTax());
                obj.setItemTotalAmountExTax(data.getItemTotalAmountExTax());
                obj.setGstTaxAmt(data.getGstTaxAmt());
                obj.setGstTaxPercantage(data.getGstTaxPercantage());
                obj.setItemTotalAmount(data.getItemTotalAmount());


            }
        });

    }
    public void updateRestSplitRemainingOrderData(final SelectedItemsList data, long qty) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Realm_SelectItemList obj = realm.where(Realm_SelectItemList.class).equalTo("itemCode", data.getItemCode()).and().equalTo("tableId", data.getTableId()).equalTo("floorId", data.getFloorId()).findFirst();
                if(obj == null) {
                    obj = realm.createObject(Realm_SelectItemList.class, obj.getId());
                }
                data.setQty(qty);
                double gstTaxPercantage =data.getGstTaxPercantage();
                double gstTaxAmt;
                gstTaxAmt = ((data.getQty() * data.getUnitPrice() * gstTaxPercantage) / 100);
                data.setGstTaxPercantage(gstTaxPercantage);
                DecimalFormat df2 = new DecimalFormat(".##");
                data.setGstTaxAmt(Double.parseDouble(df2.format(gstTaxAmt)));
                double amountExTax = data.getQty() * data.getUnitPrice();
                double amountInTax = Double.valueOf(data.getQty() * data.getUnitPrice()+gstTaxAmt);


                obj.setQty(data.getQty());
                obj.setGstTaxAmt(data.getGstTaxAmt());
                obj.setGstTaxPercantage(data.getGstTaxPercantage());
                obj.setItemTotalAmount(data.getItemTotalAmount());
                obj.setItemTotalAmountExTax(amountExTax);
                obj.setItemTotalAmountInTax(amountInTax);



            }
        });

    }
    public void updateRestSplitRemainingOrderToTempData(final SelectedItemsList data, long qty) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmTemp_SelectItemList obj = realm.where(RealmTemp_SelectItemList.class).equalTo("itemCode", data.getItemCode()).and().equalTo("tableId", data.getTableId()).equalTo("floorId", data.getFloorId()).findFirst();
                if(obj == null) {
                    obj = realm.createObject(RealmTemp_SelectItemList.class, obj.getId());
                }
                data.setQty(qty);
                double gstTaxPercantage =data.getGstTaxPercantage();
                double gstTaxAmt;
                gstTaxAmt = ((data.getQty() * data.getUnitPrice() * gstTaxPercantage) / 100);
                data.setGstTaxPercantage(gstTaxPercantage);
                DecimalFormat df2 = new DecimalFormat(".##");
                data.setGstTaxAmt(Double.parseDouble(df2.format(gstTaxAmt)));
                double amountExTax = data.getQty() * data.getUnitPrice();
                double amountInTax = Double.valueOf(data.getQty() * data.getUnitPrice()+gstTaxAmt);


                obj.setQty(data.getQty());
                obj.setItemTotalAmountInTax(data.getItemTotalAmountInTax());
                obj.setItemTotalAmountExTax(data.getItemTotalAmountExTax());
                obj.setGstTaxAmt(data.getGstTaxAmt());
                obj.setItemTotalAmount(data.getItemTotalAmount());
                data.setItemTotalAmountExTax(amountExTax);
                data.setItemTotalAmountInTax(amountInTax);

            }
        });

    }
    public void updateRestaurentTableItemDescription(final SelectedItemsList data) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Realm_SelectItemList obj = realm.where(Realm_SelectItemList.class).equalTo("itemCode", data.getItemCode()).findFirst();
                if(obj == null) {
                    obj = realm.createObject(Realm_SelectItemList.class, obj.getId());
                }
                obj.setItemDesc(data.getItemDesc());
            }
        });

    }
    public void updateRestaurentKotTableStatus(final SelectedItemsList data) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Realm_SelectItemList obj = realm.where(Realm_SelectItemList.class).equalTo("itemCode",data.getItemCode()).findFirst();
                if(obj == null) {
                    obj = realm.createObject(Realm_SelectItemList.class, obj.getId());
                }
                obj.setKotSelect(true);
            }
        });

    }
    public void updateRestaurentServerKotTableStatus(final SubRow tableData) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_SelectItemList obj = realm.where(Realm_SelectItemList.class).equalTo("tableId",tableData.getTableId()).and().equalTo("floorId",tableData.getFloorId()).findFirst();
                if(obj == null) {
                    obj = realm.createObject(Realm_SelectItemList.class, obj.getId());
                }
                obj.setKotSelect(tableData.getSelected());
            }
        });

    }


    public void updateRestaurentTableUnitPrice(final SelectedItemsList data) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Realm_SelectItemList obj = realm.where(Realm_SelectItemList.class).equalTo("unitPrice", data.getUnitPrice()).findFirst();
                if(obj == null) {
                    obj = realm.createObject(Realm_SelectItemList.class, obj.getId());
                }
                obj.setUnitPrice(data.getUnitPrice());
            }
        });

    }
    public void updateRestaurentTableQty(final SelectedItemsList data) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Realm_SelectItemList obj = realm.where(Realm_SelectItemList.class).equalTo("qty", data.getQty()).and().equalTo("type", "Cancel").findFirst();
                if(obj == null) {
                    obj = realm.createObject(Realm_SelectItemList.class, obj.getId());
                }
                obj.setQty(data.getQty());
            }
        });

    }

    public void updateRestaurentKotTableData(final SelectedItemsList tableData) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Realm_SelectItemList obj = realm.where(Realm_SelectItemList.class).equalTo("itemCode", tableData.getItemCode()).and().equalTo("tableId",tableData.getTableId()).and().equalTo("floorId",tableData.getFloorId()).findFirst();
                if(obj == null) {
                    obj = realm.createObject(Realm_SelectItemList.class, obj.getId());
                }
                obj.setKotSelect(true);

            }
        });

    }

    public void saveRestaurentItemDetails(final Realm_SelectItemList realmSelectItemList, final String itemCode, final SubRow tableVal) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Number currentIdNum = realm.where(Realm_SelectItemList.class).max("id");
                int sqId;
                if (currentIdNum == null) {
                    sqId = 1;
                } else {
                    sqId = currentIdNum.intValue() + 1;
                }

                Realm_SelectItemList selctData =  getItemCodeDetails(itemCode,tableVal.getTableId(),tableVal.getFloorId());
                if( selctData!=null && tableVal!=null && itemCode.equals(selctData.getItemCode()) && tableVal.getFloorId().equals(selctData.getFloorId()) ){
                    realmSelectItemList.setId(selctData.getId());
                    realmSelectItemList.setQty(selctData.getQty()+1);
                    realmSelectItemList.setItemDesc(selctData.getItemDesc());
                    realmSelectItemList.setSelect(true);
                    realmSelectItemList.setUnitPrice(selctData.getUnitPrice());
                    realmSelectItemList.setKotSelect(selctData.isKotSelect());
                    realm.copyToRealmOrUpdate(realmSelectItemList);
                }else{
                    realmSelectItemList.setId(sqId);
                    realm.copyToRealmOrUpdate(realmSelectItemList);
                }


            }
        });

    }

    public void saveRestaurentSplitItem(final Realm_SplitTable realmSelectItemList, final String itemCode, final SubRow tableVal) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number currentIdNum = realm.where(Realm_SplitTable.class).max("id");
                int sqId;
                if (currentIdNum == null) {
                    sqId = 1;
                } else {
                    sqId = currentIdNum.intValue() + 1;
                }
                Realm_SplitTable selctData =  getSplittItemCodeDetails(itemCode,tableVal.getTableId(),tableVal.getFloorId());
                if(selctData!=null && itemCode.equals(selctData.getItemCode()) && tableVal.getFloorId().equals(selctData.getFloorId()) ){
                    Log.e("itemname2",selctData.getItemName());
                    Log.e("itemname2qty", String.valueOf(realmSelectItemList.getQty()));
                    realmSelectItemList.setId(selctData.getId());
                    realmSelectItemList.setQty(realmSelectItemList.getQty());
                    realmSelectItemList.setItemTotalAmount(realmSelectItemList.getItemTotalAmount());
                    realmSelectItemList.setItemTotalAmountExTax(realmSelectItemList.getItemTotalAmountExTax());
                    realmSelectItemList.setItemTotalAmountInTax(realmSelectItemList.getItemTotalAmountInTax());
                    realmSelectItemList.setGstTaxAmt(realmSelectItemList.getGstTaxAmt());
                    realm.copyToRealmOrUpdate(realmSelectItemList);
                }else{
                    Log.e("itemname22","itemname22");
                    realmSelectItemList.setId(sqId);
                    realm.copyToRealmOrUpdate(realmSelectItemList);
                }

            }
        });

    }
    public void saveRestaSplitCheckoutItem(final Realm_Checkout_SplitTable realmSelectItemList, final String itemCode, final SubRow tableVal) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number currentIdNum = realm.where(Realm_Checkout_SplitTable.class).max("id");
                int sqId;
                if (currentIdNum == null) {
                    sqId = 1;
                } else {
                    sqId = currentIdNum.intValue() + 1;
                }
                Realm_Checkout_SplitTable selctData =  getSplittCheckoutItemCodeDetails(itemCode,tableVal.getTableId(),tableVal.getFloorId());

                    if(selctData!=null && itemCode.equals(selctData.getItemCode()) && tableVal.getFloorId().equals(selctData.getFloorId()) ){
                        realmSelectItemList.setId(selctData.getId());
                        realmSelectItemList.setQty(realmSelectItemList.getQty());
                        realmSelectItemList.setItemTotalAmount(realmSelectItemList.getItemTotalAmount());
                        realmSelectItemList.setItemTotalAmountExTax(realmSelectItemList.getItemTotalAmountExTax());
                        realmSelectItemList.setItemTotalAmountInTax(realmSelectItemList.getItemTotalAmountInTax());
                        realmSelectItemList.setGstTaxAmt(realmSelectItemList.getGstTaxAmt());
                        realm.copyToRealmOrUpdate(realmSelectItemList);
                    }else{
                        realmSelectItemList.setId(sqId);
                        realm.copyToRealmOrUpdate(realmSelectItemList);
                    }

            }
        });

    }

    public void saveRestaurentKotCancelOrders(final Realm_TempOrders realmSelectItemList) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number currentIdNum = realm.where(Realm_TempOrders.class).max("id");
                int sqId;
                if (currentIdNum == null) {
                    sqId = 1;
                } else {
                    sqId = currentIdNum.intValue() + 1;
                }
                    realmSelectItemList.setId(sqId);
                    realm.copyToRealmOrUpdate(realmSelectItemList);


            }
        });

    }

    public void saveRestaurentServerItemDetails(final Realm_SelectItemList realmSelectItemList, final String itemCode, final SubRow tableVal) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Number currentIdNum = realm.where(Realm_SelectItemList.class).max("id");
                int sqId;
                if (currentIdNum == null) {
                    sqId = 1;
                } else {
                    sqId = currentIdNum.intValue() + 1;
                }
                Realm_SelectItemList selctData =  getItemCodeDetails(itemCode,tableVal.getTableId(),tableVal.getFloorId());
                if (selctData!=null && tableVal!=null){
                    if(itemCode.equals(selctData.getItemCode()) && tableVal.getFloorId().equals(selctData.getFloorId()) ){
                        realmSelectItemList.setId(selctData.getId());
                        realmSelectItemList.setQty(selctData.getQty());
                        realmSelectItemList.setItemDesc(selctData.getItemDesc());
                        realmSelectItemList.setSelect(true);
                        realmSelectItemList.setKotSelect(selctData.isKotSelect());
                        realm.copyToRealmOrUpdate(realmSelectItemList);
                    }
                }else{
                    realmSelectItemList.setId(sqId);
                    realm.copyToRealmOrUpdate(realmSelectItemList);
                }
                /*Realm_SelectItemList selctData =  getItemCodeDetails(itemCode,tableVal.getTableValue(), tableVal.getFloorId());
                if (selctData!=null){
                    if(itemCode.equals(selctData.getItemCode())&& tableVal.getFloorId().equals(selctData.getFloorId()) && tableVal.getTableName().equals(selctData.getTableName())){
                        realmSelectItemList.setId(selctData.getId());
                        //realmSelectItemList.setQty(selctData.getQty());
                        realm.copyToRealmOrUpdate(realmSelectItemList);
                    }
                }else{
                    realmSelectItemList.setId(sqId);
                    realm.copyToRealmOrUpdate(realmSelectItemList);
                }*/

            }
        });

    }

    public void saveRestaurentServerDataSync(final Realm_SelectItemList realmSelectItemList, final SelectedItemsList itemsData, final SubRow tableVal) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Number currentIdNum = realm.where(Realm_SelectItemList.class).max("id");
                int sqId;
                if (currentIdNum == null) {
                    sqId = 1;
                } else {
                    sqId = currentIdNum.intValue() + 1;
                }
                Realm_SelectItemList selctData =  getItemCodeDetails(itemsData.getItemCode(),tableVal.getTableId(),tableVal.getFloorId());
                if (selctData!=null && tableVal!=null){
                    if(itemsData.getItemCode().equals(selctData.getItemCode()) && tableVal.getFloorId().equals(selctData.getFloorId()) ){
                        realmSelectItemList.setId(selctData.getId());
                        realmSelectItemList.setQty(itemsData.getQty());
                        realm.copyToRealmOrUpdate(realmSelectItemList);
                    }
                }else{
                    realmSelectItemList.setId(sqId);
                    realm.copyToRealmOrUpdate(realmSelectItemList);
                }
                /*Realm_SelectItemList selctData =  getItemCodeDetails(itemCode,tableVal.getTableValue(), tableVal.getFloorId());
                if (selctData!=null){
                    if(itemCode.equals(selctData.getItemCode())&& tableVal.getFloorId().equals(selctData.getFloorId()) && tableVal.getTableName().equals(selctData.getTableName())){
                        realmSelectItemList.setId(selctData.getId());
                        //realmSelectItemList.setQty(selctData.getQty());
                        realm.copyToRealmOrUpdate(realmSelectItemList);
                    }
                }else{
                    realmSelectItemList.setId(sqId);
                    realm.copyToRealmOrUpdate(realmSelectItemList);
                }*/

            }
        });

    }
    public List<Realm_SelectItemList> getLastInvoiceData() {
        List<Realm_SelectItemList> checkoutDataList = realm.where(Realm_SelectItemList.class).findAll();
        return checkoutDataList;

    }

    public int getLastIndexIdOfCheckoutData() {
        try {
            List<Realm_SelectItemList> location = realm.where(Realm_SelectItemList.class).findAll();
            return location.get(location.size() - 1).getId();
        } catch (Exception e) {
            return 0;
        }

    }
    public Realm_SelectItemList getCheckoutData(int lastIndexIdOfCheckoutData) {

        return realm.where(Realm_SelectItemList.class).equalTo("id",lastIndexIdOfCheckoutData).findFirst();
    }

    public void saveRestaurentTempItemDetails(final RealmTemp_SelectItemList realmSelectItemList, final String itemCode, final SubRow tableVal) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Number currentIdNum = realm.where(RealmTemp_SelectItemList.class).max("id");
                int sqId;
                if (currentIdNum == null) {
                    sqId = 1;
                } else {
                    sqId = currentIdNum.intValue() + 1;
                }
                RealmTemp_SelectItemList selctData =  getTempItemCodeDetails(itemCode,tableVal.getTableId(),tableVal.getFloorId());
                if (selctData!=null && tableVal!=null){
                    if(itemCode.equals(selctData.getItemCode()) && tableVal.getFloorId().equals(selctData.getFloorId()) ){
                        realmSelectItemList.setId(selctData.getId());
                        realm.copyToRealmOrUpdate(realmSelectItemList);
                    }
                }else{
                    realmSelectItemList.setId(sqId);
                    realm.copyToRealmOrUpdate(realmSelectItemList);
                }
              /*  RealmTemp_SelectItemList selctData =  getTempItemCodeDetails(itemCode,tableVal.getTableValue(),tableVal.getFloorId());
                if (selctData!=null){
                    if(itemCode.equals(selctData.getItemCode())){
                        realmSelectItemList.setId(selctData.getId());
                        //realmSelectItemList.setQty(selctData.getQty());
                        realm.copyToRealmOrUpdate(realmSelectItemList);
                    }
                }else{
                    realmSelectItemList.setId(sqId);
                    realm.copyToRealmOrUpdate(realmSelectItemList);
                }*/

            }
        });

    }
    public void saveRestaurentTempSyncdata(final RealmTemp_SelectItemList realmSelectItemList, final SelectedItemsList itemData, final SubRow tableVal) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Number currentIdNum = realm.where(RealmTemp_SelectItemList.class).max("id");
                int sqId;
                if (currentIdNum == null) {
                    sqId = 1;
                } else {
                    sqId = currentIdNum.intValue() + 1;
                }
                RealmTemp_SelectItemList selctData =  getTempItemCodeDetails(itemData.getItemCode(),tableVal.getTableId(),tableVal.getFloorId());
                if (selctData!=null && tableVal!=null){
                    if(itemData.getItemCode().equals(selctData.getItemCode()) && tableVal.getFloorId().equals(selctData.getFloorId()) ){
                        realmSelectItemList.setId(selctData.getId());
                        realmSelectItemList.setQty(selctData.getQty());
                        realm.copyToRealmOrUpdate(realmSelectItemList);
                    }
                }else{
                    realmSelectItemList.setId(sqId);
                    realm.copyToRealmOrUpdate(realmSelectItemList);
                }
              /*  RealmTemp_SelectItemList selctData =  getTempItemCodeDetails(itemCode,tableVal.getTableValue(),tableVal.getFloorId());
                if (selctData!=null){
                    if(itemCode.equals(selctData.getItemCode())){
                        realmSelectItemList.setId(selctData.getId());
                        //realmSelectItemList.setQty(selctData.getQty());
                        realm.copyToRealmOrUpdate(realmSelectItemList);
                    }
                }else{
                    realmSelectItemList.setId(sqId);
                    realm.copyToRealmOrUpdate(realmSelectItemList);
                }*/

            }
        });

    }
    public List<Realm_SelectItemList> getSelectedRestaurentTables() {

        return realm.where(Realm_SelectItemList.class).distinctValues("floorId").findAll();
    }
    public List<Realm_TableData> getTableData(String tableId) {

        int id = Integer.parseInt(tableId);

        return realm.where(Realm_TableData.class).equalTo("tableId",id).findAll();
    }


    public List<SelectedItemsList> getRestuarentItemList(SubRow tabledata) {

        List<SelectedItemsList> data = new ArrayList<>();

        if (tabledata!=null){
            RealmResults<Realm_SelectItemList> realmSelec= realm.where(Realm_SelectItemList.class).equalTo("floorId",tabledata.getFloorId()).and().equalTo("type","Order").findAll();

           // if (realmSelec != null ){
                Log.e("realmSelecsize", String.valueOf(realmSelec.size()));
                data.clear();
                realmSelec = realmSelec.sort("id", Sort.DESCENDING);
                for (Realm_SelectItemList realmSelectItem : realmSelec){
                    SelectedItemsList selectedItemsList = new SelectedItemsList();
                    selectedItemsList.setItemId(realmSelectItem.getItemId());
                    selectedItemsList.setItemCode(realmSelectItem.getItemCode());
                    selectedItemsList.setItemName(realmSelectItem.getItemName());
                    selectedItemsList.setItemDesc(realmSelectItem.getItemDesc());
                    // Log.e("ItemDisc", String.valueOf(realmSelectItem.getItemDesc()));
                    selectedItemsList.setItemQuantity(realmSelectItem.getQty());
                    selectedItemsList.setQty(realmSelectItem.getQty());
                    selectedItemsList.setItemTotalAmountInTax(realmSelectItem.getItemTotalAmountInTax());
                    selectedItemsList.setItemTotalAmountExTax(realmSelectItem.getItemTotalAmountExTax());
                    selectedItemsList.setGstTaxAmt(realmSelectItem.getGstTaxAmt());
                    selectedItemsList.setGstTaxPercantage(realmSelectItem.getGstTaxPercantage());
                    selectedItemsList.setUnitPrice(realmSelectItem.getUnitPrice());
                    selectedItemsList.setItemTotalAmount(realmSelectItem.getItemTotalAmount());
                    selectedItemsList.setTableId(realmSelectItem.getTableId());
                    selectedItemsList.setTableName(realmSelectItem.getTableName());
                    selectedItemsList.setFloorId(realmSelectItem.getFloorId());
                    selectedItemsList.setItemCategoryId(realmSelectItem.getItemCategoryId());
                    selectedItemsList.setItemCategoryName(realmSelectItem.getItemCategoryName());
                    selectedItemsList.setInclusiveJSON(realmSelectItem.getInclusiveJSON());
                    selectedItemsList.setTaxId(realmSelectItem.getTaxId());
                    selectedItemsList.setInputTaxId(realmSelectItem.getInputTaxId());
                    selectedItemsList.setOutputTaxId(realmSelectItem.getOuputTaxId());
                    selectedItemsList.setItemTypeId(realmSelectItem.getItemTypeId());
                    selectedItemsList.setItemTypeName(realmSelectItem.getItemTypeName());
                    selectedItemsList.setKotSelect(realmSelectItem.isKotSelect());
                    selectedItemsList.setType(realmSelectItem.getType());
                    selectedItemsList.setWaiterName(realmSelectItem.getWaiterName());
                    selectedItemsList.setCustomerId(realmSelectItem.getCustomerId());
                    selectedItemsList.setSelect(realmSelectItem.isSelect());
                    data.add(selectedItemsList);
                }


            }
       // }

        return data;
    }
    public Realm_SplitTable getRestSingleSplittItem(SelectedItemsList selectedItems) {
        Realm_SplitTable selectedItem = realm.where(Realm_SplitTable.class).equalTo("itemCode",selectedItems.getItemCode()).and().equalTo("floorId",selectedItems.getFloorId()).and().equalTo("tableId",selectedItems.getTableId()).findFirst();
        return selectedItem;
    }
    public Realm_Checkout_SplitTable getRestSingleCheckoutSplittItem(SelectedItemsList selectedItems) {
        Realm_Checkout_SplitTable selectedItem = realm.where(Realm_Checkout_SplitTable.class).equalTo("itemCode",selectedItems.getItemCode()).and().equalTo("floorId",selectedItems.getFloorId()).and().equalTo("tableId",selectedItems.getTableId()).findFirst();
        return selectedItem;
    }
    public List<SelectedItemsList> getRestSplittItemList(SubRow tabledata) {

        List<SelectedItemsList> data = new ArrayList<>();

        if (tabledata!=null){
            RealmResults<Realm_SplitTable> realmSelec= realm.where(Realm_SplitTable.class).equalTo("floorId",tabledata.getFloorId()).and().equalTo("type","Order").findAll();

            Log.e("realmSelecsize", String.valueOf(realmSelec.size()));
            data.clear();
            realmSelec = realmSelec.sort("id", Sort.DESCENDING);
            for (Realm_SplitTable realmSelectItem : realmSelec){
                SelectedItemsList selectedItemsList = new SelectedItemsList();
                selectedItemsList.setItemId(realmSelectItem.getItemId());
                selectedItemsList.setItemCode(realmSelectItem.getItemCode());
                selectedItemsList.setItemName(realmSelectItem.getItemName());
                selectedItemsList.setItemDesc(realmSelectItem.getItemDesc());
                selectedItemsList.setItemQuantity(realmSelectItem.getQty());
                selectedItemsList.setQty(realmSelectItem.getQty());
                selectedItemsList.setItemTotalAmountInTax(realmSelectItem.getItemTotalAmountInTax());
                selectedItemsList.setItemTotalAmountExTax(realmSelectItem.getItemTotalAmountExTax());
                selectedItemsList.setGstTaxAmt(realmSelectItem.getGstTaxAmt());
                selectedItemsList.setGstTaxPercantage(realmSelectItem.getGstTaxPercantage());
                selectedItemsList.setUnitPrice(realmSelectItem.getUnitPrice());
                selectedItemsList.setItemTotalAmount(realmSelectItem.getItemTotalAmount());
                selectedItemsList.setTableId(realmSelectItem.getTableId());
                selectedItemsList.setTableName(realmSelectItem.getTableName());
                selectedItemsList.setFloorId(realmSelectItem.getFloorId());
                selectedItemsList.setItemCategoryId(realmSelectItem.getItemCategoryId());
                selectedItemsList.setItemCategoryName(realmSelectItem.getItemCategoryName());
                selectedItemsList.setInclusiveJSON(realmSelectItem.getInclusiveJSON());
                selectedItemsList.setTaxId(realmSelectItem.getTaxId());
                selectedItemsList.setInputTaxId(realmSelectItem.getInputTaxId());
                selectedItemsList.setOutputTaxId(realmSelectItem.getOuputTaxId());
                selectedItemsList.setItemTypeId(realmSelectItem.getItemTypeId());
                selectedItemsList.setItemTypeName(realmSelectItem.getItemTypeName());
                selectedItemsList.setKotSelect(realmSelectItem.isKotSelect());
                selectedItemsList.setType(realmSelectItem.getType());
                selectedItemsList.setWaiterName(realmSelectItem.getWaiterName());
                selectedItemsList.setCustomerId(realmSelectItem.getCustomerId());
                data.add(selectedItemsList);
            }
        }
        return data;
    }
    public List<SelectedItemsList> getRestSplittCheckoutItemList(SubRow tabledata) {

        List<SelectedItemsList> data = new ArrayList<>();

        if (tabledata!=null){
            RealmResults<Realm_Checkout_SplitTable> realmSelec= realm.where(Realm_Checkout_SplitTable.class).equalTo("floorId",tabledata.getFloorId()).and().equalTo("type","Order").findAll();

            Log.e("realmSelecsize", String.valueOf(realmSelec.size()));
            data.clear();
            realmSelec = realmSelec.sort("id", Sort.DESCENDING);
            for (Realm_Checkout_SplitTable realmSelectItem : realmSelec){
                SelectedItemsList selectedItemsList = new SelectedItemsList();
                selectedItemsList.setItemId(realmSelectItem.getItemId());
                selectedItemsList.setItemCode(realmSelectItem.getItemCode());
                selectedItemsList.setItemName(realmSelectItem.getItemName());
                selectedItemsList.setItemDesc(realmSelectItem.getItemDesc());
                selectedItemsList.setItemQuantity(realmSelectItem.getQty());
                selectedItemsList.setQty(realmSelectItem.getQty());
                selectedItemsList.setItemTotalAmountInTax(realmSelectItem.getItemTotalAmountInTax());
                selectedItemsList.setItemTotalAmountExTax(realmSelectItem.getItemTotalAmountExTax());
                selectedItemsList.setGstTaxAmt(realmSelectItem.getGstTaxAmt());
                selectedItemsList.setGstTaxPercantage(realmSelectItem.getGstTaxPercantage());
                selectedItemsList.setUnitPrice(realmSelectItem.getUnitPrice());
                selectedItemsList.setItemTotalAmount(realmSelectItem.getItemTotalAmount());
                selectedItemsList.setTableId(realmSelectItem.getTableId());
                selectedItemsList.setTableName(realmSelectItem.getTableName());
                selectedItemsList.setFloorId(realmSelectItem.getFloorId());
                selectedItemsList.setItemCategoryId(realmSelectItem.getItemCategoryId());
                selectedItemsList.setItemCategoryName(realmSelectItem.getItemCategoryName());
                selectedItemsList.setInclusiveJSON(realmSelectItem.getInclusiveJSON());
                selectedItemsList.setTaxId(realmSelectItem.getTaxId());
                selectedItemsList.setInputTaxId(realmSelectItem.getInputTaxId());
                selectedItemsList.setOutputTaxId(realmSelectItem.getOuputTaxId());
                selectedItemsList.setItemTypeId(realmSelectItem.getItemTypeId());
                selectedItemsList.setItemTypeName(realmSelectItem.getItemTypeName());
                selectedItemsList.setKotSelect(realmSelectItem.isKotSelect());
                selectedItemsList.setType(realmSelectItem.getType());
                selectedItemsList.setWaiterName(realmSelectItem.getWaiterName());
                selectedItemsList.setCustomerId(realmSelectItem.getCustomerId());
                data.add(selectedItemsList);
            }
        }
        return data;
    }
    public List<Realm_SelectItemList> getRestuarentRealmItemList(SubRow tabledata) {

        List<Realm_SelectItemList> data = new ArrayList<>();

        RealmResults<Realm_SelectItemList> realmSelec= realm.where(Realm_SelectItemList.class).equalTo("tableId",tabledata.getTableId()).and().equalTo("floorId",tabledata.getFloorId()).and().equalTo("type","Order").findAll();


        return realmSelec;
    }
    public List<SelectedItemsList> getRestuarentAllOrderItemList(SubRow tabledata) {

        List<SelectedItemsList> data = new ArrayList<>();
        if (tabledata!=null){
            RealmResults<Realm_SelectItemList> realmSelec= realm.where(Realm_SelectItemList.class).equalTo("tableId",tabledata.getTableId()).and().equalTo("floorId",tabledata.getFloorId()).findAll();

            if (realmSelec != null){
                for (Realm_SelectItemList realmSelectItem : realmSelec){
                    SelectedItemsList selectedItemsList = new SelectedItemsList();

                    selectedItemsList.setItemId(realmSelectItem.getItemId());
                    selectedItemsList.setItemCode(realmSelectItem.getItemCode());
                    selectedItemsList.setItemName(realmSelectItem.getItemName());
                    selectedItemsList.setItemDesc(realmSelectItem.getItemDesc());
                    // Log.e("ItemDisc", String.valueOf(realmSelectItem.getItemDesc()));
                    selectedItemsList.setItemQuantity(realmSelectItem.getQty());
                    selectedItemsList.setItemTotalAmountInTax(realmSelectItem.getItemTotalAmountInTax());
                    selectedItemsList.setItemTotalAmountExTax(realmSelectItem.getItemTotalAmountExTax());
                    selectedItemsList.setGstTaxAmt(realmSelectItem.getGstTaxAmt());
                    selectedItemsList.setGstTaxPercantage(realmSelectItem.getGstTaxPercantage());
                    selectedItemsList.setUnitPrice(realmSelectItem.getUnitPrice());
                    selectedItemsList.setItemTotalAmount(realmSelectItem.getItemTotalAmount());
                    selectedItemsList.setTableId(realmSelectItem.getTableId());
                    selectedItemsList.setTableName(realmSelectItem.getTableName());
                    selectedItemsList.setFloorId(realmSelectItem.getFloorId());
                    selectedItemsList.setItemCategoryId(realmSelectItem.getItemCategoryId());
                    selectedItemsList.setItemCategoryName(realmSelectItem.getItemCategoryName());
                    selectedItemsList.setInclusiveJSON(realmSelectItem.getInclusiveJSON());
                    selectedItemsList.setTaxId(realmSelectItem.getTaxId());
                    selectedItemsList.setInputTaxId(realmSelectItem.getInputTaxId());
                    selectedItemsList.setOutputTaxId(realmSelectItem.getOuputTaxId());
                    selectedItemsList.setItemTypeId(realmSelectItem.getItemTypeId());
                    selectedItemsList.setItemTypeName(realmSelectItem.getItemTypeName());
                    selectedItemsList.setKotSelect(realmSelectItem.isKotSelect());
                    selectedItemsList.setType(realmSelectItem.getType());
                    selectedItemsList.setWaiterName(realmSelectItem.getWaiterName());
                    data.add(selectedItemsList);
                }


            }
        }

        return data;
    }

    public List<SelectedItemsList> getRestuarentTempraryOrderItemList(SubRow tabledata) {

        List<SelectedItemsList> data = new ArrayList<>();
        if (tabledata!=null){
            RealmResults<Realm_TempOrders> realmSelec= realm.where(Realm_TempOrders.class).equalTo("tableId",tabledata.getTableId()).and().equalTo("floorId",tabledata.getFloorId()).findAll();

            if (realmSelec != null){
                for (Realm_TempOrders realmSelectItem : realmSelec){
                    SelectedItemsList selectedItemsList = new SelectedItemsList();

                    selectedItemsList.setItemId(realmSelectItem.getItemId());
                    selectedItemsList.setItemCode(realmSelectItem.getItemCode());
                    selectedItemsList.setItemName(realmSelectItem.getItemName());
                    selectedItemsList.setItemDesc(realmSelectItem.getItemDesc());
                    // Log.e("ItemDisc", String.valueOf(realmSelectItem.getItemDesc()));
                    selectedItemsList.setItemQuantity(realmSelectItem.getQty());
                    selectedItemsList.setItemTotalAmountInTax(realmSelectItem.getItemTotalAmountInTax());
                    selectedItemsList.setItemTotalAmountExTax(realmSelectItem.getItemTotalAmountExTax());
                    selectedItemsList.setGstTaxAmt(realmSelectItem.getGstTaxAmt());
                    selectedItemsList.setGstTaxPercantage(realmSelectItem.getGstTaxPercantage());
                    selectedItemsList.setUnitPrice(realmSelectItem.getUnitPrice());
                    selectedItemsList.setItemTotalAmount(realmSelectItem.getItemTotalAmount());
                    selectedItemsList.setTableId(realmSelectItem.getTableId());
                    selectedItemsList.setTableName(realmSelectItem.getTableName());
                    selectedItemsList.setFloorId(realmSelectItem.getFloorId());
                    selectedItemsList.setItemCategoryId(realmSelectItem.getItemCategoryId());
                    selectedItemsList.setItemCategoryName(realmSelectItem.getItemCategoryName());
                    selectedItemsList.setInclusiveJSON(realmSelectItem.getInclusiveJSON());
                    selectedItemsList.setTaxId(realmSelectItem.getTaxId());
                    selectedItemsList.setInputTaxId(realmSelectItem.getInputTaxId());
                    selectedItemsList.setOutputTaxId(realmSelectItem.getOuputTaxId());
                    selectedItemsList.setItemTypeId(realmSelectItem.getItemTypeId());
                    selectedItemsList.setItemTypeName(realmSelectItem.getItemTypeName());
                    selectedItemsList.setKotSelect(realmSelectItem.isKotSelect());
                    selectedItemsList.setType(realmSelectItem.getType());
                    selectedItemsList.setWaiterName(realmSelectItem.getWaiterName());
                    data.add(selectedItemsList);
                }


            }
        }

        return data;
    }
    public List<SelectedItemsList> getRestuarentCancelItemList(SubRow tabledata) {

        List<SelectedItemsList> data = new ArrayList<>();
            RealmResults<Realm_SelectItemList> realmSelec= realm.where(Realm_SelectItemList.class).equalTo("tableId",tabledata.getTableId()).and().equalTo("floorId",tabledata.getFloorId()).and().equalTo("type","Cancel").findAll();

            if (realmSelec != null){
                for (Realm_SelectItemList realmSelectItem : realmSelec){
                    SelectedItemsList selectedItemsList = new SelectedItemsList();

                    selectedItemsList.setItemId(realmSelectItem.getItemId());
                    selectedItemsList.setItemCode(realmSelectItem.getItemCode());
                    selectedItemsList.setItemName(realmSelectItem.getItemName());
                    selectedItemsList.setItemDesc(realmSelectItem.getItemDesc());
                    // Log.e("ItemDisc", String.valueOf(realmSelectItem.getItemDesc()));
                    selectedItemsList.setItemQuantity(realmSelectItem.getQty());
                    selectedItemsList.setItemTotalAmountInTax(realmSelectItem.getItemTotalAmountInTax());
                    selectedItemsList.setItemTotalAmountExTax(realmSelectItem.getItemTotalAmountExTax());
                    selectedItemsList.setGstTaxAmt(realmSelectItem.getGstTaxAmt());
                    selectedItemsList.setGstTaxPercantage(realmSelectItem.getGstTaxPercantage());
                    selectedItemsList.setUnitPrice(realmSelectItem.getUnitPrice());
                    selectedItemsList.setItemTotalAmount(realmSelectItem.getItemTotalAmount());
                    selectedItemsList.setTableId(realmSelectItem.getTableId());
                    selectedItemsList.setTableName(realmSelectItem.getTableName());
                    selectedItemsList.setFloorId(realmSelectItem.getFloorId());
                    selectedItemsList.setItemCategoryId(realmSelectItem.getItemCategoryId());
                    selectedItemsList.setItemCategoryName(realmSelectItem.getItemCategoryName());
                    selectedItemsList.setInclusiveJSON(realmSelectItem.getInclusiveJSON());
                    selectedItemsList.setTaxId(realmSelectItem.getTaxId());
                    selectedItemsList.setInputTaxId(realmSelectItem.getInputTaxId());
                    selectedItemsList.setOutputTaxId(realmSelectItem.getOuputTaxId());
                    selectedItemsList.setItemTypeId(realmSelectItem.getItemTypeId());
                    selectedItemsList.setItemTypeName(realmSelectItem.getItemTypeName());
                    selectedItemsList.setKotSelect(realmSelectItem.isKotSelect());
                    selectedItemsList.setType(realmSelectItem.getType());

                    data.add(selectedItemsList);
                }


            }

        return data;
    }
    public List<SelectedItemsList> getRestuarentTempCancelOrders(SubRow tabledata) {

        List<SelectedItemsList> data = new ArrayList<>();
        RealmResults<Realm_TempOrders> realmSelec= realm.where(Realm_TempOrders.class).equalTo("tableId",tabledata.getTableId()).and().equalTo("floorId",tabledata.getFloorId()).and().equalTo("type","Cancel").findAll();

        if (realmSelec != null){
            for (Realm_TempOrders realmSelectItem : realmSelec){
                SelectedItemsList selectedItemsList = new SelectedItemsList();

                selectedItemsList.setItemId(realmSelectItem.getItemId());
                selectedItemsList.setItemCode(realmSelectItem.getItemCode());
                selectedItemsList.setItemName(realmSelectItem.getItemName());
                selectedItemsList.setItemDesc(realmSelectItem.getItemDesc());
                // Log.e("ItemDisc", String.valueOf(realmSelectItem.getItemDesc()));
                selectedItemsList.setItemQuantity(realmSelectItem.getQty());
                selectedItemsList.setItemTotalAmountInTax(realmSelectItem.getItemTotalAmountInTax());
                selectedItemsList.setItemTotalAmountExTax(realmSelectItem.getItemTotalAmountExTax());
                selectedItemsList.setGstTaxAmt(realmSelectItem.getGstTaxAmt());
                selectedItemsList.setGstTaxPercantage(realmSelectItem.getGstTaxPercantage());
                selectedItemsList.setUnitPrice(realmSelectItem.getUnitPrice());
                selectedItemsList.setItemTotalAmount(realmSelectItem.getItemTotalAmount());
                selectedItemsList.setTableId(realmSelectItem.getTableId());
                selectedItemsList.setTableName(realmSelectItem.getTableName());
                selectedItemsList.setFloorId(realmSelectItem.getFloorId());
                selectedItemsList.setItemCategoryId(realmSelectItem.getItemCategoryId());
                selectedItemsList.setItemCategoryName(realmSelectItem.getItemCategoryName());
                selectedItemsList.setInclusiveJSON(realmSelectItem.getInclusiveJSON());
                selectedItemsList.setTaxId(realmSelectItem.getTaxId());
                selectedItemsList.setInputTaxId(realmSelectItem.getInputTaxId());
                selectedItemsList.setOutputTaxId(realmSelectItem.getOuputTaxId());
                selectedItemsList.setItemTypeId(realmSelectItem.getItemTypeId());
                selectedItemsList.setItemTypeName(realmSelectItem.getItemTypeName());
                selectedItemsList.setKotSelect(realmSelectItem.isKotSelect());
                selectedItemsList.setType(realmSelectItem.getType());

                data.add(selectedItemsList);
            }


        }

        return data;
    }

    public SelectedItemsList getRestuarentServerItemList(String itemCode) {

        SelectedItemsList selectedItemsList = new SelectedItemsList();
        Realm_SelectItemList realmSelectItem= realm.where(Realm_SelectItemList.class).equalTo("itemCode",itemCode).findFirst();
       // Log.e("ItemCodeData1", String.valueOf(realmSelectItem.size()));
        if (realmSelectItem != null){
            //for (Realm_SelectItemList realmSelectItem : realmSelec){
                selectedItemsList.setItemId(realmSelectItem.getItemId());
                selectedItemsList.setItemCode(realmSelectItem.getItemCode());
                selectedItemsList.setItemName(realmSelectItem.getItemName());
                selectedItemsList.setItemDesc(realmSelectItem.getItemDesc());
                selectedItemsList.setItemQuantity(realmSelectItem.getQty());
                selectedItemsList.setItemTotalAmountInTax(realmSelectItem.getItemTotalAmountInTax());
                selectedItemsList.setItemTotalAmountExTax(realmSelectItem.getItemTotalAmountExTax());
                selectedItemsList.setGstTaxAmt(realmSelectItem.getGstTaxAmt());
                selectedItemsList.setGstTaxPercantage(realmSelectItem.getGstTaxPercantage());
                selectedItemsList.setUnitPrice(realmSelectItem.getUnitPrice());
                selectedItemsList.setItemTotalAmount(realmSelectItem.getItemTotalAmount());
                selectedItemsList.setTableId(realmSelectItem.getTableId());
                selectedItemsList.setTableName(realmSelectItem.getTableName());
                selectedItemsList.setItemCategoryId(realmSelectItem.getItemCategoryId());
                selectedItemsList.setItemCategoryName(realmSelectItem.getItemCategoryName());
                selectedItemsList.setInclusiveJSON(realmSelectItem.getInclusiveJSON());
                selectedItemsList.setTaxId(realmSelectItem.getTaxId());
                selectedItemsList.setInputTaxId(realmSelectItem.getInputTaxId());
                selectedItemsList.setOutputTaxId(realmSelectItem.getOuputTaxId());
                selectedItemsList.setItemTypeId(realmSelectItem.getItemTypeId());
                selectedItemsList.setItemTypeName(realmSelectItem.getItemTypeName());
                selectedItemsList.setKotSelect(realmSelectItem.isKotSelect());


               // data.add(selectedItemsList);
            //}

           // Log.e("ItemCodeDatasize", String.valueOf(data.size()));

        }
        return selectedItemsList;
    }


    public RealmTemp_SelectItemList getRestuarentTempItemList(SelectedItemsList data) {

        RealmTemp_SelectItemList realmSelec= realm.where(RealmTemp_SelectItemList.class).equalTo("itemCode",data.getItemCode()).and().equalTo("tableId",data.getTableId()).and().equalTo("floorId",data.getFloorId()).and().equalTo("type","Order").findFirst();

        return realmSelec;
    }
    public RealmTemp_SelectItemList getRestuarentTempCancelItemList(SelectedItemsList data) {

        RealmTemp_SelectItemList realmSelec= realm.where(RealmTemp_SelectItemList.class).equalTo("itemCode",data.getItemCode()).and().equalTo("tableId",data.getTableId()).and().equalTo("floorId",data.getFloorId()).and().equalTo("type","Cancel").findFirst();

        return realmSelec;
    }

    public Realm_SelectItemList getItemCodeDetails(String itemCode, String tableId, String floorId){
        Realm_SelectItemList  query = realm.where(Realm_SelectItemList.class).equalTo("itemCode", itemCode).and().equalTo("tableId",tableId).and().equalTo("floorId",floorId).findFirst();

        return query;
    }
    public Realm_SplitTable getSplittItemCodeDetails(String itemCode, String tableId, String floorId){
        Realm_SplitTable  query = realm.where(Realm_SplitTable.class).equalTo("itemCode", itemCode).and().equalTo("tableId",tableId).and().equalTo("floorId",floorId).findFirst();

        return query;
    }
    public Realm_Checkout_SplitTable getSplittCheckoutItemCodeDetails(String itemCode, String tableId, String floorId){
        Realm_Checkout_SplitTable  query = realm.where(Realm_Checkout_SplitTable.class).equalTo("itemCode", itemCode).and().equalTo("tableId",tableId).and().equalTo("floorId",floorId).findFirst();

        return query;
    }
    public Realm_TempOrders getTempCancelOrderItemCodeDetails(String itemCode, String tableId, String floorId){
        Realm_TempOrders  query = realm.where(Realm_TempOrders.class).equalTo("itemCode", itemCode).and().equalTo("tableId",tableId).and().equalTo("floorId",floorId).findFirst();

        return query;
    }
    public RealmTemp_SelectItemList getTempItemCodeDetails(String itemCode, String tableId, String floorId){
        RealmTemp_SelectItemList  query = realm.where(RealmTemp_SelectItemList.class).equalTo("itemCode", itemCode).and().equalTo("tableId",tableId).and().equalTo("floorId",floorId).findFirst();

        return query;
    }

    public  void  deletRealmDataAll(RestraCheckoutItem restraCheckoutItem){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    Realm_SelectItemList deleteData = realm.where(Realm_SelectItemList.class).equalTo("itemCode",restraCheckoutItem.getItemCode()).and().equalTo("tableId",restraCheckoutItem.getTableId()).and().equalTo("floorId",restraCheckoutItem.getFloorId()).findFirst();
                    deleteData.deleteFromRealm();
                }catch (Exception e){

                }

            }
        });
    }
    public  Realm_SelectItemList  getWaiterName(SubRow tableData){
        Realm_SelectItemList  query = realm.where(Realm_SelectItemList.class).equalTo("tableId",tableData.getTableId()).and().equalTo("floorId",tableData.getFloorId()).findFirst();
        return query;
    }

    public  Realm_TempOrders  getWaiterNameTemp(SubRow tableData){
        Realm_TempOrders  query = realm.where(Realm_TempOrders.class).equalTo("tableId",tableData.getTableId()).and().equalTo("floorId",tableData.getFloorId()).findFirst();
        return query;
    }
    public  Realm_SelectItemList  getTampCancelOrders(int id){
        Realm_SelectItemList  query = realm.where(Realm_SelectItemList.class).equalTo("id",id).and().equalTo("type","Cancel").findFirst();
        return query;
    }

    public  void  deletServerRealmDataAll(SubRow restraCheckoutItem){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    RealmResults<Realm_SelectItemList> deleteData = realm.where(Realm_SelectItemList.class).equalTo("tableId",restraCheckoutItem.getTableId()).and().equalTo("tableName",restraCheckoutItem.getTableName()).and().equalTo("floorId",restraCheckoutItem.getFloorId()).findAll();
                    deleteData.deleteAllFromRealm();
                }catch (Exception e){

                }

            }
        });
    }
    public  void  deletRealmDataAllClear(SelectedItemsList realm_selectItemList){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    RealmResults<Realm_SelectItemList> deleteData = realm.where(Realm_SelectItemList.class).equalTo("tableId",realm_selectItemList.getTableId()).and().equalTo("floorId",realm_selectItemList.getFloorId()).and().equalTo("kotSelect",realm_selectItemList.getKotSelect()).findAll();
                    deleteData.deleteAllFromRealm();
                }catch (Exception e){

                }

            }
        });
    }
    public  void  deletRealmTempDataAll(RestraCheckoutItem restraCheckoutItem){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    RealmTemp_SelectItemList deleteData = realm.where(RealmTemp_SelectItemList.class).equalTo("itemCode",restraCheckoutItem.getItemCode()).and().equalTo("tableId",restraCheckoutItem.getTableId()).and().equalTo("floorId",restraCheckoutItem.getFloorId()).findFirst();
                    deleteData.deleteFromRealm();
                }catch (Exception e){

                }

            }
        });
    }
    public  void  deletServerRealmTempDataAll(SubRow restraCheckoutItem){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    RealmResults<RealmTemp_SelectItemList> deleteData = realm.where(RealmTemp_SelectItemList.class).equalTo("tableId",restraCheckoutItem.getTableId()).and().equalTo("tableName",restraCheckoutItem.getTableName()).and().equalTo("floorId",restraCheckoutItem.getFloorId()).findAll();
                    deleteData.deleteAllFromRealm();
                }catch (Exception e){

                }

            }
        });
    }

    public  void  deletServerRealmTempCancelOrder(SubRow restraCheckoutItem){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    RealmResults<Realm_TempOrders> deleteData = realm.where(Realm_TempOrders.class).equalTo("tableId",restraCheckoutItem.getTableId()).and().equalTo("tableName",restraCheckoutItem.getTableName()).and().equalTo("floorId",restraCheckoutItem.getFloorId()).findAll();
                    deleteData.deleteAllFromRealm();
                }catch (Exception e){

                }

            }
        });
    }
    public  void  deletRealmTempDataAllClear(String tableId, String itemCode){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    RealmResults<RealmTemp_SelectItemList> deleteData = realm.where(RealmTemp_SelectItemList.class).equalTo("tableId",tableId).findAll();
                    deleteData.deleteAllFromRealm();
                }catch (Exception e){

                }

            }
        });
    }
    public  void  deletRealmDataAllTable(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Realm_SelectItemList> deleteData = realm.where(Realm_SelectItemList.class).findAll();

                deleteData.deleteAllFromRealm();
            }
        });
    }
    public  void  deletRealmSplitTableAllData(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Realm_SplitTable> deleteData = realm.where(Realm_SplitTable.class).findAll();

                deleteData.deleteAllFromRealm();
            }
        });
    }
    public  void  deletRealmSplitCheckoutTableAllData(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Realm_Checkout_SplitTable> deleteData = realm.where(Realm_Checkout_SplitTable.class).findAll();

                deleteData.deleteAllFromRealm();
            }
        });
    }

    public  void  deletRealmTempOrderDataAllTable(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Realm_TempOrders> deleteData = realm.where(Realm_TempOrders.class).findAll();

                deleteData.deleteAllFromRealm();
            }
        });
    }

    public  List<Realm_SelectItemList>  getSelectItemData(){
        RealmResults<Realm_SelectItemList> selectItemLists = realm.where(Realm_SelectItemList.class).findAll();

        return selectItemLists;
    }
    public  List<RealmTemp_SelectItemList>  getSelectTempItemData(){
        RealmResults<RealmTemp_SelectItemList> selectItemLists = realm.where(RealmTemp_SelectItemList.class).findAll();

        return selectItemLists;
    }

    public  void  deletRealmDataOfItem(final String itemcode){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
             Realm_SelectItemList deleteData = realm.where(Realm_SelectItemList.class).equalTo("itemCode",itemcode).findFirst();

                deleteData.deleteFromRealm();
            }
        });


    }
    public  void  deletTempCancelOrderRealmDataOfItem(final int id){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Realm_TempOrders deleteData = realm.where(Realm_TempOrders.class).equalTo("id",id).findFirst();

                deleteData.deleteFromRealm();
            }
        });


    }
    public  void  deletRealmDataTempOfItem(final String itemcode){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmTemp_SelectItemList deleteData = realm.where(RealmTemp_SelectItemList.class).equalTo("itemCode",itemcode).findFirst();

                deleteData.deleteFromRealm();
            }
        });


    }
    public  void  deletRealmSplitOrders(final Realm_SplitTable splitTable){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Realm_SplitTable deleteData = realm.where(Realm_SplitTable.class).equalTo("itemCode",splitTable.getItemCode()).findFirst();
                deleteData.deleteFromRealm();
            }
        });


    }
    public  void  deletRealmCheckoutSplitOrders(final Realm_Checkout_SplitTable splitTable){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Realm_Checkout_SplitTable deleteData = realm.where(Realm_Checkout_SplitTable.class).equalTo("itemCode",splitTable.getItemCode()).findFirst();
                deleteData.deleteFromRealm();
            }
        });


    }
    public  void  deletRealmSplitOrdersforKot(final SelectedItemsList splitTable){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Realm_SplitTable deleteData = realm.where(Realm_SplitTable.class).equalTo("itemCode",splitTable.getItemCode()).findFirst();
                deleteData.deleteFromRealm();
            }
        });
    }
    public  void  deletRealmCheckoutSplitOrdersforkot(final RestraCheckoutItem splitTable){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Realm_Checkout_SplitTable deleteData = realm.where(Realm_Checkout_SplitTable.class).equalTo("itemCode",splitTable.getItemCode()).findFirst();
                deleteData.deleteFromRealm();
            }
        });


    }

    public void updateRestaurentKotTableOrderStatus(final int id) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_SelectItemList obj = realm.where(Realm_SelectItemList.class).equalTo("id", id).findFirst();
                if(obj == null) {
                    obj = realm.createObject(Realm_SelectItemList.class, obj.getId());
                }
                obj.setType("Cancel");

            }
        });

    }
    public void updateRestaurentKotChangeTableOrder(final SelectedItemsList tableData, RestuarentTableData restuarentTableToDataList) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_SelectItemList obj = realm.where(Realm_SelectItemList.class).equalTo("itemCode", tableData.getItemCode()).and().equalTo("tableId",tableData.getTableId()).and().equalTo("tableName",tableData.getTableName()).and().equalTo("floorId",tableData.getFloorId()).findFirst();
                if(obj == null) {
                    obj = realm.createObject(Realm_SelectItemList.class, obj.getId());
                }
                if (restuarentTableToDataList!=null){
                    String floorId = restuarentTableToDataList.getFloorId()+restuarentTableToDataList.getTableid();
                    obj.setTableId(String.valueOf(restuarentTableToDataList.getTableid()));
                    obj.setTableName(restuarentTableToDataList.getTableName());
                    obj.setFloorId(floorId);
                }


            }
        });

    }
    public void updateRestaurentKotTempChangeTableOrder(final SelectedItemsList tableData, RestuarentTableData restuarentTableToDataList) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                RealmTemp_SelectItemList obj = realm.where(RealmTemp_SelectItemList.class).equalTo("itemCode", tableData.getItemCode()).and().equalTo("tableId",tableData.getTableId()).and().equalTo("tableName",tableData.getTableName()).and().equalTo("floorId",tableData.getFloorId()).findFirst();

                if(obj == null) {
                    obj = realm.createObject(RealmTemp_SelectItemList.class, obj.getId());
                }
                if (restuarentTableToDataList!=null){
                    String floorId = restuarentTableToDataList.getFloorId()+restuarentTableToDataList.getTableid();
                    obj.setTableId(String.valueOf(restuarentTableToDataList.getTableid()));
                    obj.setTableName(restuarentTableToDataList.getTableName());
                    obj.setFloorId(floorId);
                }



            }
        });

    }

    public void updateRestaurentDiscountAmt(final RestraCheckoutItem tableData) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.e("TableId..1", tableData.getItemCode());
                Log.e("TableId..2", tableData.getFloorId());
                Log.e("TableId..3", String.valueOf(tableData.getGstItemTax()));
                Log.e("TableId..4", String.valueOf(tableData.getAmtinclusivetax()));
                Log.e("TableId..5", String.valueOf(tableData.getAmtexclusivetax()));

                Realm_SelectItemList obj = realm.where(Realm_SelectItemList.class)
                        .equalTo("itemCode", tableData.getItemCode())
                        .and().equalTo("floorId",tableData.getFloorId())
                       .findFirst();
                    if(obj == null) {
                        obj = realm.createObject(Realm_SelectItemList.class, obj.getItemCode());
                    }


                    obj.setFloorId(tableData.getFloorId());
                    obj.setGstTaxAmt(tableData.getGstItemTax());
                    obj.setItemTotalAmountInTax(tableData.getAmtinclusivetax());
                    obj.setItemTotalAmountExTax(tableData.getAmtexclusivetax());
                    obj.setItemTotalAmount(tableData.getAmtinclusivetax());



            }
        });

    }

    public void updateRestaurentTempDiscountAmt(final RestraCheckoutItem tableData) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                RealmTemp_SelectItemList obj = realm.where(RealmTemp_SelectItemList.class)
                        .equalTo("itemCode", tableData.getItemCode())
                        .and().equalTo("floorId",tableData.getFloorId())
                      .findFirst();
                if(obj == null) {
                    obj = realm.createObject(RealmTemp_SelectItemList.class, obj.getItemCode());
                }


                obj.setFloorId(tableData.getFloorId());
                obj.setGstTaxAmt(tableData.getGstItemTax());
                obj.setItemTotalAmountInTax(tableData.getAmtinclusivetax());
                obj.setItemTotalAmountExTax(tableData.getAmtexclusivetax());
                obj.setItemTotalAmount(tableData.getAmtinclusivetax());

            }
        });

    }
    public void updateRestaurentKotTempTableOrderStatus(final int id) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                RealmTemp_SelectItemList obj = realm.where(RealmTemp_SelectItemList.class).equalTo("id", id).findFirst();
                if(obj == null) {
                    obj = realm.createObject(RealmTemp_SelectItemList.class, obj.getId());
                }
                obj.setType("Cancel");
                obj.setKotSelect(true);

            }
        });

    }

}
