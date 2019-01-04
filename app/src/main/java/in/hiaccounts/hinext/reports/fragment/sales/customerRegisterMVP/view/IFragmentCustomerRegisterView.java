package in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.view;

import in.hiaccounts.hinext.reports.fragment.sales.customerRegisterMVP.network.CustomerRegisterList;

public interface IFragmentCustomerRegisterView {
    void showProgressBar();
    void hideProgressBar();
    void setCustomerDate(String customerDate);
    void customerData();
    void setCustomerRegisterAdapterView(CustomerRegisterList customerRegisterList);

}
