package in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.component;

import android.content.Context;

import dagger.Component;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.module.AdapterModule;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.module.RestoActivityMvpModule;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.qualifier.ActivityContext;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.scopes.ActivityScope;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.mvp.RestuarantDayEndRegisterActivity;

@ActivityScope
@Component(modules = {AdapterModule.class, RestoActivityMvpModule.class}, dependencies = ApplicationComponent.class)
public interface RestoActivityComponent{

    @ActivityContext
    Context getContext();
     void injectMainActivity(RestuarantDayEndRegisterActivity mainActivity);
}
