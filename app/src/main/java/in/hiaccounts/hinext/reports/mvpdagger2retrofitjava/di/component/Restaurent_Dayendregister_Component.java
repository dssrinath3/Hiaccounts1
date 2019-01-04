package in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.component;


import android.content.Context;

import dagger.Component;
import in.hiaccounts.hinext.reports.fragment.restaurent.Fragment_RestaurentDayEndRegister;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.module.AdapterModule;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.module.Restaurent_Dayendregister_MvpModule;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.module.RestoActivityMvpModule;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.qualifier.ActivityContext;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.scopes.ActivityScope;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.mvp.RestuarantDayEndRegisterActivity;

//@ActivityScope
//@Component(modules = {AdapterModule.class, Restaurent_Dayendregister_MvpModule.class}, dependencies = ApplicationComponent.class)
public interface Restaurent_Dayendregister_Component {
   /* @ActivityContext
    Context getContext();
    void injectDayendregister(Fragment_RestaurentDayEndRegister fragment);*/
}
