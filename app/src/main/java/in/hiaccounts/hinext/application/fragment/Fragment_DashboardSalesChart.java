package in.hiaccounts.hinext.application.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.application.model.DashBoardData;
import in.hiaccounts.hinext.application.model.SalesDashBoardDataDtoList;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.SharedPreference;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Prateek on 8/29/2017.
 */

public class Fragment_DashboardSalesChart extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.chart)
    BarChart chart;
    @BindView(R.id.tvMonth)
    TextView tvMonth;
    private DashBoardData dashBoardData;

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private SharedPreference sharedPreference;
    private Activity mActivity;
    private static String TAG=Fragment_DashboardSalesChart.class.getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity=(Activity)context;


    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        View view = inflater.inflate(R.layout.fragment_dashboardsaleschart, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpView();
        return view;
    }

    private void setUpView() {
        sharedPreference=SharedPreference.getInstance(mActivity);

        String jsonDashBoard=sharedPreference.getData(Constant.DASHBOARD_KEY);
        if (jsonDashBoard!=null) {
            Gson gson = new Gson();
            dashBoardData = gson.fromJson(jsonDashBoard.toString(), DashBoardData.class);
            UtilView.showLogCat(TAG,"dahsboard is "+jsonDashBoard);
            if (dashBoardData!=null
                    && dashBoardData.getSalesDashBoardDataDtoList()!=null
                    && dashBoardData.getSalesDashBoardDataDtoList().size()>0) {
                generateData();
            }else {
                UtilView.showLogCat(TAG,"dahsboard is null");
            }


        }


    }

    private void generateData() {

        List<String>columLabel=new ArrayList<>();
        List<Double>columAmount=new ArrayList<>();

        HashMap<String,Double>salesDataHashMap=new HashMap<>();
        List<SalesDashBoardDataDtoList>salesList=dashBoardData.getSalesDashBoardDataDtoList();
        for (int i=0;i<salesList.size();i++){

            if (salesDataHashMap.containsKey(salesList.get(i).getSalesDate())){
                salesDataHashMap.put(salesList.get(i).getSalesDate(), salesDataHashMap.get(salesList.get(i).getSalesDate()) +salesList.get(i).getTotalRecievable());
            }else {
                salesDataHashMap.put(salesList.get(i).getSalesDate(),salesList.get(i).getTotalRecievable());
            }
        }

        int i = 0;
        ArrayList<BarDataSet> dataSet = new ArrayList<>();
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        TreeMap<String,Double> treeMap=new TreeMap<>(salesDataHashMap);

        for (Map.Entry<String, Double> entry : treeMap.entrySet()) {
            String date=entry.getKey();
            String dateArray[]=date.split("-");
            setMonth(dateArray[0],dateArray[1]);
            columLabel.add(dateArray[2]);
            columAmount.add(entry.getValue());
            BarEntry valueSet = new BarEntry(Float.parseFloat(String.valueOf(entry.getValue())), i); // Jan

            valueSet1.add(valueSet);
            i++;
        }




        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Sales");
        dataSet.add(barDataSet1);


        BarData data = new BarData(columLabel, dataSet);
        chart.setData(data);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.invalidate();


/*
        Axis axisX = null;
        int numSubcolumns = 1;
        int numColumns = columAmount.size();

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {


            values = new ArrayList<SubcolumnValue>();



            for (int j = 0; j < numSubcolumns; ++j) {
                if (i%2==0){
                    float amountFloat=Float.parseFloat(String.valueOf(columAmount.get(i)));
                    values.add( new SubcolumnValue(amountFloat, ChartUtils.COLOR_BLUE));
                }else {
                    float amountFloat=Float.parseFloat(String.valueOf(columAmount.get(i)));
                    values.add( new SubcolumnValue(amountFloat, ChartUtils.COLOR_GREEN));
                }

            }
            Column column = new Column(values);
            column.setHasLabels(true);
            column.setHasLabelsOnlyForSelected(true);
            axisValues.add(new AxisValue(i, columLabel.get(i).toCharArray()));

            axisX = new Axis(axisValues);
            columns.add(column);

        }


        salesData = new ColumnChartData(columns);
       // axisX.setTextSize(R.dimen.chartLable);
        if (hasAxes) {
            //Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("");
                  axisY.setName("Sales Amount (Rs.)");

            }

            

            axisX.setHasTiltedLabels(true);
            salesData.setAxisXBottom(axisX);
            salesData.setAxisYLeft(axisY);
        } else {
            salesData.setAxisXBottom(null);
            salesData.setAxisYLeft(null);
        }

        chart.setColumnChartData(salesData);
        chart.setZoomEnabled(false);*/

    }

    private void setMonth(String year, String month) {

        switch (month){
            case "01":
                tvMonth.setText("Jan "+year);
                break;
            case "02":
                tvMonth.setText("Feb "+year);
                break;
            case "03":
                tvMonth.setText("March "+year);
                break;
            case "04":
                tvMonth.setText("Apr "+year);
                break;
            case "05":
                tvMonth.setText("May "+year);
                break;
            case "06":
                tvMonth.setText("June "+year);
                break;
            case "07":
                tvMonth.setText("July "+year);
                break;
            case "08":
                tvMonth.setText("Aug "+year);
                break;
            case "09":
                tvMonth.setText("Sep "+year);
                break;
            case "10":
                tvMonth.setText("Oct "+year);
                break;
            case "11":
                tvMonth.setText("Nov "+year);
                break;
            case "12":
                tvMonth.setText("Dec "+year);
                break;

        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}

