package in.hiaccounts.hinext.crm.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.hiaccounts.R;
import in.hiaccounts.hinext.crm.activity.Activity_AddEvent;
import in.hiaccounts.hinext.crm.adapter.EventAdapter;
import in.hiaccounts.hinext.crm.model.event.SelectEventData;
import in.hiaccounts.task.AsyncTaskCompleteListener;
import in.hiaccounts.task.GetDataTask;
import in.hiaccounts.utility.Constant;
import in.hiaccounts.utility.ServiceHandler;
import in.hiaccounts.utility.UtilView;

/**
 * Created by Srinath on 20/12/2017.
 */
public class Fragment_CrmEvent extends Fragment implements TextWatcher {
    public static String TAG = Fragment_CrmEvent.class.getSimpleName();
    @BindView(R.id.progress_bar)
    ProgressView progressBar;
    @BindView(R.id.lvEvent)
    ListView lvEvent;
    @BindView(R.id.edSearchEvent)
    EditText edSearchEvent;
    @BindView(R.id.llSearchEvent)
    LinearLayout llSearchEvent;
    @BindView(R.id.ll)
    RelativeLayout ll;
    @BindView(R.id.ll_searchEvent)
    LinearLayout ll_searchEvent;
    Unbinder unbinder;


    private ServiceHandler serviceHandler;
    private Activity activity;
    private Boolean isInternetPresent = false;
    private String eventSearch = "", serverUrl;
    List<SelectEventData> selectEventDataList;

    public static Fragment_CrmEvent newInstance() {
        Fragment_CrmEvent fragment = new Fragment_CrmEvent();
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crm_event, container, false);
        unbinder = ButterKnife.bind(this, view);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        setHasOptionsMenu(true);
        serviceHandler = new ServiceHandler(activity);
        serverUrl = UtilView.getUrl(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.crm_adapter_eventview, null, false);
        TextView tv_EventName = header.findViewById(R.id.tv_EventName);
        TextView tv_EventDate = header.findViewById(R.id.tv_EventDate);
        TextView tv_AssignedTo = header.findViewById(R.id.tv_AssignedTo);
        TextView tv_Priority = header.findViewById(R.id.tv_Priority);
        TextView tv_EventEdit = header.findViewById(R.id.tv_Edit);
        tv_EventName.setText("EventName");
        tv_EventDate.setText("Date");
        tv_AssignedTo.setText("AssignedTo");
        tv_Priority.setText("Priority");
        tv_EventEdit.setText("Edit");
        UtilView.setTextAppearanceSmall(activity, tv_EventName);
        UtilView.setTextAppearanceSmall(activity, tv_EventDate);
        UtilView.setTextAppearanceSmall(activity, tv_AssignedTo);
        UtilView.setTextAppearanceSmall(activity, tv_Priority);
        UtilView.setTextAppearanceSmall(activity, tv_EventEdit);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_EventName);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_EventDate);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_AssignedTo);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_Priority);
        UtilView.setTextColor(getResources().getColor(R.color.colorBlack), tv_EventEdit);
        if (lvEvent != null)
            lvEvent.addHeaderView(header);
        edSearchEvent.addTextChangedListener(this);

        getCrmEventFromServer("");

        edSearchEvent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                UtilView.hideSoftKeyboard(activity, edSearchEvent);
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = true;
                    eventSearch = edSearchEvent.getText().toString().trim();
                    getCrmEventFromServer(eventSearch);
                }
                return handled;
            }
        });
    }

    private void getCrmEventFromServer(String search) {
        String url = "";
        url =serverUrl+ "/crm//" + Constant.FUNTION_GETEVENT+ "?eventSearchText=" + search.replace(" ", "%20");


        if (serverUrl!=null) {
            isInternetPresent = serviceHandler.isConnectingToInternet();
            if (isInternetPresent) {
                UtilView.showProgessBar(activity, progressBar);
                GetDataTask task = new GetDataTask(activity, new AsyncTaskCompleteListener<String>() {
                    @Override
                    public void onTaskComplete(String result) {
                        UtilView.hideProgessBar(activity, progressBar);
                        if (result != null) {
                            try {
                                selectEventDataList = new ArrayList<SelectEventData>();
                                JSONArray resultJsonArray = new JSONArray(result.toString());
                                for (int i = 0; i < resultJsonArray.length(); i++) {
                                    JSONObject asJson = resultJsonArray.getJSONObject(i);
                                    Gson gson = new Gson();
                                    SelectEventData data = gson.fromJson(asJson.toString(), SelectEventData.class);
                                    selectEventDataList.add(data);
                                }
                                if (selectEventDataList!=null && selectEventDataList.size()>0){
                                    EventAdapter adapter = new EventAdapter(activity,selectEventDataList);
                                    lvEvent.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                                else {
                                    selectEventDataList.clear();
                                    EventAdapter adapter = new EventAdapter(activity, selectEventDataList);
                                    lvEvent.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    UtilView.showErrorDialog(getResources().getString(R.string.event_notavailbale), activity);

                                }
                            }catch (Exception e){
                                UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                            }
                        } else {
                            UtilView.showErrorDialog(getResources().getString(R.string.error_null), activity);
                        }
                    }
                }, false);
                task.execute(url, "");
            }
            if (!isInternetPresent) {
                UtilView.showErrorDialog(getResources().getString(R.string.intertnet_status), activity);
            }
        }else {
            UtilView.gotToLogin(activity);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick({R.id.llSearchEvent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llSearchEvent:
                UtilView.hideSoftKeyboard(activity, edSearchEvent);
                eventSearch = edSearchEvent.getText().toString().trim();
                getCrmEventFromServer(eventSearch);
                break;

        }
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.crm_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.addMenu:
                Intent intent = new Intent(activity, Activity_AddEvent.class);
                intent.putExtra("callingFor", Constant.CALL_ADDEVENT);
                startActivityForResult(intent, Constant.RESQUSTCODE_ADDEVENT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().equals("")) {
            UtilView.hideSoftKeyboard(activity, edSearchEvent);
            eventSearch = "";
            getCrmEventFromServer(eventSearch);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_ADDEVENT) {
            getCrmEventFromServer("");
        }

        if (resultCode == Activity.RESULT_OK && requestCode == Constant.RESQUSTCODE_EDITEVENT) {
            getCrmEventFromServer("");
        }

    }
}
