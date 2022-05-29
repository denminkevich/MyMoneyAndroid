package com.example.mymoney.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mymoney.GridAdapter;
import com.example.mymoney.ExpensesGroup;
import com.example.mymoney.IncomesGroup;
import com.example.mymoney.MainActivity;
import com.example.mymoney.OperatesGroup;
import com.example.mymoney.R;
import com.example.mymoney.ui.incomes.IncomesFragment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private Context mContext;
    public String currentExp = "";
    protected double allExp = 0;
    protected double allInc = 0;
    public ExpensesGroup curExp;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        String[]monthName={"Январь","Февраль","Март", "Апрель", "Май", "Июнь", "Июль",
                "Август", "Сентябрь", "Октябрь", "Ноябрь",
                "Декабрь"};
        String month = monthName[c.get(Calendar.MONTH)];
        int year = c.get(Calendar.YEAR);

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        TextView monthText = root.findViewById(R.id.month);
        TextView yearText = root.findViewById(R.id.year);
        monthText.setText(month);
        yearText.setText(Integer.toString(year));

        ArrayList<OperatesGroup> operGroup = ((MainActivity) getActivity()).getOperList();
        ArrayList<IncomesGroup> incGroup = ((MainActivity) getActivity()).getIncList();
        ArrayList<ExpensesGroup> expGroup = ((MainActivity) getActivity()).getExpList();
        TextView expenses = (TextView) root.findViewById(R.id.expenses);
        TextView smallInc = (TextView) root.findViewById(R.id.smallIncomes);
        allExp = 0;
        for (int i = 0; i < expGroup.size(); i++) {
            allExp += expGroup.get(i).getCount();
        }
        expenses.setText(Double.toString(allExp) + " р");
        allInc = 0;
        for (int i = 0; i < incGroup.size(); i++) {
            allInc += incGroup.get(i).getCount();
        }
        smallInc.setText(Double.toString(allInc) + " р");


        ImageButton changeBtn = (ImageButton) root.findViewById(R.id.changeBtn);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IncomesFragment nextFrag = new IncomesFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }

        });

        if (expGroup.size() == 0) {
            expGroup.add(new ExpensesGroup("Здоровье", 0, R.drawable.ic_medicon));
            expGroup.add(new ExpensesGroup("Покупки", 0, R.drawable.ic_goods));
            expGroup.add(new ExpensesGroup("Рестораны", 0, R.drawable.ic_cafes));
            expGroup.add(new ExpensesGroup("Еда", 0, R.drawable.ic_eat));
            expGroup.add(new ExpensesGroup("Машина", 0, R.drawable.ic_car));
            expGroup.add(new ExpensesGroup("Дом", 0, R.drawable.ic_house));
            expGroup.add(new ExpensesGroup("Спорт", 0, R.drawable.ic_sport));
            expGroup.add(new ExpensesGroup("Досуг", 0, R.drawable.ic_hobbies));
        }
        GridView myGrid = (GridView) root.findViewById(R.id.myGrid);
        ImageView imageOfGroup = (ImageView) root.findViewById(R.id.imageOfGroup);
        TextView textOfGroup = (TextView) root.findViewById(R.id.textOfGroup);
        EditText editText = (EditText) root.findViewById(R.id.expInput);
        LinearLayout floatingWindow = (LinearLayout) root.findViewById(R.id.floatWindow);
        View emptyView = (View) root.findViewById(R.id.emptyView);
        myGrid.setAdapter(new GridAdapter(mContext, expGroup));
        LocalDate date = LocalDate.now();

        // When the user clicks on the GridItem
        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = myGrid.getItemAtPosition(position);
                ExpensesGroup ExpGroup = (ExpensesGroup) o;
                floatingWindow.setVisibility(View.VISIBLE);
                imageOfGroup.setImageResource(ExpGroup.getImg());
                textOfGroup.setText(ExpGroup.getName());
                myGrid.setVisibility(View.GONE);
                currentExp = "";
                curExp = ExpGroup;
            }
        });

        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindow.setVisibility(View.GONE);
                myGrid.setVisibility(View.VISIBLE);
                currentExp = "";
            }
        });

        Button oneExp = (Button) root.findViewById(R.id.oneExp);
        Button twoExp = (Button) root.findViewById(R.id.twoExp);
        Button threeExp = (Button) root.findViewById(R.id.threeExp);
        Button fourExp = (Button) root.findViewById(R.id.fourExp);
        Button fiveExp = (Button) root.findViewById(R.id.fiveExp);
        Button sixExp = (Button) root.findViewById(R.id.sixExp);
        Button sevenExp = (Button) root.findViewById(R.id.sevenExp);
        Button eightExp = (Button) root.findViewById(R.id.eightExp);
        Button nineExp = (Button) root.findViewById(R.id.nineExp);
        Button zeroExp = (Button) root.findViewById(R.id.zeroExp);
        Button pointExp = (Button) root.findViewById(R.id.pointExp);
        ImageButton deleteExp = (ImageButton) root.findViewById(R.id.deleteExp);
        ImageButton checkExp = (ImageButton) root.findViewById(R.id.checkExp);

        oneExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentExp += "1";
                editText.setText(currentExp);
            }
        });

        twoExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentExp += "2";
                editText.setText(currentExp);
            }
        });

        threeExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentExp += "3";
                editText.setText(currentExp);
            }
        });

        fourExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentExp += "4";
                editText.setText(currentExp);
            }
        });

        fiveExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentExp += "5";
                editText.setText(currentExp);
            }
        });

        sixExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentExp += "6";
                editText.setText(currentExp);
            }
        });

        sevenExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentExp += "7";
                editText.setText(currentExp);
            }
        });

        eightExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentExp += "8";
                editText.setText(currentExp);
            }
        });

        nineExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentExp += "9";
                editText.setText(currentExp);
            }
        });

        zeroExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentExp += "1";
                editText.setText(currentExp);
            }
        });

        pointExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentExp.contains(",")) {
                    editText.setText(currentExp);
                } else {
                    currentExp += ".";
                    editText.setText(currentExp);
                }

            }
        });

        deleteExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentExp.length() > 0) {
                    currentExp = currentExp.replace(currentExp.substring(currentExp.length() - 1), "");
                    editText.setText(currentExp);
                }

            }
        });

        checkExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentExp.equals("")) {
                    return;
                }
                for (int i = 0; i < expGroup.size(); i++) {
                    if (textOfGroup.getText() == expGroup.get(i).getName()) {
                        expGroup.get(i).setExpCount(expGroup.get(i).getCount() + Double.parseDouble(currentExp));
                        break;
                    }
                }
                HomeFragment nextFrag = new HomeFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
                operGroup.add(new OperatesGroup(curExp.getName(), date, curExp.getImg(), Double.parseDouble("-" + currentExp)));
            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

}