package in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.module;

import android.content.Context;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.qualifier.ApplicationContext;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;


@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
