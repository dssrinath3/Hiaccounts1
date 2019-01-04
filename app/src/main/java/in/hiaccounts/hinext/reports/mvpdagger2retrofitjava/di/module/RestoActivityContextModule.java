package in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.module;

import android.content.Context;


import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.qualifier.ActivityContext;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.scopes.ActivityScope;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.mvp.RestuarantDayEndRegisterActivity;
import dagger.Module;
import dagger.Provides;
@Module
public class RestoActivityContextModule {
    private RestuarantDayEndRegisterActivity mainActivity;

    public Context context;


    public RestoActivityContextModule(RestuarantDayEndRegisterActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public RestuarantDayEndRegisterActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }
}
