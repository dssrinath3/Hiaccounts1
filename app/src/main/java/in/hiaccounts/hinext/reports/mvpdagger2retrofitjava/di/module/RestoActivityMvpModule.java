package in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.module;

import dagger.Module;
import dagger.Provides;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.scopes.ActivityScope;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.mvp.RestuarantActivityContract;

@Module
public class RestoActivityMvpModule {
    private final RestuarantActivityContract.View mView;


    public RestoActivityMvpModule(RestuarantActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    RestuarantActivityContract.View provideView() {
        return mView;
    }
}
