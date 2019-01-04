package in.hiaccounts.hinext.controlpanel.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import in.hiaccounts.R;
import in.hiaccounts.hinext.controlpanel.model.configurator_bank.employee.EmployeeList;

/**
 * Created by administrator on 20/12/17.
 */

public class SelectEmployeeListAdapter extends ArrayAdapter<EmployeeList> {
    private LayoutInflater layoutInflater;
    private Activity activity;
    List<EmployeeList> empDataList;

    public SelectEmployeeListAdapter(Activity activity,List<EmployeeList> empDataList) {
        super(activity, 0,empDataList);
        this.activity = activity;
        this.empDataList = empDataList;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // holder pattern
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.adapter_employee_list, null);

            holder.setTvName((TextView) convertView.findViewById(R.id.tvName));
            holder.setTvNumber((TextView) convertView.findViewById(R.id.tvNumber));
            holder.setTvAddress((TextView) convertView.findViewById(R.id.tvAddress));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }


        holder.getTvName().setVisibility(View.GONE);
        holder.getTvAddress().setVisibility(View.GONE);
        holder.getTvNumber().setVisibility(View.GONE);

            final EmployeeList employeeData = getItem(position);

            if (employeeData != null) {
                if (employeeData.getEmployeeName() != null)
                    holder.getTvName().setText(employeeData.getEmployeeName());
                if (employeeData.getEmployeePhone() != null)
                    holder.getTvNumber().setText(employeeData.getEmployeePhone());
                if (employeeData.getEmployeeAddr() != null)
                    holder.getTvAddress().setText(employeeData.getEmployeeAddr());


                holder.getTvName().setVisibility(View.VISIBLE);
                holder.getTvAddress().setVisibility(View.VISIBLE);
                holder.getTvNumber().setVisibility(View.VISIBLE);

            }



        return convertView;
    }



    public class Holder {


        TextView tvName;
        TextView tvNumber;
        TextView tvAddress;

        public TextView getTvName() {
            return tvName;
        }

        public void setTvName(TextView tvName) {
            this.tvName = tvName;
        }

        public TextView getTvNumber() {
            return tvNumber;
        }

        public void setTvNumber(TextView tvNumber) {
            this.tvNumber = tvNumber;
        }

        public TextView getTvAddress() {
            return tvAddress;
        }

        public void setTvAddress(TextView tvAddress) {
            this.tvAddress = tvAddress;
        }
    }

}
