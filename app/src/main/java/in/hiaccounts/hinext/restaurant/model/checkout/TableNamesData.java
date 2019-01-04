package in.hiaccounts.hinext.restaurant.model.checkout;

import java.io.Serializable;
import java.util.Map;

public class TableNamesData implements Serializable {
    private Map<String,String> tableName;

    public Map<String, String> getTableName() {
        return tableName;
    }

    public void setTableName(Map<String, String> tableName) {
        this.tableName = tableName;
    }
}
