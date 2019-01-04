package in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.component;


import android.content.Context;

import dagger.Component;
import in.hiaccounts.hinext.application.app.RealmApplication;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.module.ContextModule;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.module.RetrofitModule;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.qualifier.ApplicationContext;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.scopes.ApplicationScope;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.retrofit.RestoAPIInterface;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    RestoAPIInterface getApiInterface();
    @ApplicationContext
    Context getContext();
    void injectApplication(RealmApplication myApplication);
}