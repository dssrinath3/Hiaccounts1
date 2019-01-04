package in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.interactor;

import java.util.List;

import in.hiaccounts.hinext.reports.fragment.restaurent.interactor.DayEndRegisterInterator;
import in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.network.CustomerRegisterList;
import in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.presenter.IFragmentCustomerRegisterPresenterImp;


public interface IFragmentCustomerRegisterInteractor {


    void callOnLoadPageData(onCustomerFinishedListner onCustomerFinishedListner);

    interface onCustomerFinishedListner{
        void onFailure();
        void onSuccess();
        void onFinished(CustomerRegisterList customerRegisterList);
    }

    void callCustomerRegisterData(IFragmentCustomerRegisterInteractor.onCustomerFinishedListner listner);
}
