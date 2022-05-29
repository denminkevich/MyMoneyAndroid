package com.example.mymoney;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ListAdapter extends ArrayAdapter<OperatesGroup> {

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<OperatesGroup> operList;

    public ListAdapter(Context context, int resource, ArrayList<OperatesGroup> operates) {
        super(context, resource, operates);
        this.operList = operates;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final OperatesGroup operates = operList.get(position);

        LocalDate date = operates.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String stringDate = date.format(formatter);

        if (operates.getCount() > 0) {
            viewHolder.countView.setTextColor(getContext().getResources().getColor(R.color.lightBlue));
            viewHolder.countView.setText("+" + String.valueOf(operates.getCount()) + " Br");
        } else {
            viewHolder.countView.setText(String.valueOf(operates.getCount()) + " Br");
        }

        viewHolder.nameView.setText(operates.getOperName());
        viewHolder.dateView.setText(stringDate);
        viewHolder.imageRecView.setImageResource(operates.getImg());

        return convertView;
    }

    private class ViewHolder {
        final TextView nameView, dateView, countView;
        final ImageView imageRecView;

        ViewHolder(View view){
            dateView = (TextView) view.findViewById(R.id.operDate);
            nameView = (TextView) view.findViewById(R.id.operName);
            countView = (TextView) view.findViewById(R.id.operCount);
            imageRecView = (ImageView) view.findViewById(R.id.operImg);
        }
    }
}
