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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class ExpAdapter extends ArrayAdapter<ExpensesGroup> {

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<ExpensesGroup> expGroup;

    public ExpAdapter(Context context, int resource, ArrayList<ExpensesGroup> expGroup) {
        super(context, resource, expGroup);
        this.expGroup = expGroup;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    public View getView(int position, View convertView, ViewGroup parent) {
        final ExpAdapter.ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ExpAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ExpAdapter.ViewHolder) convertView.getTag();
        }

        final ExpensesGroup expStat = expGroup.get(position);

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String stringDate = date.format(formatter);

        viewHolder.countView.setText(String.valueOf(expStat.getCount()) + " Br");
        viewHolder.nameView.setText(expStat.getName());
        viewHolder.dateView.setText(stringDate);
        viewHolder.imageRecView.setImageResource(expStat.getImg());

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
