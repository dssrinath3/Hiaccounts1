package in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.module;

import dagger.Module;
import dagger.Provides;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.adapter.RecyclerViewAdapter;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.scopes.ActivityScope;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.mvp.RestuarantDayEndRegisterActivity;

@Module(includes = {RestoActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getCoinList(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(RestuarantDayEndRegisterActivity mainActivity) {
        return mainActivity;
    }
}
