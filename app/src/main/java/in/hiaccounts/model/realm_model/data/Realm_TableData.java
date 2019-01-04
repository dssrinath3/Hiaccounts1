package in.hiaccounts.model.realm_model.data;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Realm_TableData extends RealmObject {
    @PrimaryKey
    private int tableId;

    private RealmList<Realm_RestraPosTableOrderCartItem> restraPosTableList;
    private String tableName;
    private String tableVal;

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public RealmList<Realm_RestraPosTableOrderCartItem> getRestraPosTableList() {
        return restraPosTableList;
    }

    public void setRestraPosTableList(RealmList<Realm_RestraPosTableOrderCartItem> restraPosTableList) {
        this.restraPosTableList = restraPosTableList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableVal() {
        return tableVal;
    }

    public void setTableVal(String tableVal) {
        this.tableVal = tableVal;
    }
}
