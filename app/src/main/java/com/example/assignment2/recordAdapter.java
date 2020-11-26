package com.example.assignment2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class recordAdapter extends BaseAdapter {

    Activity mActivity;
    myRecords _records;

    public recordAdapter(Activity mActivity, myRecords _records) {
        this.mActivity = mActivity;
        this._records = _records;
    }

    @Override
    public int getCount() {
        return _records.getMyRecords().size();
    }

    @Override
    public Expenses getItem(int position) {
        return _records.getMyRecords().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View recordTile;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        recordTile = inflater.inflate(R.layout.record_tile,parent,false);

        TextView date = recordTile.findViewById(R.id.dateText);
        TextView amountVal = recordTile.findViewById(R.id.amountValue);
        TextView categoryVal = recordTile.findViewById(R.id.categoryValue);

        Expenses e = this.getItem(position);

        date.setText(e.getExpenseDate());
        amountVal.setText(e.getAmount().toString());
        categoryVal.setText(e.getCategory());

        return recordTile;

    }
}
