package in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.app.RealmApplication;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.adapter.RecyclerViewAdapter;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.component.ApplicationComponent;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.component.DaggerRestoActivityComponent;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.component.RestoActivityComponent;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.module.RestoActivityContextModule;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.module.RestoActivityMvpModule;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.qualifier.ActivityContext;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.di.qualifier.ApplicationContext;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.pojo.CryptoData;

public class RestuarantDayEndRegisterActivity extends AppCompatActivity implements RestuarantActivityContract.View,RecyclerViewAdapter.ClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    RestoActivityComponent restoActivityComponent;
    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    RestuarantPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restuarant_day_end_register);
        ButterKnife.bind(this);

        ApplicationComponent applicationComponent = RealmApplication.get(this).getApplicationComponent();
        restoActivityComponent = DaggerRestoActivityComponent.builder()
                .restoActivityContextModule(new RestoActivityContextModule(this))
                .restoActivityMvpModule(new RestoActivityMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();

        restoActivityComponent.injectMainActivity(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        recyclerView.setAdapter(recyclerViewAdapter);
        progressBar = findViewById(R.id.progressBar);

        presenter.loadData();
    }

    @Override
    public void launchIntent(String name) {
        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(List<CryptoData> data) {
        recyclerViewAdapter.setData(data);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
