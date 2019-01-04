package in.hiaccounts.hinext.application.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.component.ApplicationComponent;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.component.DaggerApplicationComponent;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.module.ContextModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by srinath on 16/5/18.
 */

public class RealmApplication extends Application {
    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //Dagger comonent
        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);


        //RealmDb connection
        Realm.init(this);
        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder()
                        .name("realm-hyva-india.db")
                        .schemaVersion(1)
                        .build();
        Realm.setDefaultConfiguration(realmConfiguration);


    }


    public static RealmApplication get(Activity activity){
        return (RealmApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


    // Add multidex Code or other Application Class here
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
