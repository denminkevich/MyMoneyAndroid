package com.example.mymoney;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class GridAdapter extends BaseAdapter {

    private List<ExpensesGroup> ExpGroupData;
    private LayoutInflater layoutInflater;
    private Context context;

    public GridAdapter(Context aContext, List<ExpensesGroup> ExpGroupData) {
        this.context = aContext;
        this.ExpGroupData = ExpGroupData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return ExpGroupData.size();
    }

    @Override
    public Object getItem(int position) {
        return ExpGroupData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item, null);
            holder = new ViewHolder();
            holder.ExpView = (ImageView) convertView.findViewById(R.id.ExpImg);
            holder.ExpName = (TextView) convertView.findViewById(R.id.ExpName);
            holder.ExpCount = (TextView) convertView.findViewById(R.id.ExpCount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ExpensesGroup expGroup = this.ExpGroupData.get(position);
        holder.ExpName.setText(expGroup.getName());
        holder.ExpCount.setText("" + expGroup.getCount() + " р");
        holder.ExpView.setImageResource(expGroup.getImg());

        return convertView;
    }

    static class ViewHolder {
        ImageView ExpView;
        TextView ExpName;
        TextView ExpCount;
    }

}