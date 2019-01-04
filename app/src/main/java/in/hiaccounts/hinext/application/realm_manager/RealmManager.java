package in.hiaccounts.hinext.application.realm_manager;

import in.hiaccounts.model.realm_model.dao.RestaurentDao;
import io.realm.Realm;

public class RealmManager {
    private static Realm mRealm;

    public static Realm open() {
        mRealm = Realm.getDefaultInstance();
        return mRealm;
    }

    public static void close() {
        if (mRealm != null) {
            mRealm.close();
        }
    }


    public static RestaurentDao createRestaurentDao() {
        checkForOpenRealm();
        return new RestaurentDao(mRealm);
    }




    private static void checkForOpenRealm() {
        if (mRealm == null || mRealm.isClosed()) {
            throw new IllegalStateException("RealmManager: Realm is closed, call open() method first");
        }
    }
}
