package in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.mvp;

import java.util.List;

import in.hiaccounts.hinext.reports.mvpdagger2retrofitjava.pojo.CryptoData;

public interface RestuarantActivityContract {
    interface View {
        void showData(List<CryptoData> data);

        void showError(String message);

        void showComplete();

        void showProgress();

        void hideProgress();
    }

    interface Presenter {
        void loadData();
    }
}
