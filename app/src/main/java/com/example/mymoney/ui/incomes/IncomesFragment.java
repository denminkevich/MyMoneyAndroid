package com.example.mymoney.ui.incomes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mymoney.ExpensesGroup;
import com.example.mymoney.GridAdapter;
import com.example.mymoney.GridIncAdapter;
import com.example.mymoney.IncomesGroup;
import com.example.mymoney.MainActivity;
import com.example.mymoney.R;
import com.example.mymoney.ui.home.HomeFragment;
import com.example.mymoney.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IncomesFragment extends Fragment {

    private IncomesViewModel mViewModel;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
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

        ArrayList<IncomesGroup> incGroup = ((MainActivity) getActivity()).getIncList();
        if (incGroup.size() == 0) {
            incGroup.add(new IncomesGroup("Зарплата", 0, R.drawable.ic_salery));
            incGroup.add(new IncomesGroup("Фриланс", 0, R.drawable.ic_freelance));
            incGroup.add(new IncomesGroup("Продажа", 0, R.drawable.ic_sells));
            incGroup.add(new IncomesGroup("Аренда", 0, R.drawable.ic_rents  ));
        }

        GridView myGrid = (GridView) root.findViewById(R.id.IncGrid);
        ImageView imageOfGroupInc = (ImageView) root.findViewById(R.id.imageOfGroupInc);
        TextView textOfGroupInc = (TextView) root.findViewById(R.id.textOfGroupinc);
        EditText editTextInc = (EditText) root.findViewById(R.id.incInput);
        LinearLayout floatingWindowInc = (LinearLayout) root.findViewById(R.id.floatWindowInc);
        View emptyViewInc = (View) root.findViewById(R.id.emptyViewInc);

        myGrid.setAdapter(new GridIncAdapter(mContext, incGroup));

        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = myGrid.getItemAtPosition(position);
                IncomesGroup IncGroup = (IncomesGroup) o;
                floatingWindowInc.setVisibility(View.VISIBLE);
                imageOfGroupInc.setImageResource(IncGroup.getImg());
                textOfGroupInc.setText(IncGroup.getName());
                myGrid.setVisibility(View.GONE);
            }
        });

        emptyViewInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindowInc.setVisibility(View.GONE);
                myGrid.setVisibility(View.VISIBLE);
            }
        });

        return root;
    }

}