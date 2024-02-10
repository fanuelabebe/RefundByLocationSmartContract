package com.fan.locationsmartcontract.implementaition;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.fan.locationsmartcontract.R;
import com.fan.locationsmartcontract.models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountsAutoCompleteAdapter  extends ArrayAdapter<Account> {
    List<Account> accountList;
    public List<Account> mFilteredList;
    Context context;
    private LayoutInflater inflater = null;
    int resource = 0;
    public AccountsAutoCompleteAdapter(Context context, int resource, List<Account> objects) {
        super(context, resource, objects);
        this.context = context;
        this.accountList = objects;
        this.mFilteredList = objects;
        this.resource = resource;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(resource, parent, false);
        }
        if(mFilteredList.size() > position) {
            Account account = mFilteredList.get(position);
            TextView accountNameText = convertView.findViewById(R.id.acc_name);
            accountNameText.setText(account.getName());

            TextView accountAddressText = convertView.findViewById(R.id.acc_address);
            accountAddressText.setText(account.getAddress());

        }
        return convertView;
    }

    @Override
    public Account getItem(int position) {
        return mFilteredList.get(position);
    }
    @Override
    public int getCount() {
        if(mFilteredList != null) {
            return mFilteredList.size();
        }else{
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {


                String charString = charSequence.toString().toLowerCase();
                Log.v("stuffC", "" + charString);
                if (charString.isEmpty()) {

                    mFilteredList = accountList;


                } else {

                    List<Account> filteredList = new ArrayList<>();

                    for ( Account account : accountList) {
//                        Organization organization = SelectOperations.SelectOrganizationByCode(voucher.get(codeIndex), daoSession);
//                        if(organization!= null){
//                            if (organization.getTradeName().toLowerCase().contains(charString)){
//                                filteredList.add(voucher);
//                            }
//                        }
                        if(account.getName() != null && account.getName() != null &&
                                (account.getName().toLowerCase().contains(charString) || account.getAddress().toLowerCase().contains(charString))) {
                            filteredList.add(account);
//                            Log.v("stuffCC", "" + account.getName());

                        }
                    }

                    mFilteredList = filteredList;

                }

                FilterResults filterResults = new FilterResults();

                filterResults.values = mFilteredList;

                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (List<Account>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
