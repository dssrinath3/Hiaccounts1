package in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.mvp;



import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.pojo.CryptoData;
import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.retrofit.RestoAPIInterface;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;
import javax.inject.Inject;

public class RestuarantPresenterImpl implements RestuarantActivityContract.Presenter {

    RestoAPIInterface apiInterface;
    RestuarantActivityContract.View mView;

    @Inject
    public RestuarantPresenterImpl(RestoAPIInterface apiInterface, RestuarantActivityContract.View mView) {
        this.apiInterface = apiInterface;
        this.mView = mView;
    }
    @Override
    public void loadData() {
        mView.showProgress();

      /*  apiInterface.getData("10").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CryptoData>>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError("Error occurred");
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<CryptoData> data) {
                        mView.showData(data);
                    }
                });*/


    }
}
