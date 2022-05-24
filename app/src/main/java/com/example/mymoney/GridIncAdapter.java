package com.example.mymoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridIncAdapter extends BaseAdapter {

    private List<IncomesGroup> IncGroupData;
    private LayoutInflater layoutInflater;
    private Context context;

    public GridIncAdapter(Context aContext, List<IncomesGroup> IncGroupData) {
        this.context = aContext;
        this.IncGroupData = IncGroupData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return IncGroupData.size();
    }

    @Override
    public Object getItem(int position) {
        return IncGroupData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_inc_item, null);
            holder = new ViewHolder();
            holder.IncView = (ImageView) convertView.findViewById(R.id.IncImg);
            holder.IncName = (TextView) convertView.findViewById(R.id.IncName);
            holder.IncCount = (TextView) convertView.findViewById(R.id.IncCount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        IncomesGroup IncGroup = this.IncGroupData.get(position);
        holder.IncName.setText(IncGroup.getName());
        holder.IncCount.setText("" + IncGroup.getCount() + " р");
        holder.IncView.setImageResource(IncGroup.getImg());

        return convertView;
    }

    static class ViewHolder {
        ImageView IncView;
        TextView IncName;
        TextView IncCount;
    }

}
