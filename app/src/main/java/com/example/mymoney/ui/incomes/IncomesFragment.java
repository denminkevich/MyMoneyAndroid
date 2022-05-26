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
import android.widget.Button;
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
    protected String currentIncomes = "";
    protected double allExp = 0;
    protected double allInc = 0;

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

        ArrayList<IncomesGroup> incGroup = ((MainActivity) getActivity()).getIncList();
        ArrayList<ExpensesGroup> expGroup = ((MainActivity) getActivity()).getExpList();
        TextView incomes = (TextView) root.findViewById(R.id.incomes);
        TextView smallExp = (TextView) root.findViewById(R.id.smallExpenses);
        allInc = 0;
        for (int i = 0; i < incGroup.size(); i++) {
            allInc += incGroup.get(i).getCount();
        }
        incomes.setText(Double.toString(allInc) + " р");
        allExp = 0;
        for (int i = 0; i < expGroup.size(); i++) {
            allExp += expGroup.get(i).getCount();
        }
        smallExp.setText(Double.toString(allExp) + " р");

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
                currentIncomes = "";
            }
        });

        emptyViewInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindowInc.setVisibility(View.GONE);
                myGrid.setVisibility(View.VISIBLE);
                currentIncomes = "";
            }
        });

        Button oneInc = (Button) root.findViewById(R.id.oneInc);
        Button twoInc = (Button) root.findViewById(R.id.twoInc);
        Button threeInc = (Button) root.findViewById(R.id.threeInc);
        Button fourInc = (Button) root.findViewById(R.id.fourInc);
        Button fiveInc = (Button) root.findViewById(R.id.fiveInc);
        Button sixInc = (Button) root.findViewById(R.id.sixInc);
        Button sevenInc = (Button) root.findViewById(R.id.sevenInc);
        Button eightInc = (Button) root.findViewById(R.id.eightInc);
        Button nineInc = (Button) root.findViewById(R.id.nineInc);
        Button zeroInc = (Button) root.findViewById(R.id.zeroInc);
        Button pointInc = (Button) root.findViewById(R.id.pointInc);
        ImageButton deleteInc = (ImageButton) root.findViewById(R.id.deleteInc);
        ImageButton checkInc = (ImageButton) root.findViewById(R.id.checkInc);

        oneInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIncomes += "1";
                editTextInc.setText(currentIncomes);
            }
        });

        twoInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIncomes += "2";
                editTextInc.setText(currentIncomes);
            }
        });

        threeInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIncomes += "3";
                editTextInc.setText(currentIncomes);
            }
        });

        fourInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIncomes += "4";
                editTextInc.setText(currentIncomes);
            }
        });

        fiveInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIncomes += "5";
                editTextInc.setText(currentIncomes);
            }
        });

        sixInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIncomes += "6";
                editTextInc.setText(currentIncomes);
            }
        });

        sevenInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIncomes += "7";
                editTextInc.setText(currentIncomes);
            }
        });

        eightInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIncomes += "8";
                editTextInc.setText(currentIncomes);
            }
        });

        nineInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIncomes += "9";
                editTextInc.setText(currentIncomes);
            }
        });

        zeroInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIncomes += "1";
                editTextInc.setText(currentIncomes);
            }
        });

        pointInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIncomes.contains(",")) {
                    editTextInc.setText(currentIncomes);
                } else {
                    currentIncomes += ".";
                    editTextInc.setText(currentIncomes);
                }

            }
        });

        deleteInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIncomes.length() > 0) {
                    currentIncomes = currentIncomes.replace(currentIncomes.substring(currentIncomes.length() - 1), "");
                    editTextInc.setText(currentIncomes);
                }

            }
        });

        checkInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < incGroup.size(); i++) {
                    if (textOfGroupInc.getText() == incGroup.get(i).getName()) {
                        incGroup.get(i).setExpCount(incGroup.get(i).getCount() + Double.parseDouble(currentIncomes));
                        break;
                    }
                }
                IncomesFragment nextFrag = new IncomesFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });


        return root;
    }

}