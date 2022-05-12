package com.example.mymoney.ui.incomes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mymoney.R;
import com.example.mymoney.ui.home.HomeFragment;
import com.example.mymoney.ui.home.HomeViewModel;

import java.util.Calendar;
import java.util.List;

public class IncomesFragment extends Fragment {

    private IncomesViewModel mViewModel;

    public static IncomesFragment newInstance() {
        return new IncomesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Calendar c = Calendar.getInstance();
        String[]monthName = {"Январь","Февраль","Март", "Апрель", "Май", "Июнь", "Июль",
                "Август", "Сентябрь", "Октябрь", "Ноябрь",
                "Декабрь"};
        String month = monthName[c.get(Calendar.MONTH)];
        int year = c.get(Calendar.YEAR);

        View root = inflater.inflate(R.layout.incomes_fragment, container, false);
        TextView monthText = root.findViewById(R.id.monthInc);
        TextView yearText = root.findViewById(R.id.yearInc);
        monthText.setText(month);
        yearText.setText(Integer.toString(year));

        ImageButton changeBtn = (ImageButton) root.findViewById(R.id.changeBtnInc);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment nextFrag = new HomeFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
                List fragments = getActivity().getSupportFragmentManager().getFragments();
                Fragment mCurrentFragment = (Fragment) fragments.get(fragments.size() - 1);
            }
        });

        ImageView imageOfGroupInc = (ImageView) root.findViewById(R.id.imageOfGroupInc);
        TextView textOfGroupInc = (TextView) root.findViewById(R.id.textOfGroupinc);
        EditText editTextInc = (EditText) root.findViewById(R.id.incInput);
        LinearLayout floatingWindowInc = (LinearLayout) root.findViewById(R.id.floatWindowInc);
        ImageButton salaryBtn = (ImageButton) root.findViewById(R.id.salaryBtn);
        ImageButton freelanceBtn = (ImageButton) root.findViewById(R.id.freelanceBtn);
        ImageButton sellsBtn = (ImageButton) root.findViewById(R.id.sellsBtn);
        ImageButton rentsBtn = (ImageButton) root.findViewById(R.id.rentsBtn);
        View emptyViewInc = (View) root.findViewById(R.id.emptyViewInc);


        salaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindowInc.setVisibility(View.VISIBLE);
                imageOfGroupInc.setImageResource(R.drawable.ic_salery);
                textOfGroupInc.setText(R.string.Salery);
            }
        });

        freelanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindowInc.setVisibility(View.VISIBLE);
                imageOfGroupInc.setImageResource(R.drawable.ic_freelance);
                textOfGroupInc.setText(R.string.Freelance);
            }
        });

        sellsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindowInc.setVisibility(View.VISIBLE);
                imageOfGroupInc.setImageResource(R.drawable.ic_sells);
                textOfGroupInc.setText(R.string.Sells);
            }
        });

        rentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindowInc.setVisibility(View.VISIBLE);
                imageOfGroupInc.setImageResource(R.drawable.ic_rents);
                textOfGroupInc.setText(R.string.Rents);
            }
        });

        emptyViewInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindowInc.setVisibility(View.GONE);
            }
        });


        return root;
    }

}